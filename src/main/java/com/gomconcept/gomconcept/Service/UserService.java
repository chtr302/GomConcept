package com.gomconcept.gomconcept.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gomconcept.gomconcept.Model.Role;
import com.gomconcept.gomconcept.Model.User;
import com.gomconcept.gomconcept.Repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(String hoVaTen, String email, String matKhau){
        if (userRepository.existsByEmail(email)){throw new RuntimeException("Email đã được sử dụng với một tài khoản khác");}
        
        User newUser = new User();
        newUser.setHoVaTen(hoVaTen);
        newUser.setEmail(email);
        newUser.setMatKhau(passwordEncoder.encode(matKhau));
        newUser.setRole(Role.NHBT);
        return userRepository.save(newUser);
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public User createGoogleUser(String email, String name) throws Exception {
        if (findByEmail(email).isPresent()){
            throw new Exception("Email đã được sử dụng");
        }
        User user = new User();
        user.setEmail(email);
        user.setHoVaTen(name);
        user.setVaiTro(Role.NHBT.getCode());

        System.out.println("Da tao user thanh cong: " + user);

        return userRepository.save(user);
    }
}
