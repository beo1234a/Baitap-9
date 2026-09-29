IF DB_ID(N'webst3') IS NULL CREATE DATABASE webst3;
GO
USE webst3;
GO
IF NOT EXISTS (SELECT 1 FROM roles WHERE name='ROLE_USER') INSERT INTO roles(name) VALUES('ROLE_USER');
IF NOT EXISTS (SELECT 1 FROM roles WHERE name='ROLE_ADMIN') INSERT INTO roles(name) VALUES('ROLE_ADMIN');
GO
-- Ung dung tu dong tao users/products/otp_tokens bang Hibernate.
-- Tai khoan demo: admin / 123456 va user / 123456 duoc tao khi ung dung khoi dong.
