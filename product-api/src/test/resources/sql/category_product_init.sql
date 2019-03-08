INSERT INTO category
(category_description, category_name)
VALUES ('Food', 'Food');

INSERT INTO category
(id, category_description, category_name)
VALUES (2, 'Oranges', 'Oranges');

INSERT INTO category
(id, category_description, category_name)
VALUES (3, 'Electronics', 'Electronics');

INSERT INTO category
(id, category_description, category_name)
VALUES (4, 'Televison', 'Televison');

INSERT INTO product(candisplay, isautomotive, isdeleted, isinternational, long_description, pcode, name, short_description, category_id, parent_category_id)
VALUES (1, 0, 0, 1, '70 inch Samsung Television with Retina Display', 'P2345872', 'Samsung 70 inch TV', '', 4, 3);

INSERT INTO product
(candisplay, isautomotive, isdeleted, isinternational, long_description, pcode, name, short_description, category_id, parent_category_id)
VALUES (1, 0, 0, 1, '60 inch Samsung Television with Retina Display', 'P2345873', 'Samsung 60 inch TV', '', 4, 3);