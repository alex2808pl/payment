--liquibase formatted sql
--changeset andrii:create_table_PurchaseOrderId
CREATE TABLE PurchaseOrderId (PurchaseOrderID INT AUTO_INCREMENT NOT NULL, OrderId INT NULL, RecipientID INT NULL, UserID INT NULL, PaymentId INT NULL, Type INT NULL, Status INT NULL, Amount INT NULL, CreatedAt INT NULL, UpdatedAt INT NULL, PRIMARY KEY (PurchaseOrderID), UNIQUE (UserId));