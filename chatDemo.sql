create database chatDB
create table user_table
(
userid varchar(8) primary key not null,
username varchar(10) not null,
sex varchar(2),
birthday datetime,
enrollday datetime default getdate(),
password varchar(10) not null


)
