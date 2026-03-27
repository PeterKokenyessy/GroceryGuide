TRUNCATE categories, brands, stores, products, prices RESTART IDENTITY CASCADE;
CREATE SCHEMA public;

\i schema.sql

SET search_path TO public;

\copy categories(id,name) FROM 'csvFiles/categories.csv' CSV HEADER
\copy brands(id,name) FROM 'csvFiles/brands.csv' CSV HEADER
\copy stores(id,name,color) FROM 'csvFiles/stores.csv' CSV HEADER

\copy products(id,name,quantity,unit,category_id,brand_id,image_url) FROM 'csvFiles/products.csv' CSV HEADER

\copy prices(product_id,store_id,price) FROM 'csvFiles/prices.csv' CSV HEADER

SELECT setval('categories_id_seq', (SELECT MAX(id) FROM categories));
SELECT setval('brands_id_seq', (SELECT MAX(id) FROM brands));
SELECT setval('stores_id_seq', (SELECT MAX(id) FROM stores));
SELECT setval('products_id_seq', (SELECT MAX(id) FROM products));
SELECT setval('prices_id_seq', (SELECT MAX(id) FROM prices));

SELECT COUNT(*) AS categories FROM categories;
SELECT COUNT(*) AS brands FROM brands;
SELECT COUNT(*) AS stores FROM stores;
SELECT COUNT(*) AS products FROM products;
SELECT COUNT(*) AS prices FROM prices;