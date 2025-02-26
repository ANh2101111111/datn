-- Thêm cột role_id vào bảng users
ALTER TABLE users
ADD role_id BIGINT;
ALTER TABLE users
ADD CONSTRAINT fk_role
FOREIGN KEY (role_id) REFERENCES roles(role_id);