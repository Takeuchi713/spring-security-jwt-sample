--INSERT Role USER
INSERT INTO USER(name,email,roles,password,is_active) VALUES(
    'user','test@test.com','ROLE_USER','$2a$10$27Kls5TCTZUttJHzlmuUqecS0Ab7jRFp2vmBMqKk3HeW3p3ebFx4m',true
);

--INSERT Role ADMIN
INSERT INTO USER(name,email,roles,password,is_active) VALUES(
    'admin','test2@test.com','ROLE_ADMIN','$2a$10$27Kls5TCTZUttJHzlmuUqecS0Ab7jRFp2vmBMqKk3HeW3p3ebFx4m',true
);

--INSERT Role ADMIN and USER
INSERT INTO USER(name,email,roles,password,is_active) VALUES(
    'admin_user','test3@test.com','ROLE_USER,ROLE_ADMIN','$2a$10$27Kls5TCTZUttJHzlmuUqecS0Ab7jRFp2vmBMqKk3HeW3p3ebFx4m',true
);