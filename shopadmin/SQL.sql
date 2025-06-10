---------------------------
--어드민 표
---------------------------
create table admin(
	admin_id int primary key auto_increment,
	id varchar(20),
	pwd varchar(20),
	name varchar(20),
	email varchar(20));

---------------------------
--최상위 카테고리
---------------------------
CREATE TABLE topcategory(
	topcategory_id int PRIMARY KEY auto_increment,
	top_name varchar(20)
);

---------------------------
--하위 카테고리
---------------------------
create table subcategory(
	subcategory_id int primary key auto_increment,
	sub_name varchar(20),
	topcategory_id int,
	constraint fk_topcategory_subcategory foreign key(topcategory_id)
	references topcategory(topcategory_id)
);

insert into topcategory(top_name) values('상의');
insert into topcategory(top_name) values('하의');
insert into topcategory(top_name) values('신발');
insert into topcategory(top_name) values('액세서리');

insert into subcategory(sub_name, topcategory_id) values('티셔츠', 1);
insert into subcategory(sub_name, topcategory_id) values('가디건', 1);
insert into subcategory(sub_name, topcategory_id) values('점퍼', 1);
insert into subcategory(sub_name, topcategory_id) values('셔츠', 1);

insert into subcategory(sub_name, topcategory_id) values('치마', 2);
insert into subcategory(sub_name, topcategory_id) values('반바지', 2);
insert into subcategory(sub_name, topcategory_id) values('청바지', 2);
insert into subcategory(sub_name, topcategory_id) values('면바지', 2);

insert into subcategory(sub_name, topcategory_id) values('운동화', 3);
insert into subcategory(sub_name, topcategory_id) values('구두', 3);
insert into subcategory(sub_name, topcategory_id) values('슬리퍼', 3);
insert into subcategory(sub_name, topcategory_id) values('샌들', 3);

insert into subcategory(sub_name, topcategory_id) values('반지', 4);
insert into subcategory(sub_name, topcategory_id) values('목걸이', 4);
insert into subcategory(sub_name, topcategory_id) values('팔지', 4);
insert into subcategory(sub_name, topcategory_id) values('귀걸이', 4);

---------------------------
--색상 표
---------------------------
create table color(
	color_id int primary key auto_increment,
	color_name varchar(15));

---------------------------
--사이즈 표
---------------------------
create table size(
	size_id int primary key auto_increment,
	size varchar(10));

 

