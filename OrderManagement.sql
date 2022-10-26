CREATE DATABASE IF NOT EXISTS management;

USE management;

drop table orders;
drop table users;
drop table admin;


-- `users`
create table if not exists `users`
(
`userID` INT not null auto_increment primary key,
`first_name` varchar(256) not null,
`last_name` varchar(256) not null,
`username` varchar(256) not null,
`password` varchar(256) not null,
`register_date` date not null,
`employeeID` INT not null
) comment '`users`';





-- `orders`
create table if not exists `orders`
(
`id` INT not null auto_increment primary key,
`order_number` varchar(256) not null,
`product` varchar(256) not null,
`unit_price` DECIMAL(5,2) not null,
`quantity` INT not null,
`product_type` varchar(256) not null,
`customer` varchar(256) not null,
`factory` varchar(256) not null,
`order_received_date` DATE not null,
`order_finished` varchar(256) not null,
`order_shipped` varchar(256) not null,
CONSTRAINT order_finished_check CHECK ( order_finished IN ('YES','NO') ),
CONSTRAINT order_shipped_check CHECK ( order_finished IN ('YES','NO') )
) ;

CREATE TABLE admin(
  adminID INT AUTO_INCREMENT PRIMARY KEY ,
  username VARCHAR(10),
  password VARCHAR(12),
  employeeID INT
);





insert into `users` (`userID`, `first_name`, `last_name`, `username`, `password`, `register_date`, `employeeID`) values ('1', 'Rachel', 'Rollin', 'kxf310', 'abc123!@', '2002-11-09', '000001');
insert into `users` (`userID`, `first_name`, `last_name`, `username`, `password`, `register_date`, `employeeID`) values ('2', 'Tom', 'Rollin', 'yhe229', 'abc123!@', '2002-02-06', '000002');
insert into `users` (`userID`, `first_name`, `last_name`, `username`, `password`, `register_date`, `employeeID`) values ('3', 'Rachel', 'Rollin', 'nsp230', 'abc123!@', '2000-11-07', '000003');
insert into `users` (`userID`, `first_name`, `last_name`, `username`, `password`, `register_date`, `employeeID`) values ('4', 'Flora', 'Rollin', 'jpg728', 'abc123!@', '2011-01-26', '000004');
insert into `users` (`userID`, `first_name`, `last_name`, `username`, `password`, `register_date`, `employeeID`) values ('5', 'Echo', 'Sanif', 'qxp834', 'abc123!@', '2012-02-14', '000005');
insert into `users` (`userID`, `first_name`, `last_name`, `username`, `password`, `register_date`, `employeeID`) values ('6', 'Tom', 'Edward', 'qrs462', 'abc123!@', '2022-12-19', '000006');
insert into `users` (`userID`, `first_name`, `last_name`, `username`, `password`, `register_date`, `employeeID`) values ('7', 'Daniel', 'Sanif', 'wec937', 'abc123!@', '2002-10-09', '000007');
insert into `users` (`userID`, `first_name`, `last_name`, `username`, `password`, `register_date`, `employeeID`) values ('8', 'Flora', 'Rollin', 'mri776', 'abc123!@', '2002-12-01', '000008');
insert into `users` (`userID`, `first_name`, `last_name`, `username`, `password`, `register_date`, `employeeID`) values ('9', 'Daniel', 'Kent', 'ppj615', 'abc123!@', '2022-10-05', '000009');
insert into `users` (`userID`, `first_name`, `last_name`, `username`, `password`, `register_date`, `employeeID`) values ('10', 'Daniel', 'Musk', 'kma801', 'abc123!@', '2001-02-17', '000010');










