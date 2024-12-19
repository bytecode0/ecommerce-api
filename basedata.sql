# SQL configs
SET SQL_MODE ='IGNORE_SPACE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

# create database and use it
CREATE DATABASE IF NOT EXISTS ecommerce-petshop-database;
USE ecommerce-petshop-database;

# create the category table
CREATE TABLE IF NOT EXISTS CATEGORY(
category_id int unique key not null auto_increment primary key,
name        varchar(255) null
);

# insert default categories
INSERT INTO CATEGORY(name) VALUES ('Alimentos para Perros'),
                                  ('Alimentos para Gatos'),
                                  ('Accesorios para Perros'),
                                  ('Accesorios para Gatos'),
                                  ('Higiene y Cuidado'),
                                  ('Cuidado de la Salud'),
                                  ('Ropa y Accesorios'),
                                  ('Cajas y Transportadoras'),
                                  ('Comederos y Bebederos'),
                                  ('Suministros de Entrenamiento');

# create the customer table
CREATE TABLE IF NOT EXISTS CUSTOMER(
id       int unique key not null auto_increment primary key,
address  varchar(255) null,
email    varchar(255) null,
password varchar(255) null,
role     varchar(255) null,
username varchar(255) null,
UNIQUE (username)
);

# insert default customers
INSERT INTO CUSTOMER(address, email, password, role, username) VALUES
                                                                   ('123, Albany Street', 'admin@nyan.cat', '123', 'ROLE_ADMIN', 'admin'),
                                                                   ('765, 5th Avenue', 'lisa@gmail.com', '765', 'ROLE_NORMAL', 'lisa');

# create the product table
CREATE TABLE IF NOT EXISTS PRODUCT(
product_id  int unique key not null auto_increment primary key,
description varchar(255) null,
image       varchar(255) null,
name        varchar(255) null,
price       int null,
quantity    int null,
weight      int null,
category_id int null,
customer_id int null
);

