# VD1 - Spring Boot 4 + Spring Security 7 + MapStruct + Thymeleaf

## Chức năng
- Đăng nhập bằng email.
- Mật khẩu được mã hóa BCrypt.
- Phân quyền USER / ADMIN.
- Hiển thị thông tin tài khoản ở header.
- Thymeleaf fragments thủ công, không dùng Layout Dialect.
- MapStruct chuyển User <-> UserDTO.
- SQL Server + JPA.

## Môi trường
- Java 22
- Spring Boot 4.1.1
- MapStruct 1.6.3
- SQL Server

## Cấu hình
1. Copy `.env.example` thành `.env`.
2. Sửa `DB_URL`, `DB_USERNAME`, `DB_PASSWORD` theo SQL Server của bạn.
3. Không commit `.env`.

## Chạy
```bash
mvn clean spring-boot:run
```

Mở:
http://localhost:8088/login

## Tài khoản mẫu
- USER: user@example.com / 123456
- ADMIN: admin@example.com / 123456

Database/table sẽ được JPA tạo/cập nhật khi ứng dụng chạy với `DDL_AUTO=update`.
