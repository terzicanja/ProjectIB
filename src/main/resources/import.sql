--INSERT INTO users (id, username, password, email, active) VALUES (1, 'a', 'a', 'user@example.com', true);
--INSERT INTO users (id, username, password, email, active) VALUES (2, 'b', 'a', 'admin@example.com', true);

INSERT INTO users (id, password, email, active) VALUES (1, 'a', 'user@example.com', true);
INSERT INTO users (id, password, email, active) VALUES (2, 'a', 'admin@example.com', true);


INSERT INTO authority (name) VALUES ('ROLE_USER');
INSERT INTO authority (name) VALUES ('ROLE_ADMIN');

INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 2);
