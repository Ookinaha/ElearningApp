package model;

public class KhoaHoc {
    private String maKhoaHoc;
    private String tenKhoaHoc;
    private int kichThuoc;

    public KhoaHoc(String maKhoaHoc, String tenKhoaHoc, int kichThuoc) {
        this.maKhoaHoc = maKhoaHoc;
        this.tenKhoaHoc = tenKhoaHoc;
        this.kichThuoc = kichThuoc;
    }

    public String getMaKhoaHoc() {
        return maKhoaHoc;
    }

    public void setMaKhoaHoc(String maKhoaHoc) {
        this.maKhoaHoc = maKhoaHoc;
    }

    public String getTenKhoaHoc() {
        return tenKhoaHoc;
    }

    public void setTenKhoaHoc(String tenKhoaHoc) {
        this.tenKhoaHoc = tenKhoaHoc;
    }

    public int getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(int kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    @Override
    public String toString() {
        return "KhoaHoc{" +
                "maKhoaHoc=" + maKhoaHoc + '\'' +
                ", tenKhoaHoc='" + tenKhoaHoc + '\'' +
                ", kichThuoc=" + kichThuoc + "MB" + '}';
    }

}