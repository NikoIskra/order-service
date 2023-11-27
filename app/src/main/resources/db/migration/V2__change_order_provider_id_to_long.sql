ALTER TABLE orders
DROP COLUMN provider_id,
ADD COLUMN provider_id bigint;