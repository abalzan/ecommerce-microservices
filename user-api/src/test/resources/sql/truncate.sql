SET FOREIGN_KEY_CHECKS=0;

TRUNCATE TABLE USER;

SET FOREIGN_KEY_CHECKS=1;

ALTER TABLE USER ALTER COLUMN ID RESTART WITH 1;