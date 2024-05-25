--liquibase formatted sql
--changeset andrii:create_table
CREATE TABLE purchase_order(
id int primary keey auto_increment,
order_id int,
recipient_id int,
user_id int,
payment_id int,
type int,
status int,
amount int,
created_at int,
updated_at int);