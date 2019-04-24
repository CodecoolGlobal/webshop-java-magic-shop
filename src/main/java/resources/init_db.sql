DROP TABLE IF EXISTS product CASCADE;
DROP TABLE IF EXISTS productCategory CASCADE;
DROP TABLE IF EXISTS supplier CASCADE;


create table if not exists productcategory
(
	id serial not null
		constraint productcategory_pkey
			primary key,
	name varchar(255) unique ,
	description varchar(255),
	department varchar(255)
);


create unique index if not exists productcategory_name_uindex
	on productcategory (name);

create table if not exists supplier
(
	id serial not null
		constraint supplier_pkey
			primary key,
	name varchar(255) unique ,
	description varchar(255)
);


create table if not exists product
(
	id integer not null
		constraint product_pkey
			primary key,
	name varchar(255),
	defaultprice double precision,
	currencystring varchar(255),
	productcategory varchar(255)
		constraint product_productcategory_name_fk
			references productcategory (name),
	supplier varchar(255)
		constraint product_supplier_name_fk
			references supplier (name),
	description varchar(255)
);

