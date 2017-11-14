create schema xMen default character set utf8;
use xMen;

create table dna(
id int(15) not null auto_increment,
dnaData varchar(200) not null,
isMutant tinyint(1) not null default 0,
primary key (id)
);