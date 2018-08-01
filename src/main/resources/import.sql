--INSERT INTO users (id, username, password, email, active) VALUES (1, 'a', 'a', 'user@example.com', true);
--INSERT INTO users (id, username, password, email, active) VALUES (2, 'b', 'a', 'admin@example.com', true);

INSERT INTO users (id, password, email, active, certificate) VALUES (1, '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'user', true, 'user');
INSERT INTO users (id, password, email, active, certificate) VALUES (2, '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'admin', true, 'admin');


INSERT INTO authority (name) VALUES ('ROLE_USER');
INSERT INTO authority (name) VALUES ('ROLE_ADMIN');

INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 2);
