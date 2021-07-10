--INSERT Role USER
INSERT INTO USER(name,email,roles,password,is_active) VALUES(
    'testUser','test@test.com','ROLE_USER','$2a$10$27Kls5TCTZUttJHzlmuUqecS0Ab7jRFp2vmBMqKk3HeW3p3ebFx4m',true
);

--INSERT Role ADMIN
INSERT INTO USER(name,email,roles,password,is_active) VALUES(
    'adminUser','test2@test.com','ROLE_ADMIN','$2a$10$27Kls5TCTZUttJHzlmuUqecS0Ab7jRFp2vmBMqKk3HeW3p3ebFx4m',true
);