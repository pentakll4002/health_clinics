# Health Clinics Backend - Spring Boot

Backend cho hệ thống quản lý phòng khám được xây dựng bằng **Java Spring Boot**, **JPA/Hibernate**, và **PostgreSQL**.

## Cấu trúc Project

```
backend/
├── src/main/java/com/healthclinics/
│   ├── HealthClinicsApplication.java    # Main application
│   ├── config/                          # Cấu hình Security, JWT
│   │   ├── JwtTokenUtil.java
│   │   ├── JwtAuthenticationFilter.java
│   │   ├── CustomUserDetailsService.java
│   │   ├── SecurityConfig.java
│   │   └── UserPrincipal.java
│   ├── controller/                      # REST Controllers
│   │   ├── AuthController.java
│   │   ├── BenhNhanController.java
│   │   ├── NhanVienController.java
│   │   ├── ThuocController.java
│   │   ├── PhieuKhamController.java
│   │   ├── DanhSachTiepNhanController.java
│   │   ├── HoaDonController.java
│   │   ├── LichKhamController.java
│   │   ├── DichVuController.java
│   │   ├── LoaiBenhController.java
│   │   ├── NhomNguoiDungController.java
│   │   ├── ChucNangController.java
│   │   ├── PhanQuyenController.java
│   │   ├── QuiDinhController.java
│   │   ├── DVTController.java
│   │   └── CachDungController.java
│   ├── dto/                             # Data Transfer Objects
│   ├── entity/                          # JPA Entities
│   ├── exception/                       # Exception handling
│   ├── repository/                      # JPA Repositories
│   ├── security/                        # Security components
│   └── service/                         # Business logic
├── src/main/resources/
│   └── application.yml                  # Cấu hình ứng dụng
└── pom.xml                              # Maven dependencies
```

## Yêu cầu hệ thống

- **Java 17+**
- **Maven 3.8+**
- **PostgreSQL 14+**

## Cấu hình Database

1. Tạo database PostgreSQL:
```sql
CREATE DATABASE health_clinics;
```

2. Cập nhật cấu hình trong `application.yml`:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/health_clinics
    username: postgres
    password: your_password
```

## Chạy ứng dụng

```bash
# Di chuyển vào thư mục backend
cd backend

# Build project
mvn clean install

# Chạy ứng dụng
mvn spring-boot:run
```

Ứng dụng sẽ chạy tại: `http://localhost:8080/api`

## API Endpoints

### Authentication
| Method | Endpoint | Mô tả |
|--------|----------|-------|
| POST | `/login` | Đăng nhập |
| POST | `/register` | Đăng ký |
| POST | `/register/verify-otp` | Xác thực OTP |
| POST | `/logout` | Đăng xuất |
| GET | `/user-profile` | Thông tin user |
| GET | `/my-permissions` | Quyền của user |

### Bệnh nhân
| Method | Endpoint | Mô tả |
|--------|----------|-------|
| GET | `/benh-nhan` | Danh sách bệnh nhân |
| GET | `/benh-nhan/{id}` | Chi tiết bệnh nhân |
| POST | `/benh-nhan` | Tạo bệnh nhân |
| PUT | `/benh-nhan/{id}` | Cập nhật |
| DELETE | `/benh-nhan/{id}` | Xóa mềm |

### Nhân viên
| Method | Endpoint | Mô tả |
|--------|----------|-------|
| GET | `/nhanvien` | Danh sách nhân viên |
| GET | `/nhanvien/{id}` | Chi tiết nhân viên |
| POST | `/nhanvien` | Tạo nhân viên |
| PUT | `/nhanvien/{id}` | Cập nhật |
| DELETE | `/nhanvien/{id}` | Xóa |

### Thuốc
| Method | Endpoint | Mô tả |
|--------|----------|-------|
| GET | `/thuoc` | Danh sách thuốc |
| GET | `/thuoc/{id}` | Chi tiết thuốc |
| POST | `/thuoc` | Tạo thuốc |
| PUT | `/thuoc/{id}` | Cập nhật |
| DELETE | `/thuoc/{id}` | Xóa mềm |

### Phiếu khám
| Method | Endpoint | Mô tả |
|--------|----------|-------|
| GET | `/phieu-kham` | Danh sách phiếu khám |
| GET | `/phieu-kham/{id}` | Chi tiết |
| POST | `/phieu-kham` | Tạo phiếu khám |
| PUT | `/phieu-kham/{id}` | Cập nhật |
| POST | `/phieu-kham/{id}/complete` | Hoàn tất |
| POST | `/phieu-kham/{id}/toa-thuoc` | Thêm toa thuốc |
| POST | `/phieu-kham/{id}/dich-vu-phu` | Thêm dịch vụ phụ |

### Lịch khám
| Method | Endpoint | Mô tả |
|--------|----------|-------|
| GET | `/lich-kham` | Danh sách lịch khám |
| GET | `/lich-kham/{id}` | Chi tiết |
| POST | `/lich-kham` | Đặt lịch |
| POST | `/lich-kham/{id}/confirm` | Xác nhận |
| POST | `/lich-kham/{id}/cancel` | Hủy |

### Hóa đơn
| Method | Endpoint | Mô tả |
|--------|----------|-------|
| GET | `/invoices` | Danh sách hóa đơn |
| GET | `/invoices/{id}` | Chi tiết |
| GET | `/invoices/preview/{phieuKhamId}` | Xem trước |
| POST | `/invoices` | Tạo hóa đơn |

### Admin only
| Method | Endpoint | Mô tả |
|--------|----------|-------|
| GET | `/nhom-nguoi-dung` | Nhóm người dùng |
| GET | `/chuc-nang` | Chức năng |
| GET | `/phan-quyen` | Phân quyền |

## Authentication

API sử dụng **JWT Bearer Token**. Thêm header:
```
Authorization: Bearer <token>
```

## Role-based Authorization

- **ROLE_ADMIN**: Toàn quyền quản trị
- **ROLE_MANAGERS**: Quản lý thuốc, loại bệnh, DVT, cách dùng
- **ROLE_DOCTOR**: Bác sĩ khám bệnh
- **ROLE_RECEPTIONIST**: Lễ tân tiếp nhận

## Technologies

- **Spring Boot 3.2.1**
- **Spring Security** với JWT
- **Spring Data JPA** với Hibernate
- **PostgreSQL** database
- **Lombok** - giảm boilerplate code
- **MapStruct** - mapping DTOs
- **Maven** - build tool

## License

MIT License
