package com.example.quanlisinhvien;

public class SinhVien {
    String idSV;
    String TenSV;
    String LopSV;
    String Email;
    int SDT;

    public SinhVien() {
    }

    public SinhVien(String idSV, String tenSV, String lopSV, String email, int SDT) {
        this.idSV = idSV;
        TenSV = tenSV;
        LopSV = lopSV;
        Email = email;
        this.SDT = SDT;
    }

    public String getIdSV() {
        return idSV;
    }

    public void setIdSV(String idSV) {
        this.idSV = idSV;
    }

    public String getTenSV() {
        return TenSV;
    }

    public void setTenSV(String tenSV) {
        TenSV = tenSV;
    }

    public String getLopSV() {
        return LopSV;
    }

    public void setLopSV(String lopSV) {
        LopSV = lopSV;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getSDT() {
        return SDT;
    }

    public void setSDT(int SDT) {
        this.SDT = SDT;
    }

    @Override
    public String toString() {
        return this.idSV+"\n"+this.TenSV+"\n"+this.LopSV+"\n"+this.Email+"\n"+this.SDT;
    }
}
