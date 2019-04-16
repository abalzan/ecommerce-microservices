use ecommerce;

CREATE TABLE `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) NOT NULL,
  `house_number` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  `street_address` varchar(255) NOT NULL,
  `zip_code` varchar(255) NOT NULL,
  `account_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKascogpq8x3gfx04oy2fr6l3i5` (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;