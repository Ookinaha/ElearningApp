package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ELearningApp extends JFrame {

    private JTextField txtMaSinhVien;
    private JTextField txtTenSinhVien;
    private JCheckBox chkJava;
    private JCheckBox chkDb;
    private JCheckBox chkAi;
    private JButton btnDangKyVaTai;
    private JTextArea txtThongTinTai;

    private List<KhoaHoc> danhSachKhoaHocCoSan;

    public ELearningApp() {
        setTitle("Ứng dụng E-Learning Đơn giản");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        khoiTaoDanhSachKhoaHoc();

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel pnlThongTinSinhVien = new JPanel(new GridLayout(2, 2, 5, 5));
        pnlThongTinSinhVien.setBorder(BorderFactory.createTitledBorder("Thông tin sinh viên"));
        pnlThongTinSinhVien.add(new JLabel("Mã sinh viên:"));
        txtMaSinhVien = new JTextField();
        pnlThongTinSinhVien.add(txtMaSinhVien);
        pnlThongTinSinhVien.add(new JLabel("Tên sinh viên:"));
        txtTenSinhVien = new JTextField();
        pnlThongTinSinhVien.add(txtTenSinhVien);

        JPanel pnlChonKhoaHoc = new JPanel();
        pnlChonKhoaHoc.setLayout(new BoxLayout(pnlChonKhoaHoc, BoxLayout.Y_AXIS));
        pnlChonKhoaHoc.setBorder(BorderFactory.createTitledBorder("Chọn khóa học"));
        chkJava = new JCheckBox(danhSachKhoaHocCoSan.get(0).getTenKhoaHoc() + " ("
                + danhSachKhoaHocCoSan.get(0).getKichThuoc() + "MB)");
        chkDb = new JCheckBox(danhSachKhoaHocCoSan.get(1).getTenKhoaHoc() + " ("
                + danhSachKhoaHocCoSan.get(1).getKichThuoc() + "MB)");
        chkAi = new JCheckBox(danhSachKhoaHocCoSan.get(2).getTenKhoaHoc() + " ("
                + danhSachKhoaHocCoSan.get(2).getKichThuoc() + "MB)");
        pnlChonKhoaHoc.add(chkJava);
        pnlChonKhoaHoc.add(chkDb);
        pnlChonKhoaHoc.add(chkAi);

        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.add(pnlThongTinSinhVien, BorderLayout.NORTH);
        topPanel.add(pnlChonKhoaHoc, BorderLayout.CENTER);

        JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnDangKyVaTai = new JButton("Đăng ký và Tải tài liệu");
        pnlButton.add(btnDangKyVaTai);

        txtThongTinTai = new JTextArea(8, 40);
        txtThongTinTai.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtThongTinTai);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Log tải tài liệu và đăng ký"));

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(pnlButton, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        add(mainPanel);

        btnDangKyVaTai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyDangKyVaTaiTaiLieu();
            }
        });
    }

    private void khoiTaoDanhSachKhoaHoc() {
        danhSachKhoaHocCoSan = new ArrayList<>();
        danhSachKhoaHocCoSan.add(new KhoaHoc("KH001", "Lập trình Java", 50));
        danhSachKhoaHocCoSan.add(new KhoaHoc("KH002", "Hệ quản trị cơ sở dữ liệu", 75));
        danhSachKhoaHocCoSan.add(new KhoaHoc("KH003", "Trí tuệ nhân tạo", 120));
    }

    private void xuLyDangKyVaTaiTaiLieu() {
        String maSV = txtMaSinhVien.getText().trim();
        String tenSV = txtTenSinhVien.getText().trim();

        if (maSV.isEmpty() || tenSV.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ Mã và Tên sinh viên.", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        txtThongTinTai.setText(""); // Xóa log cũ trên TextArea
        SinhVien sv = new SinhVien(maSV, tenSV);
        txtThongTinTai.append("Sinh viên: " + sv.getTen() + " (Mã: " + sv.getMa() + ") thực hiện đăng ký:\n");

        boolean coCheckboxDuocChon = false;

        if (chkJava.isSelected()) {
            coCheckboxDuocChon = true;
            KhoaHoc kh = danhSachKhoaHocCoSan.get(0);
            if (sv.dangKy(kh)) {
                txtThongTinTai.append("- Đăng ký MỚI thành công: " + kh.getTenKhoaHoc() + "\n");

            } else {
                txtThongTinTai.append("- Thông tin: Khóa học '" + kh.getTenKhoaHoc()
                        + "' đã được đăng ký trước đó hoặc không hợp lệ.\n");
            }
        }
        if (chkDb.isSelected()) {
            coCheckboxDuocChon = true;
            KhoaHoc kh = danhSachKhoaHocCoSan.get(1);
            if (sv.dangKy(kh)) {
                txtThongTinTai.append("- Đăng ký MỚI thành công: " + kh.getTenKhoaHoc() + "\n");

            } else {
                txtThongTinTai.append("- Thông tin: Khóa học '" + kh.getTenKhoaHoc()
                        + "' đã được đăng ký trước đó hoặc không hợp lệ.\n");
            }
        }
        if (chkAi.isSelected()) {
            coCheckboxDuocChon = true;
            KhoaHoc kh = danhSachKhoaHocCoSan.get(2);
            if (sv.dangKy(kh)) {
                txtThongTinTai.append("- Đăng ký MỚI thành công: " + kh.getTenKhoaHoc() + "\n");
            } else {
                txtThongTinTai.append("- Thông tin: Khóa học '" + kh.getTenKhoaHoc()
                        + "' đã được đăng ký trước đó hoặc không hợp lệ.\n");
            }
        }

        if (!coCheckboxDuocChon) {
            txtThongTinTai.append("Vui lòng chọn ít nhất một khóa học để đăng ký.\n");
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ít nhất một khóa học.", "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Tải tài liệu cho các khóa học đã được đăng ký (bao gồm cả những khóa đã đăng
        // ký từ trước nếu có)
        sv.taiTaiLieu(txtThongTinTai);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ELearningApp().setVisible(true);
            }
        });
    }
}