# insert default products
INSERT INTO PRODUCT (description, image, name, price, quantity, weight, category_id) VALUES
    ('Croquetas para perros adultos de razas medianas, con pollo y arroz.', 'https://media.zooplus.com/bilder/1/400/81022_pla_eukanuba_activeadultlarge_15_3kg_1.jpg', 'Croquetas Caninas Pollo y Arroz', 19.99, 1, '2 kg', 1),
    ('Alimento húmedo para gatos adultos, sabor salmón.', 'https://files.gosbi.com/products/75/Fresko_Cat_Sterilized_Tuna_loin_with_shrimp.png?size=md', 'Comida Húmeda Gatos Salmón', 15.49, 4, '300 g', 2),
    ('Pienso para cachorros con pollo, ideal para el desarrollo temprano.', 'https://media.zooplus.com/bilder/6/400/252997_252306_253096_pla_royalcanin_dog_mini_puppy_hs_01_6.jpg', 'Pienso Cachorro Pollo', 22.99, 1, '3 kg', 1),
    ('Snacks saludables para perros, ideales para entrenar.', 'https://files.gosbi.com/products/332/Gosbits_Objective_Puppy.png?size=md', 'Bocaditos Entrenamiento Perro', 9.99, 1, '500 g', 2),
    ('Comida seca para gatos senior con pollo y atún.', 'https://files.gosbi.com/products/550/Exclusive_cat_adult.png?size=md', 'Comida Gatos Senior Pollo y Atún', 18.99, 1, '1.5 kg', 2),
    ('Comida de alta proteína para perros activos, con carne de res.', 'https://www.tiendanimal.es/dw/image/v2/BDLQ_PRD/on/demandware.static/-/Sites-kiwoko-master-catalog/default/dwf0710109/images/pienso_perros_criadores_alta%20energia_CRD1125_M.jpg?sw=780&sh=780&q=85', 'Comida Perro Activo Carne de Res', 25.99, 1, '2.5 kg', 1),
    ('Alimento balanceado para gatos con sabor a pavo y vegetales.', 'https://i.ebayimg.com/images/g/~NAAAOSwdnJlwl0J/s-l1600.webp', 'Comida Gato Pavo y Vegetales', 16.49, 1, '2 kg', 2),
    ('Croquetas para perros pequeños con pollo y zanahoria.', 'https://m.media-amazon.com/images/I/71FdRZO2b+L._AC_SL1500_.jpg', 'Croquetas Perro Pequeño Pollo y Zanahoria', 14.99, 1, '1.2 kg', 1),
    ('Snacks naturales para perros con ingredientes orgánicos.', 'https://m.media-amazon.com/images/I/81WNQflfV1L._AC_SL1500_.jpg', 'Bocaditos Orgánicos Perro', 8.99, 1, '400 g', 3),
    ('Comida húmeda para perros adultos, sabor ternera.', 'https://www.tiendanimal.es/dw/image/v2/BDLQ_PRD/on/demandware.static/-/Sites-kiwoko-master-catalog/default/dw3eaa1ec2/images/rc_maxi_adult_pate_lata_ROY1111000.jpg?sw=780&sh=780&q=85', 'Comida Húmeda Perro Ternera', 13.99, 6, '350 g', 1),
    ('Alimento hipoalergénico para perros con piel sensible.', 'https://www.barakaldotiendaveterinaria.es/16596-large_default/barakaldo-pienso-hypoallergenic-grain-free.jpg', 'Comida Hipoalergénica Perro', 30.49, 1, '5 kg', 1),
    ('Croquetas para gatos con proteínas de pescado.', 'https://www.feliway.es/cdn/shop/files/ES_FELIWAY_HAPPY_SNACK_358x.jpg?v=1730363585', 'Comida Gato Pescado', 12.99, 1, '1 kg', 2),
    ('Alimento completo para perros mayores con pollo.', 'https://m.media-amazon.com/images/I/61OSeiseewL._AC_SL1500_.jpg', 'Comida Perro Mayor Pollo', 21.99, 1, '3 kg', 1),
    ('Alimento enlatado para gatos con carne de res.', 'https://m.media-amazon.com/images/I/71FdRZO2b+L._AC_SL1500_.jpg', 'Comida Gato Carne de Res', 5.99, 12, '200 g', 2),
    ('Pienso para gatos con proteínas vegetales y carne de pollo.', 'https://example.com/images/product15.jpg', 'Pienso Gato Proteínas Vegetales', 17.49, 1, '1.5 kg', 2),
    ('Comida húmeda para perros con pavo y zanahoria.', 'https://m.media-amazon.com/images/I/71FdRZO2b+L._AC_SL1500_.jpg', 'Comida Húmeda Perro Pavo y Zanahoria', 10.99, 5, '300 g', 1),
    ('Croquetas para perros grandes con carne de cordero.', 'https://m.media-amazon.com/images/I/419cytDMbQS._AC_.jpg', 'Croquetas Perro Grande Cordero', 26.49, 1, '3 kg', 1),
    ('Bocaditos de pollo para perros en entrenamiento.', 'https://m.media-amazon.com/images/I/71FdRZO2b+L._AC_SL1500_.jpg', 'Bocaditos Entrenamiento Pollo', 7.99, 1, '500 g', 1),
    ('Alimento completo para gatos con pescado y pollo.', 'https://media.zooplus.com/bilder/6/800/neues_projekt_2__6.jpg', 'Comida Gato Pescado y Pollo', 19.99, 1, '2 kg', 2),
    ('Snacks para perros con sabor a hígado y zanahoria.', 'https://m.media-amazon.com/images/I/419cytDMbQS._AC_.jpg', 'Bocaditos Hígado y Zanahoria', 9.49, 1, '300 g', 1),
    ('Comida húmeda para gatos con atún y mejillones.', 'https://i.ebayimg.com/images/g/~NAAAOSwdnJlwl0J/s-l1600.webp', 'Comida Húmeda Gato Atún y Mejillones', 6.99, 8, '150 g', 2),
    ('Alimento seco para perros con carne de pato y patatas.', 'https://m.media-amazon.com/images/I/419cytDMbQS._AC_.jpg', 'Comida Perro Pato y Patatas', 23.99, 1, '2.5 kg', 1),
    ('Pienso para gatos con pollo y arroz integral.', 'https://i.ebayimg.com/images/g/~NAAAOSwdnJlwl0J/s-l1600.webp', 'Pienso Gato Pollo y Arroz Integral', 14.99, 1, '1 kg', 2),
    ('Comida húmeda para perros con cordero y arroz.', 'https://m.media-amazon.com/images/I/419cytDMbQS._AC_.jpg', 'Comida Húmeda Perro Cordero y Arroz', 11.99, 6, '300 g', 1),
    ('Alimento natural para perros con pollo y batata.', 'https://m.media-amazon.com/images/I/419cytDMbQS._AC_.jpg', 'Comida Natural Perro Pollo y Batata', 28.99, 1, '3 kg', 1);

# create the cart-product table

CREATE TABLE CART_PRODUCT (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    count INT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES PRODUCT(product_id)
);

# create indexes
CREATE INDEX FK7u438kvwr308xcwr4wbx36uiw
    ON PRODUCT (category_id);

CREATE INDEX FKt23apo8r9s2hse1dkt95ig0w5
    ON PRODUCT (customer_id);