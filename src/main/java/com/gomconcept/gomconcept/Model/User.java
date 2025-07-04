package com.gomconcept.gomconcept.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="NguoiDung")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaNguoiDung")
    private long id;

    @Column(name="HoVaTen")
    private String hoVaTen;

    @Column(name="Email")
    private String email;

    @Column(name="MatKhau")
    private String matKhau;

    @Column(name="VaiTro")
    private String vaiTro = Role.NHBT.getCode();

    public User(){}

    public User(String hoVaTen, String email, String matKhau) {
        this.hoVaTen = hoVaTen;
        this.email = email;
        this.matKhau = matKhau;
        this.vaiTro = Role.NHBT.getCode();
    }

    public User(String hoVaTen, String email, String matKhau, Role role) {
        this.hoVaTen = hoVaTen;
        this.email = email;
        this.matKhau = matKhau;
        this.vaiTro = role.getCode();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    public Role getRole(){
        return Role.fromString(this.vaiTro);
    }
    public void setRole(Role role){
        this.vaiTro = role.getCode();
    }

    public boolean isAdmin() {
        return Role.HBT.getCode().equals(this.vaiTro);
    }

    public boolean isUser() {
        return Role.NHBT.getCode().equals(this.vaiTro);
    }

    public String getRoleDisplayName() {
        return getRole().getDisplayname();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", hoVaTen='" + hoVaTen + '\'' +
                ", email='" + email + '\'' +
                ", vaiTro='" + vaiTro + '\'' +
                '}';
    }
}
