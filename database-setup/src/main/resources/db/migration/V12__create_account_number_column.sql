use ecommerce;

ALTER TABLE `user`
ADD COLUMN `account_number` VARCHAR(255) NOT NULL AFTER `user_name`;
