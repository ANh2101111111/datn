-- Thêm các cột mới vào bảng products
ALTER TABLE products
ADD COLUMN image_url VARCHAR(255),
ADD COLUMN rating DOUBLE PRECISION,
ADD COLUMN original_price DECIMAL(10, 2),
ADD COLUMN discounted_price DECIMAL(10, 2);




