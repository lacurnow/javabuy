CREATE TABLE favourite_items(
user_id bigint, 
product_id bigint,
is_favourite boolean,
PRIMARY KEY (user_id, product_id),
FOREIGN KEY (user_id)  REFERENCES users(id),
FOREIGN KEY (product_id) REFERENCES products(id)
);