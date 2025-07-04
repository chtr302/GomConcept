package com.gomconcept.gomconcept.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name="SanPham")
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="MaSanPham")
    private long maSanPham;

    @Column(name="TenSanPham")
    private String tenSanPham;

    @Column(name="MoTa")
    private String moTa;

    @Column(name="GiaTienCoBan", precision=10, scale=3)
    private BigDecimal giaTienCoBan;

    @Column(name="HinhAnhHienThi")
    private String hinhAnhHienThi;

    @Column(name="HinhAnh2")
    private String hinhAnh2;

    @Column(name="HinhAnh3")
    private String hinhAnh3;

    @Column(name="HinhAnh4")
    private String hinhAnh4;

    @Column(name="TinhTrang", columnDefinition="BIT")
    private Boolean tinhTrang;

    @Column(name="NgayThemSanPham")
    private LocalDateTime ngayTao;

    @Column(name="Slug", unique=true, length=255)
    private String slug;

    @PrePersist
    protected void onCreate() {
        ngayTao = LocalDateTime.now();

        if (this.slug == null || this.slug.isEmpty()) {
            generateSlug();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        if (this.slug == null || this.slug.isEmpty()) {
            generateSlug();
        }
    }

    public Product(){
        this.tinhTrang = false;
        this.ngayTao = LocalDateTime.now();
    }

    public Product(String hinhAnh2, String hinhAnh3, String hinhAnh4, String hinhAnhHienThi, long maSanPham, String moTa, BigDecimal giaTienCoBan, String tenSanPham, Boolean tinhTrang) {
        this.hinhAnh2 = hinhAnh2;
        this.hinhAnh3 = hinhAnh3;
        this.hinhAnh4 = hinhAnh4;
        this.hinhAnhHienThi = hinhAnhHienThi;
        this.maSanPham = maSanPham;
        this.moTa = moTa;
        this.giaTienCoBan = giaTienCoBan;
        this.tenSanPham = tenSanPham;
        this.tinhTrang = tinhTrang != null ? tinhTrang : false;
        this.ngayTao = LocalDateTime.now();
    }

    public void generateSlug() {
        if (this.tenSanPham != null) {
            this.slug = createSlug(this.tenSanPham);
        }
    }

    private String createSlug(String text) {
        if (text == null) return "";
        
        String result = text.toLowerCase().trim();

        result = result.replaceAll("[àáạảãâầấậẩẫăằắặẳẵ]", "a");
        result = result.replaceAll("[ÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴ]", "a");

        result = result.replaceAll("[èéẹẻẽêềếệểễ]", "e");
        result = result.replaceAll("[ÈÉẸẺẼÊỀẾỆỂỄ]", "e");

        result = result.replaceAll("[ìíịỉĩ]", "i");
        result = result.replaceAll("[ÌÍỊỈĨ]", "i");

        result = result.replaceAll("[òóọỏõôồốộổỗơờớợởỡ]", "o");
        result = result.replaceAll("[ÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠ]", "o");

        result = result.replaceAll("[ùúụủũưừứựửữ]", "u");
        result = result.replaceAll("[ÙÚỤỦŨƯỪỨỰỬỮ]", "u");

        result = result.replaceAll("[ỳýỵỷỹ]", "y");
        result = result.replaceAll("[ỲÝỴỶỸ]", "y");

        result = result.replaceAll("[đ]", "d");
        result = result.replaceAll("[Đ]", "d");

        result = result.replaceAll("[ç]", "c");
        result = result.replaceAll("[Ç]", "c");

        result = result.replaceAll("[ñ]", "n");
        result = result.replaceAll("[Ñ]", "n");

        result = result.replaceAll("[^a-z0-9\\s-]", "");

        result = result.replaceAll("\\s+", "-");

        result = result.replaceAll("-+", "-");

        result = result.replaceAll("^-|-$", "");
        
        return result;
    }

    public long getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(long maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getHinhAnhHienThi() {
        return hinhAnhHienThi;
    }

    public void setHinhAnhHienThi(String hinhAnhHienThi) {
        this.hinhAnhHienThi = hinhAnhHienThi;
    }

    public String getHinhAnh2() {
        return hinhAnh2;
    }

    public void setHinhAnh2(String hinhAnh2) {
        this.hinhAnh2 = hinhAnh2;
    }

    public String getHinhAnh3() {
        return hinhAnh3;
    }

    public void setHinhAnh3(String hinhAnh3) {
        this.hinhAnh3 = hinhAnh3;
    }

    public String getHinhAnh4() {
        return hinhAnh4;
    }

    public void setHinhAnh4(String hinhAnh4) {
        this.hinhAnh4 = hinhAnh4;
    }

    public BigDecimal getGiaTienCoBan() {
        return giaTienCoBan;
    }

    public void setGiaTienCoBan(BigDecimal giaTienCoBan) {
        this.giaTienCoBan = giaTienCoBan;
    }

    public Boolean getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(Boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public boolean isAvailable() {
        return tinhTrang != null && !tinhTrang;
    }

    public boolean isOutOfStock() {
        return tinhTrang != null && tinhTrang;
    }

    public LocalDateTime getNgayTao() {
        return ngayTao;
    }
    
    public void setNgayTao(LocalDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getSlug() { 
        return slug; 
    }

    public void setSlug(String slug) { 
        this.slug = slug; 
    }
}