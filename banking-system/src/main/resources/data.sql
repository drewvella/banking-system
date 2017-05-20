INSERT INTO banking_user(username,password,enabled) VALUES ('bank_user','$2a$04$H9kJnsHlt0AaDrFHWGVtB.OyljQod.sugVf5apJh3vdXCwWAvaIfm',TRUE);
INSERT INTO banking_user_role(banking_user_id,role_name) VALUES  (1,'ROLE_USER');
INSERT INTO banking_user_role(banking_user_id,role_name) VALUES  (1,'ROLE_ADMIN');
INSERT INTO client (client_name,client_surname) VALUES ('test','test');
INSERT INTO ACCOUNT (DESCRIPTION,CLIENT_ID,balance) VALUES ('test1', (select id from client where client_name = 'test'), 10);
INSERT INTO ACCOUNT (DESCRIPTION,CLIENT_ID,balance) VALUES ('test2', (select id from client where client_name = 'test'), 0);

INSERT INTO account_transaction (reference, source_account_id, debited_amount, destination_account_id, credited_amount) VALUES ('TESTREF',10000,-10,10050,10);
