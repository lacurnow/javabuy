CREATE TABLE favourite_items(
id bigserial PRIMARY KEY,
user_id bigint, 
product_id bigint,
is_favourite boolean,
FOREIGN KEY (user_id)  REFERENCES users(id),
FOREIGN KEY (product_id) REFERENCES products(id)
);