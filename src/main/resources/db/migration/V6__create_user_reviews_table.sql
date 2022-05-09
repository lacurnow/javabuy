DROP TABLE IF EXISTS user_reviews;

CREATE TABLE user_reviews (
  id BIGSERIAL PRIMARY KEY,
  title VARCHAR(50) NOT NULL,
  content VARCHAR(1000) NOT NULL,
  rating INT NOT NULL,
  timestamp TIMESTAMP,
  userid BIGINT REFERENCES users(id),
  authorid BIGINT REFERENCES users(id)
);