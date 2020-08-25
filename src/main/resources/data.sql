SELECT setval('products_id_seq', (SELECT max(id) from products), true);
SELECT setval('categories_id_seq', (SELECT max(id) from categories), true);

INSERT INTO categories (name, description)
VALUES ('Clothes', 'All clothes for men and women'),
       ('Phones', 'All phones'),
       ('Technology', 'All technology stuff'),
       ('Toys', 'All toys stuff'),
       ('Sports', 'All sport stuff');

INSERT INTO products (name, quantity, price, description, category_id)
VALUES ('T-Shirt', 10, 29.9, 'New collection of t-shirts has been arrived.',(SELECT id from categories where name = 'Clothes')),
       ('Apple Iphone 11 Pro Max', 100, 699, 'Newest iphone is coming',(SELECT id from categories where name = 'Phones')),
       ('MacBook Pro', 5, 1499, 'MacBook pro 13 inch', (SELECT id from categories where name = 'Technology')),
       ('Lego Game', 15, 14.5, 'Lego Game', (SELECT id from categories where name = 'Toys')),
       ('Fishing Reel', 150, 1.77, 'Fishing reel', (SELECT id from categories where name = 'Sports'));

