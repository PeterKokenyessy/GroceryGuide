DROP TABLE IF EXISTS prices CASCADE;
DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS stores CASCADE;
DROP TABLE IF EXISTS brands CASCADE;

CREATE TABLE IF NOT EXISTS brands (
                                      id SERIAL PRIMARY KEY,
                                      name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS stores (
                                      id SERIAL PRIMARY KEY,
                                      name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS products (
                                        id SERIAL PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL,
                                        quantity DOUBLE PRECISION NOT NULL,
                                        unit VARCHAR(50) NOT NULL,
                                        category_id INTEGER REFERENCES categories(id),
                                        brand_id INTEGER REFERENCES brands(id)
);

CREATE TABLE IF NOT EXISTS prices (
                                      id SERIAL PRIMARY KEY,
                                      product_id INTEGER REFERENCES products(id),
                                      store_id INTEGER REFERENCES stores(id),
                                      price DOUBLE PRECISION NOT NULL
);

INSERT INTO brands (name) VALUES
                              ('Pick'),
                              ('Sága'),
                              ('Mizo'),
                              ('Pöttyös'),
                              ('Gyermelyi')
ON CONFLICT DO NOTHING;

INSERT INTO stores (name) VALUES
                              ('Lidl'),
                              ('Tesco'),
                              ('Aldi'),
                              ('Auchan')
ON CONFLICT DO NOTHING;

INSERT INTO products (name, quantity, unit, category_id, brand_id) VALUES
    ('Chicken breast fillet', 1000, 'g',
     (SELECT id FROM categories WHERE slug='Meats'),
     (SELECT id FROM brands WHERE name='Sága')
    )
ON CONFLICT DO NOTHING;

INSERT INTO products (name, quantity, unit, category_id, brand_id) VALUES
    ('Chicken thigh', 1000, 'g',
     (SELECT id FROM categories WHERE slug='Meats'),
     (SELECT id FROM brands WHERE name='Sága')
    )
ON CONFLICT DO NOTHING;

INSERT INTO products (name, quantity, unit, category_id, brand_id) VALUES
    ('Pork salami', 400, 'g',
     (SELECT id FROM categories WHERE slug='Meats'),
     (SELECT id FROM brands WHERE name='Pick')
    )
ON CONFLICT DO NOTHING;

INSERT INTO products (name, quantity, unit, category_id, brand_id) VALUES
    ('Minced pork', 1000, 'g',
     (SELECT id FROM categories WHERE slug='Meats'),
     (SELECT id FROM brands WHERE name='Sága')
    )
ON CONFLICT DO NOTHING;

INSERT INTO products (name, quantity, unit, category_id, brand_id) VALUES
    ('UHT milk 2.8%', 1, 'l',
     (SELECT id FROM categories WHERE slug='Dairies'),
     (SELECT id FROM brands WHERE name='Mizo')
    )
ON CONFLICT DO NOTHING;

INSERT INTO products (name, quantity, unit, category_id, brand_id) VALUES
    ('Butter', 250, 'g',
     (SELECT id FROM categories WHERE slug='Dairies'),
     (SELECT id FROM brands WHERE name='Mizo')
    )
ON CONFLICT DO NOTHING;

INSERT INTO products (name, quantity, unit, category_id, brand_id) VALUES
    ('Fruit yogurt strawberry', 150, 'g',
     (SELECT id FROM categories WHERE slug='Dairies'),
     (SELECT id FROM brands WHERE name='Pöttyös')
    )
ON CONFLICT DO NOTHING;

INSERT INTO products (name, quantity, unit, category_id, brand_id) VALUES
    ('Dry spaghetti', 500, 'g',
     (SELECT id FROM categories WHERE slug='Bakery products'),
     (SELECT id FROM brands WHERE name='Gyermelyi')
    )
ON CONFLICT DO NOTHING;

INSERT INTO products (name, quantity, unit, category_id, brand_id) VALUES
    ('Toast bread', 500, 'g',
     (SELECT id FROM categories WHERE slug='Bakery products'),
     (SELECT id FROM brands WHERE name='Gyermelyi')
    )
ON CONFLICT DO NOTHING;

INSERT INTO products (name, quantity, unit, category_id, brand_id) VALUES
    ('Frozen peas', 750, 'g',
     (SELECT id FROM categories WHERE slug='Frozen foods'),
     (SELECT id FROM brands WHERE name='Mizo')
    )
ON CONFLICT DO NOTHING;

INSERT INTO products (name, quantity, unit, category_id, brand_id) VALUES
    ('Beef goulash meat', 800, 'g',
     (SELECT id FROM categories WHERE slug='Meats'),
     (SELECT id FROM brands WHERE name='Pick')
    )
ON CONFLICT DO NOTHING;

INSERT INTO products (name, quantity, unit, category_id, brand_id) VALUES
    ('Smoked bacon', 300, 'g',
     (SELECT id FROM categories WHERE slug='Meats'),
     (SELECT id FROM brands WHERE name='Pick')
    )
ON CONFLICT DO NOTHING;

INSERT INTO products (name, quantity, unit, category_id, brand_id) VALUES
    ('Turkey breast slices', 200, 'g',
     (SELECT id FROM categories WHERE slug='Meats'),
     (SELECT id FROM brands WHERE name='Sága')
    )
ON CONFLICT DO NOTHING;

INSERT INTO products (name, quantity, unit, category_id, brand_id) VALUES
    ('Sausage frankfurter', 500, 'g',
     (SELECT id FROM categories WHERE slug='Meats'),
     (SELECT id FROM brands WHERE name='Sága')
    )
ON CONFLICT DO NOTHING;

INSERT INTO products (name, quantity, unit, category_id, brand_id) VALUES
    ('Trappista cheese slices', 200, 'g',
     (SELECT id FROM categories WHERE slug='Dairies'),
     (SELECT id FROM brands WHERE name='Mizo')
    )
ON CONFLICT DO NOTHING;

INSERT INTO products (name, quantity, unit, category_id, brand_id) VALUES
    ('Natural yogurt', 400, 'g',
     (SELECT id FROM categories WHERE slug='Dairies'),
     (SELECT id FROM brands WHERE name='Pöttyös')
    )
ON CONFLICT DO NOTHING;

INSERT INTO products (name, quantity, unit, category_id, brand_id) VALUES
    ('Sour cream 20%', 330, 'g',
     (SELECT id FROM categories WHERE slug='Dairies'),
     (SELECT id FROM brands WHERE name='Mizo')
    )
ON CONFLICT DO NOTHING;

INSERT INTO products (name, quantity, unit, category_id, brand_id) VALUES
    ('Cooking cream 20%', 500, 'ml',
     (SELECT id FROM categories WHERE slug='Dairies'),
     (SELECT id FROM brands WHERE name='Mizo')
    )
ON CONFLICT DO NOTHING;

INSERT INTO products (name, quantity, unit, category_id, brand_id) VALUES
    ('Penne pasta', 500, 'g',
     (SELECT id FROM categories WHERE slug='Bakery products'),
     (SELECT id FROM brands WHERE name='Gyermelyi')
    )
ON CONFLICT DO NOTHING;

INSERT INTO products (name, quantity, unit, category_id, brand_id) VALUES
    ('Whole wheat pasta', 500, 'g',
     (SELECT id FROM categories WHERE slug='Bakery products'),
     (SELECT id FROM brands WHERE name='Gyermelyi')
    )
ON CONFLICT DO NOTHING;

INSERT INTO products (name, quantity, unit, category_id, brand_id) VALUES
    ('White flour', 1000, 'g',
     (SELECT id FROM categories WHERE slug='Bakery products'),
     (SELECT id FROM brands WHERE name='Gyermelyi')
    )
ON CONFLICT DO NOTHING;

INSERT INTO products (name, quantity, unit, category_id, brand_id) VALUES
    ('Frozen green beans', 750, 'g',
     (SELECT id FROM categories WHERE slug='Frozen foods'),
     (SELECT id FROM brands WHERE name='Mizo')
    )
ON CONFLICT DO NOTHING;

INSERT INTO products (name, quantity, unit, category_id, brand_id) VALUES
    ('Frozen sweet corn', 1000, 'g',
     (SELECT id FROM categories WHERE slug='Frozen foods'),
     (SELECT id FROM brands WHERE name='Mizo')
    )
ON CONFLICT DO NOTHING;

INSERT INTO products (name, quantity, unit, category_id, brand_id) VALUES
    ('Frozen chicken nuggets', 750, 'g',
     (SELECT id FROM categories WHERE slug='Frozen foods'),
     (SELECT id FROM brands WHERE name='Sága')
    )
ON CONFLICT DO NOTHING;

INSERT INTO products (name, quantity, unit, category_id, brand_id) VALUES
    ('Frozen french fries', 1000, 'g',
     (SELECT id FROM categories WHERE slug='Frozen foods'),
     (SELECT id FROM brands WHERE name='Gyermelyi')
    )
ON CONFLICT DO NOTHING;

INSERT INTO products (name, quantity, unit, category_id, brand_id) VALUES
    ('Frozen broccoli', 1000, 'g',
     (SELECT id FROM categories WHERE slug='Frozen foods'),
     (SELECT id FROM brands WHERE name='Mizo')
    )
ON CONFLICT DO NOTHING;

INSERT INTO products (name, quantity, unit, category_id, brand_id) VALUES
    ('Frozen spinach', 750, 'g',
     (SELECT id FROM categories WHERE slug='Frozen foods'),
     (SELECT id FROM brands WHERE name='Mizo')
    )
ON CONFLICT DO NOTHING;

INSERT INTO products (name, quantity, unit, category_id, brand_id) VALUES
    ('Frozen fish fillet', 600, 'g',
     (SELECT id FROM categories WHERE slug='Frozen foods'),
     (SELECT id FROM brands WHERE name='Sága')
    )
ON CONFLICT DO NOTHING;

INSERT INTO products (name, quantity, unit, category_id, brand_id) VALUES
    ('Frozen pizza salami', 450, 'g',
     (SELECT id FROM categories WHERE slug='Frozen foods'),
     (SELECT id FROM brands WHERE name='Gyermelyi')
    )
ON CONFLICT DO NOTHING;

INSERT INTO products (name, quantity, unit, category_id, brand_id) VALUES
    ('Frozen berry mix', 750, 'g',
     (SELECT id FROM categories WHERE slug='Frozen foods'),
     (SELECT id FROM brands WHERE name='Gyermelyi')
    )
ON CONFLICT DO NOTHING;

-- Chicken breast fillet
INSERT INTO prices (product_id, store_id, price) VALUES
                                                     ((SELECT id FROM products WHERE name='Chicken breast fillet'), (SELECT id FROM stores WHERE name='Lidl'), 6.20),
                                                     ((SELECT id FROM products WHERE name='Chicken breast fillet'), (SELECT id FROM stores WHERE name='Tesco'), 6.45),
                                                     ((SELECT id FROM products WHERE name='Chicken breast fillet'), (SELECT id FROM stores WHERE name='Aldi'), 6.10),
                                                     ((SELECT id FROM products WHERE name='Chicken breast fillet'), (SELECT id FROM stores WHERE name='Auchan'), 6.50)
ON CONFLICT DO NOTHING;

-- Chicken thigh
INSERT INTO prices (product_id, store_id, price) VALUES
                                                     ((SELECT id FROM products WHERE name='Chicken thigh'), (SELECT id FROM stores WHERE name='Lidl'), 5.60),
                                                     ((SELECT id FROM products WHERE name='Chicken thigh'), (SELECT id FROM stores WHERE name='Tesco'), 5.80),
                                                     ((SELECT id FROM products WHERE name='Chicken thigh'), (SELECT id FROM stores WHERE name='Aldi'), 5.55),
                                                     ((SELECT id FROM products WHERE name='Chicken thigh'), (SELECT id FROM stores WHERE name='Auchan'), 5.90)
ON CONFLICT DO NOTHING;

-- Pork salami
INSERT INTO prices (product_id, store_id, price) VALUES
                                                     ((SELECT id FROM products WHERE name='Pork salami'), (SELECT id FROM stores WHERE name='Lidl'), 6.10),
                                                     ((SELECT id FROM products WHERE name='Pork salami'), (SELECT id FROM stores WHERE name='Tesco'), 6.30),
                                                     ((SELECT id FROM products WHERE name='Pork salami'), (SELECT id FROM stores WHERE name='Aldi'), 6.00),
                                                     ((SELECT id FROM products WHERE name='Pork salami'), (SELECT id FROM stores WHERE name='Auchan'), 6.50)
ON CONFLICT DO NOTHING;

-- Minced pork
INSERT INTO prices (product_id, store_id, price) VALUES
                                                     ((SELECT id FROM products WHERE name='Minced pork'), (SELECT id FROM stores WHERE name='Lidl'), 5.10),
                                                     ((SELECT id FROM products WHERE name='Minced pork'), (SELECT id FROM stores WHERE name='Tesco'), 5.30),
                                                     ((SELECT id FROM products WHERE name='Minced pork'), (SELECT id FROM stores WHERE name='Aldi'), 5.00),
                                                     ((SELECT id FROM products WHERE name='Minced pork'), (SELECT id FROM stores WHERE name='Auchan'), 5.40)
ON CONFLICT DO NOTHING;

-- UHT milk 2.8%
INSERT INTO prices (product_id, store_id, price) VALUES
                                                     ((SELECT id FROM products WHERE name='UHT milk 2.8%'), (SELECT id FROM stores WHERE name='Lidl'), 1.20),
                                                     ((SELECT id FROM products WHERE name='UHT milk 2.8%'), (SELECT id FROM stores WHERE name='Tesco'), 1.25),
                                                     ((SELECT id FROM products WHERE name='UHT milk 2.8%'), (SELECT id FROM stores WHERE name='Aldi'), 1.15),
                                                     ((SELECT id FROM products WHERE name='UHT milk 2.8%'), (SELECT id FROM stores WHERE name='Auchan'), 1.30)
ON CONFLICT DO NOTHING;

-- Butter
INSERT INTO prices (product_id, store_id, price) VALUES
                                                     ((SELECT id FROM products WHERE name='Butter'), (SELECT id FROM stores WHERE name='Lidl'), 2.30),
                                                     ((SELECT id FROM products WHERE name='Butter'), (SELECT id FROM stores WHERE name='Tesco'), 2.45),
                                                     ((SELECT id FROM products WHERE name='Butter'), (SELECT id FROM stores WHERE name='Aldi'), 2.25),
                                                     ((SELECT id FROM products WHERE name='Butter'), (SELECT id FROM stores WHERE name='Auchan'), 2.50)
ON CONFLICT DO NOTHING;

-- Fruit yogurt strawberry
INSERT INTO prices (product_id, store_id, price) VALUES
                                                     ((SELECT id FROM products WHERE name='Fruit yogurt strawberry'), (SELECT id FROM stores WHERE name='Lidl'), 0.75),
                                                     ((SELECT id FROM products WHERE name='Fruit yogurt strawberry'), (SELECT id FROM stores WHERE name='Tesco'), 0.80),
                                                     ((SELECT id FROM products WHERE name='Fruit yogurt strawberry'), (SELECT id FROM stores WHERE name='Aldi'), 0.70),
                                                     ((SELECT id FROM products WHERE name='Fruit yogurt strawberry'), (SELECT id FROM stores WHERE name='Auchan'), 0.85)
ON CONFLICT DO NOTHING;

-- Dry spaghetti
INSERT INTO prices (product_id, store_id, price) VALUES
                                                     ((SELECT id FROM products WHERE name='Dry spaghetti'), (SELECT id FROM stores WHERE name='Lidl'), 1.40),
                                                     ((SELECT id FROM products WHERE name='Dry spaghetti'), (SELECT id FROM stores WHERE name='Tesco'), 1.55),
                                                     ((SELECT id FROM products WHERE name='Dry spaghetti'), (SELECT id FROM stores WHERE name='Aldi'), 1.35),
                                                     ((SELECT id FROM products WHERE name='Dry spaghetti'), (SELECT id FROM stores WHERE name='Auchan'), 1.60)
ON CONFLICT DO NOTHING;

-- Toast bread
INSERT INTO prices (product_id, store_id, price) VALUES
                                                     ((SELECT id FROM products WHERE name='Toast bread'), (SELECT id FROM stores WHERE name='Lidl'), 1.30),
                                                     ((SELECT id FROM products WHERE name='Toast bread'), (SELECT id FROM stores WHERE name='Tesco'), 1.45),
                                                     ((SELECT id FROM products WHERE name='Toast bread'), (SELECT id FROM stores WHERE name='Aldi'), 1.25),
                                                     ((SELECT id FROM products WHERE name='Toast bread'), (SELECT id FROM stores WHERE name='Auchan'), 1.50)
ON CONFLICT DO NOTHING;

-- Frozen peas
INSERT INTO prices (product_id, store_id, price) VALUES
                                                     ((SELECT id FROM products WHERE name='Frozen peas'), (SELECT id FROM stores WHERE name='Lidl'), 2.10),
                                                     ((SELECT id FROM products WHERE name='Frozen peas'), (SELECT id FROM stores WHERE name='Tesco'), 2.25),
                                                     ((SELECT id FROM products WHERE name='Frozen peas'), (SELECT id FROM stores WHERE name='Aldi'), 2.05),
                                                     ((SELECT id FROM products WHERE name='Frozen peas'), (SELECT id FROM stores WHERE name='Auchan'), 2.30)
ON CONFLICT DO NOTHING;

-- Beef goulash meat
INSERT INTO prices (product_id, store_id, price) VALUES
                                                     ((SELECT id FROM products WHERE name='Beef goulash meat'), (SELECT id FROM stores WHERE name='Lidl'), 8.70),
                                                     ((SELECT id FROM products WHERE name='Beef goulash meat'), (SELECT id FROM stores WHERE name='Tesco'), 8.95),
                                                     ((SELECT id FROM products WHERE name='Beef goulash meat'), (SELECT id FROM stores WHERE name='Aldi'), 8.60),
                                                     ((SELECT id FROM products WHERE name='Beef goulash meat'), (SELECT id FROM stores WHERE name='Auchan'), 9.10)
ON CONFLICT DO NOTHING;

-- Smoked bacon
INSERT INTO prices (product_id, store_id, price) VALUES
                                                     ((SELECT id FROM products WHERE name='Smoked bacon'), (SELECT id FROM stores WHERE name='Lidl'), 3.10),
                                                     ((SELECT id FROM products WHERE name='Smoked bacon'), (SELECT id FROM stores WHERE name='Tesco'), 3.25),
                                                     ((SELECT id FROM products WHERE name='Smoked bacon'), (SELECT id FROM stores WHERE name='Aldi'), 3.00),
                                                     ((SELECT id FROM products WHERE name='Smoked bacon'), (SELECT id FROM stores WHERE name='Auchan'), 3.35)
ON CONFLICT DO NOTHING;

-- Turkey breast slices
INSERT INTO prices (product_id, store_id, price) VALUES
                                                     ((SELECT id FROM products WHERE name='Turkey breast slices'), (SELECT id FROM stores WHERE name='Lidl'), 3.40),
                                                     ((SELECT id FROM products WHERE name='Turkey breast slices'), (SELECT id FROM stores WHERE name='Tesco'), 3.60),
                                                     ((SELECT id FROM products WHERE name='Turkey breast slices'), (SELECT id FROM stores WHERE name='Aldi'), 3.30),
                                                     ((SELECT id FROM products WHERE name='Turkey breast slices'), (SELECT id FROM stores WHERE name='Auchan'), 3.65)
ON CONFLICT DO NOTHING;

-- Sausage frankfurter
INSERT INTO prices (product_id, store_id, price) VALUES
                                                     ((SELECT id FROM products WHERE name='Sausage frankfurter'), (SELECT id FROM stores WHERE name='Lidl'), 4.10),
                                                     ((SELECT id FROM products WHERE name='Sausage frankfurter'), (SELECT id FROM stores WHERE name='Tesco'), 4.30),
                                                     ((SELECT id FROM products WHERE name='Sausage frankfurter'), (SELECT id FROM stores WHERE name='Aldi'), 4.00),
                                                     ((SELECT id FROM products WHERE name='Sausage frankfurter'), (SELECT id FROM stores WHERE name='Auchan'), 4.35)
ON CONFLICT DO NOTHING;

-- Trappista cheese slices
INSERT INTO prices (product_id, store_id, price) VALUES
                                                     ((SELECT id FROM products WHERE name='Trappista cheese slices'), (SELECT id FROM stores WHERE name='Lidl'), 2.20),
                                                     ((SELECT id FROM products WHERE name='Trappista cheese slices'), (SELECT id FROM stores WHERE name='Tesco'), 2.35),
                                                     ((SELECT id FROM products WHERE name='Trappista cheese slices'), (SELECT id FROM stores WHERE name='Aldi'), 2.15),
                                                     ((SELECT id FROM products WHERE name='Trappista cheese slices'), (SELECT id FROM stores WHERE name='Auchan'), 2.40)
ON CONFLICT DO NOTHING;

-- Natural yogurt
INSERT INTO prices (product_id, store_id, price) VALUES
                                                     ((SELECT id FROM products WHERE name='Natural yogurt'), (SELECT id FROM stores WHERE name='Lidl'), 1.10),
                                                     ((SELECT id FROM products WHERE name='Natural yogurt'), (SELECT id FROM stores WHERE name='Tesco'), 1.25),
                                                     ((SELECT id FROM products WHERE name='Natural yogurt'), (SELECT id FROM stores WHERE name='Aldi'), 1.05),
                                                     ((SELECT id FROM products WHERE name='Natural yogurt'), (SELECT id FROM stores WHERE name='Auchan'), 1.30)
ON CONFLICT DO NOTHING;

-- Sour cream 20%
INSERT INTO prices (product_id, store_id, price) VALUES
                                                     ((SELECT id FROM products WHERE name='Sour cream 20%'), (SELECT id FROM stores WHERE name='Lidl'), 1.30),
                                                     ((SELECT id FROM products WHERE name='Sour cream 20%'), (SELECT id FROM stores WHERE name='Tesco'), 1.45),
                                                     ((SELECT id FROM products WHERE name='Sour cream 20%'), (SELECT id FROM stores WHERE name='Aldi'), 1.25),
                                                     ((SELECT id FROM products WHERE name='Sour cream 20%'), (SELECT id FROM stores WHERE name='Auchan'), 1.50)
ON CONFLICT DO NOTHING;

-- Cooking cream 20%
INSERT INTO prices (product_id, store_id, price) VALUES
                                                     ((SELECT id FROM products WHERE name='Cooking cream 20%'), (SELECT id FROM stores WHERE name='Lidl'), 1.75),
                                                     ((SELECT id FROM products WHERE name='Cooking cream 20%'), (SELECT id FROM stores WHERE name='Tesco'), 1.90),
                                                     ((SELECT id FROM products WHERE name='Cooking cream 20%'), (SELECT id FROM stores WHERE name='Aldi'), 1.70),
                                                     ((SELECT id FROM products WHERE name='Cooking cream 20%'), (SELECT id FROM stores WHERE name='Auchan'), 1.95)
ON CONFLICT DO NOTHING;

-- Penne pasta
INSERT INTO prices (product_id, store_id, price) VALUES
                                                     ((SELECT id FROM products WHERE name='Penne pasta'), (SELECT id FROM stores WHERE name='Lidl'), 1.35),
                                                     ((SELECT id FROM products WHERE name='Penne pasta'), (SELECT id FROM stores WHERE name='Tesco'), 1.55),
                                                     ((SELECT id FROM products WHERE name='Penne pasta'), (SELECT id FROM stores WHERE name='Aldi'), 1.30),
                                                     ((SELECT id FROM products WHERE name='Penne pasta'), (SELECT id FROM stores WHERE name='Auchan'), 1.60)
ON CONFLICT DO NOTHING;

-- Whole wheat pasta
INSERT INTO prices (product_id, store_id, price) VALUES
                                                     ((SELECT id FROM products WHERE name='Whole wheat pasta'), (SELECT id FROM stores WHERE name='Lidl'), 1.50),
                                                     ((SELECT id FROM products WHERE name='Whole wheat pasta'), (SELECT id FROM stores WHERE name='Tesco'), 1.70),
                                                     ((SELECT id FROM products WHERE name='Whole wheat pasta'), (SELECT id FROM stores WHERE name='Aldi'), 1.45),
                                                     ((SELECT id FROM products WHERE name='Whole wheat pasta'), (SELECT id FROM stores WHERE name='Auchan'), 1.75)
ON CONFLICT DO NOTHING;

-- White flour
INSERT INTO prices (product_id, store_id, price) VALUES
                                                     ((SELECT id FROM products WHERE name='White flour'), (SELECT id FROM stores WHERE name='Lidl'), 0.95),
                                                     ((SELECT id FROM products WHERE name='White flour'), (SELECT id FROM stores WHERE name='Tesco'), 1.05),
                                                     ((SELECT id FROM products WHERE name='White flour'), (SELECT id FROM stores WHERE name='Aldi'), 0.90),
                                                     ((SELECT id FROM products WHERE name='White flour'), (SELECT id FROM stores WHERE name='Auchan'), 1.10)
ON CONFLICT DO NOTHING;

-- Frozen green beans
INSERT INTO prices (product_id, store_id, price) VALUES
                                                     ((SELECT id FROM products WHERE name='Frozen green beans'), (SELECT id FROM stores WHERE name='Lidl'), 2.00),
                                                     ((SELECT id FROM products WHERE name='Frozen green beans'), (SELECT id FROM stores WHERE name='Tesco'), 2.15),
                                                     ((SELECT id FROM products WHERE name='Frozen green beans'), (SELECT id FROM stores WHERE name='Aldi'), 1.95),
                                                     ((SELECT id FROM products WHERE name='Frozen green beans'), (SELECT id FROM stores WHERE name='Auchan'), 2.20)
ON CONFLICT DO NOTHING;

-- Frozen sweet corn
INSERT INTO prices (product_id, store_id, price) VALUES
                                                     ((SELECT id FROM products WHERE name='Frozen sweet corn'), (SELECT id FROM stores WHERE name='Lidl'), 2.30),
                                                     ((SELECT id FROM products WHERE name='Frozen sweet corn'), (SELECT id FROM stores WHERE name='Tesco'), 2.50),
                                                     ((SELECT id FROM products WHERE name='Frozen sweet corn'), (SELECT id FROM stores WHERE name='Aldi'), 2.25),
                                                     ((SELECT id FROM products WHERE name='Frozen sweet corn'), (SELECT id FROM stores WHERE name='Auchan'), 2.55)
ON CONFLICT DO NOTHING;

-- Frozen chicken nuggets
INSERT INTO prices (product_id, store_id, price) VALUES
                                                     ((SELECT id FROM products WHERE name='Frozen chicken nuggets'), (SELECT id FROM stores WHERE name='Lidl'), 4.40),
                                                     ((SELECT id FROM products WHERE name='Frozen chicken nuggets'), (SELECT id FROM stores WHERE name='Tesco'), 4.65),
                                                     ((SELECT id FROM products WHERE name='Frozen chicken nuggets'), (SELECT id FROM stores WHERE name='Aldi'), 4.30),
                                                     ((SELECT id FROM products WHERE name='Frozen chicken nuggets'), (SELECT id FROM stores WHERE name='Auchan'), 4.70)
ON CONFLICT DO NOTHING;

-- Frozen french fries
INSERT INTO prices (product_id, store_id, price) VALUES
                                                     ((SELECT id FROM products WHERE name='Frozen french fries'), (SELECT id FROM stores WHERE name='Lidl'), 2.80),
                                                     ((SELECT id FROM products WHERE name='Frozen french fries'), (SELECT id FROM stores WHERE name='Tesco'), 3.00),
                                                     ((SELECT id FROM products WHERE name='Frozen french fries'), (SELECT id FROM stores WHERE name='Aldi'), 2.75),
                                                     ((SELECT id FROM products WHERE name='Frozen french fries'), (SELECT id FROM stores WHERE name='Auchan'), 3.05)
ON CONFLICT DO NOTHING;

-- Frozen broccoli
INSERT INTO prices (product_id, store_id, price) VALUES
                                                     ((SELECT id FROM products WHERE name='Frozen broccoli'), (SELECT id FROM stores WHERE name='Lidl'), 2.50),
                                                     ((SELECT id FROM products WHERE name='Frozen broccoli'), (SELECT id FROM stores WHERE name='Tesco'), 2.70),
                                                     ((SELECT id FROM products WHERE name='Frozen broccoli'), (SELECT id FROM stores WHERE name='Aldi'), 2.45),
                                                     ((SELECT id FROM products WHERE name='Frozen broccoli'), (SELECT id FROM stores WHERE name='Auchan'), 2.75)
ON CONFLICT DO NOTHING;

-- Frozen spinach
INSERT INTO prices (product_id, store_id, price) VALUES
                                                     ((SELECT id FROM products WHERE name='Frozen spinach'), (SELECT id FROM stores WHERE name='Lidl'), 1.90),
                                                     ((SELECT id FROM products WHERE name='Frozen spinach'), (SELECT id FROM stores WHERE name='Tesco'), 2.05),
                                                     ((SELECT id FROM products WHERE name='Frozen spinach'), (SELECT id FROM stores WHERE name='Aldi'), 1.85),
                                                     ((SELECT id FROM products WHERE name='Frozen spinach'), (SELECT id FROM stores WHERE name='Auchan'), 2.10)
ON CONFLICT DO NOTHING;

-- Frozen fish fillet
INSERT INTO prices (product_id, store_id, price) VALUES
                                                     ((SELECT id FROM products WHERE name='Frozen fish fillet'), (SELECT id FROM stores WHERE name='Lidl'), 5.20),
                                                     ((SELECT id FROM products WHERE name='Frozen fish fillet'), (SELECT id FROM stores WHERE name='Tesco'), 5.45),
                                                     ((SELECT id FROM products WHERE name='Frozen fish fillet'), (SELECT id FROM stores WHERE name='Aldi'), 5.10),
                                                     ((SELECT id FROM products WHERE name='Frozen fish fillet'), (SELECT id FROM stores WHERE name='Auchan'), 5.55)
ON CONFLICT DO NOTHING;
