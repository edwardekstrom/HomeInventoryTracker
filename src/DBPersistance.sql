DROP TABLE IF EXISTS "items";
CREATE TABLE "items" ("id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , "product_container" INTEGER, "product" INTEGER, "barcode" VARCHAR, "entry_date" DATETIME, "exit_date" DATETIME, "expiration_date" DATETIME);
DROP TABLE IF EXISTS "pc_join_p";
CREATE TABLE "pc_join_p" ("product_container" INTEGER, "product" INTEGER);
DROP TABLE IF EXISTS "product_containers";
CREATE TABLE "product_containers" ("id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , "name" VARCHAR, "parent" INTEGER, "storage_unit" INTEGER, "three_month_amount" DOUBLE, "three_month_unit" VARCHAR);
DROP TABLE IF EXISTS "products";
CREATE TABLE "products" ("id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , "description" TEXT, "three_month_supply" DOUBLE, "amount" DOUBLE, "unit" VARCHAR, "shelf_life" INTEGER, "barcode" VARCHAR);
