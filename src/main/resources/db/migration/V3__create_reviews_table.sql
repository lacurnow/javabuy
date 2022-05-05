DROP TABLE IF EXISTS reviews;

CREATE TABLE reviews (
  id bigserial PRIMARY KEY,
  title varchar(50) NOT NULL,
  content varchar (250) NOT NULL,
  rating integer,
  user_id bigint,
  product_id bigint,
  photo varchar(100),
  FOREIGN KEY(user_id) REFERENCES users(id)
);