CREATE TABLE "items" (
"id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL ,
"product_container" INTEGER FOREIGN KEY,
"product" INTEGER FOREIGN KEY, 
"barcode" VARCHAR, 
"entry_date" DATETIME, 
"exit_date" DATETIME, 
"expiration_date" DATETIME, 
"removed" BOOL
);

CREATE TABLE "pc_join_p" (
"product_container_id" INTEGER FOREIGN KEY, 
"product_id" INTEGER FOREIGN KEY
);

CREATE TABLE "product_containers" (
"id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , 
"name" VARCHAR, 
"parent" INTEGER FOREIGN_KEY, 
"storage_unit" INTEGER FOREIGN KEY, 
"three_month_amount" DOUBLE, 
"three_month_unit" VARCHAR
);

CREATE TABLE "products" (
"id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , "description" TEXT, 
"three_month_supply" INTEGER, 
"amount" DOUBLE, 
"unit" VARCHAR, 
"shelf_life" INTEGER, 
"barcode" VARCHAR);
