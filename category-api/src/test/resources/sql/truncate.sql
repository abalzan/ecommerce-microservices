SET FOREIGN_KEY_CHECKS=0;

TRUNCATE TABLE CATEGORY;

SET FOREIGN_KEY_CHECKS=1;

ALTER TABLE CATEGORY ALTER COLUMN ID RESTART WITH 1;