insert into `orders` (`id`, `order_number`, `product`, `unit_price`, `quantity`, `product_type`, `customer`, `factory`, `order_received_date`, `order_finished`, `order_shipped`) values ('1', 'UKPD-0007', 'jeans', '04.68', '1617', 'jeans', 'Roots', 'Factory-Z ', '2012-01-12', 'YES', 'YES');
insert into `orders` (`id`, `order_number`, `product`, `unit_price`, `quantity`, `product_type`, `customer`, `factory`, `order_received_date`, `order_finished`, `order_shipped`) values ('2', 'FIYG-2889', 'bikini bottom', '04.57', '0208', 'swimwear', 'Nike', 'Factory-X ', '2011-07-19', 'NO', 'YES');
insert into `orders` (`id`, `order_number`, `product`, `unit_price`, `quantity`, `product_type`, `customer`, `factory`, `order_received_date`, `order_finished`, `order_shipped`) values ('3', 'NPQI-0078', 'hoodie', '27.94', '0386', 'hoodie', 'Adidas', 'Factory-Y ', '2021-07-27', 'NO', 'NO');
insert into `orders` (`id`, `order_number`, `product`, `unit_price`, `quantity`, `product_type`, `customer`, `factory`, `order_received_date`, `order_finished`, `order_shipped`) values ('4', 'CMYH-9075', 'sweater pants', '28.86', '0647', 'sweater pants', 'Pink Lily', 'Factory-X ', '2000-09-12', 'YES', 'YES');
insert into `orders` (`id`, `order_number`, `product`, `unit_price`, `quantity`, `product_type`, `customer`, `factory`, `order_received_date`, `order_finished`, `order_shipped`) values ('5', 'HJOM-4557', 'bikini bottom', '19.62', '1235', 'swimwear', 'Time Machine', 'Factory-X ', '2020-09-28', 'YES', 'YES');
insert into `orders` (`id`, `order_number`, `product`, `unit_price`, `quantity`, `product_type`, `customer`, `factory`, `order_received_date`, `order_finished`, `order_shipped`) values ('6', 'VTQB-8009', 'skirt', '22.45', '1417', 'skirt', 'TinyTiny', 'Factory-Z ', '2020-02-00', 'NO', 'NO');
insert into `orders` (`id`, `order_number`, `product`, `unit_price`, `quantity`, `product_type`, `customer`, `factory`, `order_received_date`, `order_finished`, `order_shipped`) values ('7', 'BAEI-3336', 'T-shirt', '25.33', '1356', 'T-shirt', 'PLT', 'Factory-Y ', '2001-02-18', 'NO', 'NO');
insert into `orders` (`id`, `order_number`, `product`, `unit_price`, `quantity`, `product_type`, `customer`, `factory`, `order_received_date`, `order_finished`, `order_shipped`) values ('8', 'EBJR-0131', 'sarong', '10.52', '0321', 'swimwear', 'Lucas', 'Factory-Y ', '2002-02-24', 'NO', 'NO');
insert into `orders` (`id`, `order_number`, `product`, `unit_price`, `quantity`, `product_type`, `customer`, `factory`, `order_received_date`, `order_finished`, `order_shipped`) values ('9', 'LCJH-4690', 'bikini bottom', '07.52', '1438', 'swimwear', 'Roots', 'Factory-X ', '2000-06-21', 'YES', 'YES');
insert into `orders` (`id`, `order_number`, `product`, `unit_price`, `quantity`, `product_type`, `customer`, `factory`, `order_received_date`, `order_finished`, `order_shipped`) values ('10', 'ODZL-5974', 'jeans', '18.60', '1402', 'jeans', '437', 'Factory-Z ', '2021-08-05', 'YES', 'NO');
insert into `orders` (`id`, `order_number`, `product`, `unit_price`, `quantity`, `product_type`, `customer`, `factory`, `order_received_date`, `order_finished`, `order_shipped`) values ('11', 'ENQV-8026', 'sweater', '03.86', '0167', 'sweater', 'Missguided', 'Factory-Y ', '2001-06-29', 'YES', 'YES');
insert into `orders` (`id`, `order_number`, `product`, `unit_price`, `quantity`, `product_type`, `customer`, `factory`, `order_received_date`, `order_finished`, `order_shipped`) values ('12', 'XTSU-2288', 'bikini top', '00.79', '1125', 'swimwear', 'Time Machine', 'Factory-X ', '2021-04-25', 'NO', 'NO');
insert into `orders` (`id`, `order_number`, `product`, `unit_price`, `quantity`, `product_type`, `customer`, `factory`, `order_received_date`, `order_finished`, `order_shipped`) values ('13', 'PUUA-9606', 'hoodie', '16.86', '0624', 'hoodie', 'PLT', 'Factory-Z ', '2011-07-14', 'YES', 'NO');
insert into `orders` (`id`, `order_number`, `product`, `unit_price`, `quantity`, `product_type`, `customer`, `factory`, `order_received_date`, `order_finished`, `order_shipped`) values ('14', 'RCHK-4631', 'sweater', '06.88', '0286', 'sweater', 'Pink Lily', 'Factory-Z ', '2001-01-17', 'YES', 'NO');
insert into `orders` (`id`, `order_number`, `product`, `unit_price`, `quantity`, `product_type`, `customer`, `factory`, `order_received_date`, `order_finished`, `order_shipped`) values ('15', 'KAGI-7028', 'swimsuit', '07.85', '1969', 'swimwear', 'Lucas', 'Factory-Y ', '2002-09-10', 'YES', 'NO');
insert into `orders` (`id`, `order_number`, `product`, `unit_price`, `quantity`, `product_type`, `customer`, `factory`, `order_received_date`, `order_finished`, `order_shipped`) values ('16', 'YIXO-6696', 'bikini bottom', '20.96', '0053', 'swimwear', '437', 'Factory-X ', '2002-03-14', 'YES', 'NO');
insert into `orders` (`id`, `order_number`, `product`, `unit_price`, `quantity`, `product_type`, `customer`, `factory`, `order_received_date`, `order_finished`, `order_shipped`) values ('17', 'MVGW-1223', 'jacket', '28.52', '0702', 'jacket', 'Adidas', 'Factory-Z ', '2010-06-04', 'YES', 'YES');
insert into `orders` (`id`, `order_number`, `product`, `unit_price`, `quantity`, `product_type`, `customer`, `factory`, `order_received_date`, `order_finished`, `order_shipped`) values ('18', 'TKDJ-3136', 'bikini bottom', '10.11', '1013', 'swimwear', 'Roots', 'Factory-X ', '2011-08-18', 'NO', 'NO');
insert into `orders` (`id`, `order_number`, `product`, `unit_price`, `quantity`, `product_type`, `customer`, `factory`, `order_received_date`, `order_finished`, `order_shipped`) values ('19', 'DZPK-5746', 'skirt', '04.06', '0307', 'skirt', 'Adidas', 'Factory-Y ', '2011-06-15', 'YES', 'YES');
insert into `orders` (`id`, `order_number`, `product`, `unit_price`, `quantity`, `product_type`, `customer`, `factory`, `order_received_date`, `order_finished`, `order_shipped`) values ('20', 'SFIB-3323', 'sweater pants', '09.61', '1865', 'sweater pants', 'Adidas', 'Factory-Z ', '2002-01-21', 'NO', 'NO');




INSERT INTO admin(username, password, employeeID) VALUES ('admin','admin',123456);


