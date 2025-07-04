package com.gomconcept.gomconcept.Model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ChiTietSanPham")
public class ProductDetail {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="MaChiTiet")
    private long maChiTiet;

    @Column(name="MaSanPham")
    private long maSanPham;

    @Column(name="KichThuoc")
    private String kichThuoc;

    @Column(name="ChieuCao")
    private long chieuCao;

    @Column(name="ChieuRong")
    private long chieuRong;

    @Column(name="GiaTien")
    private BigDecimal giaTien;

    @Column(name="SoLuongTrongKho")
    private long soLuongTrongKho;

    public ProductDetail() {}

    public ProductDetail(long maChiTiet, long maSanPham, String kichThuoc, long chieuCao, long chieuRong,
            BigDecimal giaTien, long soLuongTrongKho) {
        this.maChiTiet = maChiTiet;
        this.maSanPham = maSanPham;
        this.kichThuoc = kichThuoc;
        this.chieuCao = chieuCao;
        this.chieuRong = chieuRong;
        this.giaTien = giaTien;
        this.soLuongTrongKho = soLuongTrongKho;
    }

    public long getMaChiTiet() {
        return maChiTiet;
    }

    public void setMaChiTiet(long maChiTiet) {
        this.maChiTiet = maChiTiet;
    }

    public long getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(long maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(String kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public long getChieuCao() {
        return chieuCao;
    }

    public void setChieuCao(long chieuCao) {
        this.chieuCao = chieuCao;
    }

    public long getChieuRong() {
        return chieuRong;
    }

    public void setChieuRong(long chieuRong) {
        this.chieuRong = chieuRong;
    }

    public BigDecimal getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(BigDecimal giaTien) {
        this.giaTien = giaTien;
    }

    public long getSoLuongTrongKho() {
        return soLuongTrongKho;
    }

    public void setSoLuongTrongKho(long soLuongTrongKho) {
        this.soLuongTrongKho = soLuongTrongKho;
    }
    
    
}
