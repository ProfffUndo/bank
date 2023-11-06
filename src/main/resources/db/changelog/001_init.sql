CREATE SCHEMA IF NOT EXISTS bank;

CREATE TABLE IF NOT EXISTS bank.account (
                                            account_id serial PRIMARY KEY,
                                            number VARCHAR (64) UNIQUE NOT NULL,
                                            balance numeric (10,2) DEFAULT 0
);
CREATE TABLE IF NOT EXISTS bank.transaction (
                                                tran_id serial PRIMARY KEY,
                                                sender_id bigint,
                                                receiver_id bigint,
                                                sum numeric (10,2) DEFAULT 0,
                                                tran_time timestamp,
                                                FOREIGN KEY (sender_id) REFERENCES account(account_id),
                                                FOREIGN KEY (receiver_id) REFERENCES account(account_id)
);