INSERT INTO users (id, username, firstname, lastname, password) VALUES
  (1, 'admin', 'admin', 'admin', 'admin'),
  (2, 'user', 'user', 'user', 'user');
INSERT INTO user_role (user_id, roles) VALUES
  (1, 'ADMIN'),
  (2, 'USER');