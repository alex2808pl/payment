--liquibase formatted sql
--changeset alex2: insertPurchaseOrders
insert into purchaseorders (PurchaseOrderID)
values (1), (2), (3), (4), (4), (4), (4), (4), (4)