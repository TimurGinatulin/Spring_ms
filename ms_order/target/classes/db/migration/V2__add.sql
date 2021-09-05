CREATE TABLE `shop_order_db`.`cart` (
  `id` VARCHAR(64) NOT NULL,
  `user_id` BIGINT NOT NULL,
  `price` DECIMAL NULL,
  PRIMARY KEY (`id`));
CREATE TABLE `shop_order_db`.`cart_item` (
  `id` BIGINT NOT NULL,
  `cart_id` VARCHAR(64) NOT NULL,
  `product_id` BIGINT NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `quantity` INT NOT NULL,
  `price_per_product` DECIMAL NOT NULL,
  `price` DECIMAL NOT NULL,
  `time_stamp` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`));
