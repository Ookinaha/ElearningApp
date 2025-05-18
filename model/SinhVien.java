package model;

import java.util.ArrayList;
import java.util.List;

public class SinhVien {
    private String ma;
    private String ten;
    private List<KhoaHoc> khoaHocDangKy;

    public SinhVien(String ma, String ten) {
        this.ma = ma;
        this.ten = ten;
        this.khoaHocDangKy = new ArrayList<>();
    }

    public String getMa() {
        return ma;
    }

    // Setter va getter
    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public List<KhoaHoc> getKhoaHocDangKy() {
        return khoaHocDangKy;
    }

    public void setKhoaHocDangKy(List<KhoaHoc> khoaHocDangKy) {
        this.khoaHocDangKy = khoaHocDangKy;
    }

    // Ham kiem tra dang
    public boolean dangKy(KhoaHoc khoaHoc) {
        if (khoaHoc != null && !khoaHocDangKy.contains(khoaHoc)) {
            return true;
        }
        return false;
    }

    // Ham tai tai lieulieu
    public void taiTaiLieu(javax.swing.JTextArea logTextArea) {
        if (khoaHocDangKy.isEmpty()) {
            logTextArea.append("Sinh Vien " + ten + " chua dang ky khoa hoc nao.\n");
            return;
        }

        logTextArea.append("Bắt đầu quá trình tải tài liệu cho sinh viên: " + ten + " (Mã SV: " + ma + ")\n");
        for (KhoaHoc kh : khoaHocDangKy) {
            Thread taiLieuThread = new Thread(() -> {
                try {
                    Thread.sleep(200); // Yêu cầu sleep 200ms
                    String thongBao = "Đã tải xong tài liệu cho khóa học: " + kh.getTenKhoaHoc() + " (cho SV: " + ten
                            + ")\n";
                    System.out.print(thongBao);
                    javax.swing.SwingUtilities.invokeLater(() -> logTextArea.append(thongBao));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    String loi = "Lỗi khi tải tài liệu khóa học " + kh.getTenKhoaHoc() + ": " + e.getMessage() + "\n";
                    System.err.print(loi);
                    javax.swing.SwingUtilities.invokeLater(() -> logTextArea.append(loi));
                }
            });
            taiLieuThread.setName("Thread-TaiLieu-" + kh.getMaKhoaHoc());
            taiLieuThread.start();
        }
    }
}