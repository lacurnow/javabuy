DROP TABLE IF EXISTS products;

CREATE TABLE products (
  id bigserial PRIMARY KEY,
  name varchar(50) NOT NULL,
  price decimal(5,2),
  description varchar(250) NOT NULL,
  photo varchar(100),
  seller_id bigint,
  buyer_id bigint,
  sold boolean,
  FOREIGN KEY(seller_id) REFERENCES users(id),
  FOREIGN KEY(buyer_id) REFERENCES users(id)
);