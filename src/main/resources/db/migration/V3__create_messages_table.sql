DROP TABLE IF EXISTS messages;

CREATE TABLE messages (
  id BIGSERIAL PRIMARY KEY,
  content VARCHAR(1000) NOT NULL,
  timestamp TIMESTAMP,
  productid BIGINT REFERENCES products(id),
  sellerid BIGINT REFERENCES users(id),
  enquirerid BIGINT REFERENCES users(id),
  senderid BIGINT REFERENCES users(id)
);
