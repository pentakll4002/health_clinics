package com.healthclinics.config;

import com.healthclinics.entity.*;
import com.healthclinics.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final NhanVienRepository nhanVienRepository;
    private final NhomNguoiDungRepository nhomNguoiDungRepository;
    private final BenhNhanRepository benhNhanRepository;
    private final ThuocRepository thuocRepository;
    private final DichVuRepository dichVuRepository;
    private final LoaiBenhRepository loaiBenhRepository;
    private final DVTRepository dvtRepository;
    private final CachDungRepository cachDungRepository;
    private final QuiDinhRepository quiDinhRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.count() > 0) {
            log.info("Database already initialized, skipping...");
            return;
        }

        log.info("Initializing database with seed data...");

        // Create user groups
        NhomNguoiDung adminGroup = nhomNguoiDungRepository.save(NhomNguoiDung.builder()
                .maNhom("ADMIN")
                .tenNhom("Quản trị hệ thống")
                .build());

        NhomNguoiDung managerGroup = nhomNguoiDungRepository.save(NhomNguoiDung.builder()
                .maNhom("MANAGERS")
                .tenNhom("Quản lý")
                .build());

        NhomNguoiDung doctorGroup = nhomNguoiDungRepository.save(NhomNguoiDung.builder()
                .maNhom("DOCTOR")
                .tenNhom("Bác sĩ")
                .build());

        NhomNguoiDung receptionistGroup = nhomNguoiDungRepository.save(NhomNguoiDung.builder()
                .maNhom("RECEPTIONIST")
                .tenNhom("Tiếp tân")
                .build());

        // Create admin user
        User adminUser = userRepository.save(User.builder()
                .name("Admin")
                .email("admin@healthclinic.com")
                .password(passwordEncoder.encode("admin123"))
                .role("ADMIN")
                .build());

        nhanVienRepository.save(NhanVien.builder()
                .user(adminUser)
                .hoTenNV("Quản trị viên")
                .dienThoai("0900000001")
                .diaChi("Hà Nội")
                .idNhom(adminGroup.getIdNhom())
                .userId(adminUser.getId())
                .trangThai("active")
                .build());

        // Create doctor user
        User doctorUser = userRepository.save(User.builder()
                .name("Doctor")
                .email("doctor@healthclinic.com")
                .password(passwordEncoder.encode("doctor123"))
                .role("DOCTOR")
                .build());

        nhanVienRepository.save(NhanVien.builder()
                .user(doctorUser)
                .hoTenNV("Bác sĩ Nguyễn Văn A")
                .dienThoai("0900000002")
                .diaChi("Hà Nội")
                .idNhom(doctorGroup.getIdNhom())
                .userId(doctorUser.getId())
                .trangThai("active")
                .build());

        // Create receptionist user
        User receptionistUser = userRepository.save(User.builder()
                .name("Receptionist")
                .email("receptionist@healthclinic.com")
                .password(passwordEncoder.encode("receptionist123"))
                .role("RECEPTIONIST")
                .build());

        nhanVienRepository.save(NhanVien.builder()
                .user(receptionistUser)
                .hoTenNV("Nhân viên tiếp tân")
                .dienThoai("0900000003")
                .diaChi("Hà Nội")
                .idNhom(receptionistGroup.getIdNhom())
                .userId(receptionistUser.getId())
                .trangThai("active")
                .build());

        // Create patient user
        User patientUser = userRepository.save(User.builder()
                .name("Patient")
                .email("patient@healthclinic.com")
                .password(passwordEncoder.encode("patient123"))
                .role("patient")
                .build());

        benhNhanRepository.save(BenhNhan.builder()
                .user(patientUser)
                .hoTenBN("Bệnh nhân Trần Thị B")
                .dienThoai("0911111111")
                .diaChi("Hà Nội")
                .ngaySinh(LocalDate.of(1990, 5, 15))
                .gioiTinh("Nữ")
                .ngayDK(LocalDateTime.now())
                .userId(patientUser.getId())
                .isDeleted(false)
                .build());

        // Create DVT (units)
        DVT vien = dvtRepository.save(DVT.builder().tenDvt("Viên").build());
        DVT chai = dvtRepository.save(DVT.builder().tenDvt("Chai").build());
        DVT goi = dvtRepository.save(DVT.builder().tenDvt("Gói").build());
        DVT hop = dvtRepository.save(DVT.builder().tenDvt("Hộp").build());

        // Create CachDung (usage methods)
        CachDung uong = cachDungRepository.save(CachDung.builder().moTaCachDung("Uống").build());
        CachDung bo = cachDungRepository.save(CachDung.builder().moTaCachDung("Bôi").build());
        CachDung small = cachDungRepository.save(CachDung.builder().moTaCachDung("Nhỏ").build());

        // Create LoaiBenh (disease types)
        loaiBenhRepository.save(LoaiBenh.builder().tenLoaiBenh("Cảm cúm").build());
        loaiBenhRepository.save(LoaiBenh.builder().tenLoaiBenh("Tiêu hóa").build());
        loaiBenhRepository.save(LoaiBenh.builder().tenLoaiBenh("Hô hấp").build());
        loaiBenhRepository.save(LoaiBenh.builder().tenLoaiBenh("Da liễu").build());

        // Create Thuoc (medicines)
        thuocRepository.save(Thuoc.builder()
                .tenThuoc("Paracetamol 500mg")
                .idDvt(vien.getIdDvt())
                .idCachDung(uong.getIdCachDung())
                .donGiaNhap(BigDecimal.valueOf(1000))
                .tyLeGiaBan(BigDecimal.valueOf(2.0))
                .donGiaBan(BigDecimal.valueOf(2000))
                .soLuongTon(1000)
                .isDeleted(false)
                .build());

        thuocRepository.save(Thuoc.builder()
                .tenThuoc("Amoxicillin 500mg")
                .idDvt(vien.getIdDvt())
                .idCachDung(uong.getIdCachDung())
                .donGiaNhap(BigDecimal.valueOf(2500))
                .tyLeGiaBan(BigDecimal.valueOf(2.0))
                .donGiaBan(BigDecimal.valueOf(5000))
                .soLuongTon(500)
                .isDeleted(false)
                .build());

        thuocRepository.save(Thuoc.builder()
                .tenThuoc("Vitamin C 1000mg")
                .idDvt(vien.getIdDvt())
                .idCachDung(uong.getIdCachDung())
                .donGiaNhap(BigDecimal.valueOf(750))
                .tyLeGiaBan(BigDecimal.valueOf(2.0))
                .donGiaBan(BigDecimal.valueOf(1500))
                .soLuongTon(800)
                .isDeleted(false)
                .build());

        // Create DichVu (services)
        dichVuRepository.save(DichVu.builder()
                .tenDichVu("Khám tổng quát")
                .donGia(BigDecimal.valueOf(200000))
                .isDeleted(false)
                .build());

        dichVuRepository.save(DichVu.builder()
                .tenDichVu("Khám chuyên khoa")
                .donGia(BigDecimal.valueOf(300000))
                .isDeleted(false)
                .build());

        dichVuRepository.save(DichVu.builder()
                .tenDichVu("Xét nghiệm máu")
                .donGia(BigDecimal.valueOf(150000))
                .isDeleted(false)
                .build());

        // Create QuiDinh (regulations)
        quiDinhRepository.save(QuiDinh.builder()
                .tenQuyDinh("Số bệnh nhân tối đa trong ngày")
                .giaTri(BigDecimal.valueOf(40))
                .build());

        quiDinhRepository.save(QuiDinh.builder()
                .tenQuyDinh("Số thuốc tối đa trong toa")
                .giaTri(BigDecimal.valueOf(10))
                .build());

        quiDinhRepository.save(QuiDinh.builder()
                .tenQuyDinh("Giá khám cơ bản")
                .giaTri(BigDecimal.valueOf(100000))
                .build());

        log.info("Database initialization completed!");
        log.info("Default users created:");
        log.info("  - admin@healthclinic.com / admin123 (Admin)");
        log.info("  - doctor@healthclinic.com / doctor123 (Doctor)");
        log.info("  - receptionist@healthclinic.com / receptionist123 (Receptionist)");
        log.info("  - patient@healthclinic.com / patient123 (Patient)");
    }
}
