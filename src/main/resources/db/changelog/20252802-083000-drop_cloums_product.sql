ALTER TABLE products
DROP COLUMN discounted_price,
DROP COLUMN price,
ADD COLUMN type VARCHAR(50) NULL;
