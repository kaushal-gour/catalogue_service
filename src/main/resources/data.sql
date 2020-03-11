insert into tb_product 	(id, category,size,color,price,product_code,stock_quantity) 
values (1, 'Shirt', 'XL', 'White', 100.00, 11, 23); 
insert into tb_seller 	(id, name,contact) 
values (1, 'S1', '9999999999');
insert into tb_product_seller 	(product_id, seller_id) 
values (1, 1);

insert into tb_product 	(id, category,size,color,price,product_code,stock_quantity) 
values (2, 'Pants', 'XL', 'Blue', 400.00, 12, 54); 
insert into tb_seller 	(id, name,contact) 
values (2, 'S2', '888888888');
insert into tb_product_seller 	(product_id, seller_id) 
values (2, 2);


insert into tb_product 	(id, category,size,color,price,product_code,stock_quantity) 
values (3, 'Shirt', 'L', 'BLACK', 200.00, 13, 45); 
insert into tb_product_seller 	(product_id, seller_id) 
values (3, 1);

 
insert into tb_seller 	(id, name,contact) 
values (3, 'S3', '5555555555');
insert into tb_product_seller 	(product_id, seller_id) 
values (1, 3);

insert into tb_product 	(id, category,size,color,price,product_code,stock_quantity) 
values (4, 'Shirt', 'M', 'White', 100.00, 14, 23); 
insert into tb_seller 	(id, name,contact) 
values (4, 'S4', '6666666666');


