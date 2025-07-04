package com.gomconcept.gomconcept.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gomconcept.gomconcept.Model.User;
import com.gomconcept.gomconcept.Model.Role;
import com.gomconcept.gomconcept.Service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            String hoVaTen = request.get("hoVaTen");
            String email = request.get("email");
            String matKhau = request.get("matKhau");
            
            User newUser = userService.registerUser(hoVaTen, email, matKhau);
            
            response.put("success", true);
            response.put("message", "Đăng ký thành công");
            response.put("userId", newUser.getId());
            response.put("hoVaTen", newUser.getHoVaTen());
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> login(@RequestBody Map<String, String> request, HttpSession session){
        Map<String, Object> response = new HashMap<>();

        try{
            String email = request.get("email");
            String matKhau = request.get("matKhau");

            Optional<User> userOpt = userService.findByEmail(email);
            
            if (userOpt.isPresent()){
                User user = userOpt.get();
                if (userService.checkPassword(matKhau, user.getMatKhau())){
                    session.setAttribute("userId", user.getId());
                    session.setAttribute("hoVaTen", user.getHoVaTen());
                    session.setAttribute("email", user.getEmail());
                    session.setAttribute("vaiTro", user.getVaiTro());
                    session.setAttribute("isLoggedIn", true);

                    response.put("success", true);
                    response.put("message", "Đăng nhập thành công");
                    response.put("user", Map.of(
                        "id", user.getId(),
                        "hoVaTen", user.getHoVaTen(),
                        "email", user.getEmail(),
                        "vaiTro", user.getRoleDisplayName(),
                        "isAdmin", user.isAdmin()
                    ));
                    if (user.isAdmin()){
                        response.put("redirectUrl", "/admin/dashboard");
                    } else {
                        response.put("redirectUrl", "/");
                    }
                } else {
                    response.put("success", false);
                    response.put("message", "Mật khẩu không đúng");
                }
            } else {
                response.put("success", false);
                response.put("message", "Email không tồn tại");
            }
        } catch (Exception e){
            response.put("success", false);
            response.put("message", "Có lỗi xảy ra: " + e.getMessage());
        }
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/logout")
    public ResponseEntity<Map<String, Object>> logout(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        session.invalidate();
        
        response.put("success", true);
        response.put("message", "Đăng xuất thành công");
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> getCurrentUser(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // Check if user is logged in
            Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
            
            if (isLoggedIn != null && isLoggedIn) {
                Long userId = (Long) session.getAttribute("userId");
                String hoVaTen = (String) session.getAttribute("hoVaTen");
                String email = (String) session.getAttribute("email");
                String vaiTro = (String) session.getAttribute("vaiTro");
                
                response.put("success", true);
                response.put("user", Map.of(
                    "id", userId,
                    "hoVaTen", hoVaTen,
                    "email", email,
                    "vaiTro", Role.fromString(vaiTro).getDisplayname(),
                    "isAdmin", Role.HBT.getCode().equals(vaiTro)
                ));
            } else {
                response.put("success", false);
                response.put("message", "User not logged in");
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error: " + e.getMessage());
        }
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/oauth2")
    public void loginWithOuath2(
            @AuthenticationPrincipal OAuth2User oAuth2User,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        if (oAuth2User != null){
            String email = oAuth2User.getAttribute("email");
            String name = oAuth2User.getAttribute("name");

            User user = findOrCreateGoogleUser(email, name);

            HttpSession session = request.getSession();
            setUserSession(session, user);
        }
        response.sendRedirect("/");
    }

    private User findOrCreateGoogleUser(String email, String name){
        Optional<User> existingUser = userService.findByEmail(email);
        if (existingUser.isPresent()){
            return existingUser.get();
        } else {
            try{
                return userService.createGoogleUser(email, name);
            } catch (Exception e) {
                throw new RuntimeException("Không thể tạo tài khoản từ Google: " + e.getMessage());
            }
        }
    }

    private void setUserSession(HttpSession session, User user) {
        session.setAttribute("userId", user.getId());
        session.setAttribute("hoVaTen", user.getHoVaTen());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("vaiTro", user.getVaiTro());
        session.setAttribute("isLoggedIn", true);
    }

    // private Map<String, Object> createUserResponse(User user) {
    //     return Map.of(
    //         "id", user.getId(),
    //         "hoVaTen", user.getHoVaTen(),
    //         "email", user.getEmail(),
    //         "vaiTro", user.getRoleDisplayName(),
    //         "isAdmin", user.isAdmin()
    //     );
    // }
}
