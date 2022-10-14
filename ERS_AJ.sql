create table employees (
id serial primary key,
first_name varchar(20) not null,
last_name varchar(20) not null,
user_name varchar(20) not null unique,
pass_word varchar(20) not null,
job_title varchar(20) default 'Employee',
status varchar(10) default 'Logged out'

);

create table tickets(
ticket_id serial primary key,
amount double not null,
description varchar(1000),
status varchar(10) default 'Pending',
creator_id int not null,

foreign key (creator_id) references employees


);

 