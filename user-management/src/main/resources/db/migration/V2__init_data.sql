INSERT INTO roles (role_name) VALUES ('Admin'), ('User');

INSERT INTO users (username, password, email, role_id)
VALUES ('admin', '$2a$10$lmKHwBFqaLzYi1tjnIbcVeztIeOP9Bx4WHAIwpAyILGpe6ZxsD5ya', 'admin@example.com', 1) , ('user','$2a$10$lmKHwBFqaLzYi1tjnIbcVeztIeOP9Bx4WHAIwpAyILGpe6ZxsD5ya', 'user@example.com', 2);
