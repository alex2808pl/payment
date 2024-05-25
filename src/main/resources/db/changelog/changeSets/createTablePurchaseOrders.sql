--liquibase formatted sql
--changeset andrii:create_table
CREATE TABLE PurchaseOrderId (id INT AUTO_INCREMENT NOT NULL, OrderId INT NULL, RecipientID INT NULL, UserID INT NULL, PaymentId INT NULL, Type INT NULL, Status INT NULL, Amount INT NULL, CreatedAt INT NULL, UpdatedAt INT NULL, PRIMARY KEY (id), UNIQUE (UserId));