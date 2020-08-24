INSERT INTO categories (id, name, description)
VALUES (1, 'Clothes', 'All clothes for men and women'),
       (2, 'Phones', 'All phones'),
       (3, 'Technology', 'All technology stuff'),
       (4, 'Toys', 'All toys stuff'),
       (5, 'Sports', 'All sport stuff');

INSERT INTO products (id, name, quantity, price, description, category_id)
VALUES (1, 'T-Shirt', 10, 29.9, 'New collection of t-shirts has been arrived.',(SELECT id from categories where name = 'Clothes')),
       (2, 'Apple Iphone 11 Pro Max', 100, 699, 'Newest iphone is coming',(SELECT id from categories where name = 'Phones')),
       (3, 'MacBook Pro', 5, 1499, 'MacBook pro 13 inch', (SELECT id from categories where name = 'Technology')),
       (4, 'Lego Game', 15, 14.5, 'Lego Game', (SELECT id from categories where name = 'Toys')),
       (5, 'Fishing Reel', 150, 1.77, 'Fishing reel', (SELECT id from categories where name = 'Sports'));

