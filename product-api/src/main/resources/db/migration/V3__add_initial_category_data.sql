DELIMITER //
CREATE PROCEDURE AddCategoryData()
 BEGIN
    INSERT INTO category
    (id, category_description, category_name)
    VALUES
    (1, 'Food', 'Food');

    INSERT INTO category
    (id, category_description, category_name)
    VALUES
    (2, 'Oranges', 'Oranges');

    INSERT INTO category
    (id, category_description, category_name)
    VALUES
    (3, 'Electronics', 'Electronics');

    INSERT INTO category
    (id, category_description, category_name)
    VALUES
    (4, 'Televison', 'Televison');
 END //
DELIMITER;

CALL AddCategoryData();
