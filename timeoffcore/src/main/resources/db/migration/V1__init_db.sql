drop table if exists department;
drop table if exists task;
drop table if exists timerecord;
drop table if exists user;

create table department (
   id bigint not null auto_increment,
	created_date datetime not null,
	name varchar(200) not null,
	parent_department_fk bigint,
	primary key (id)
) engine=MyISAM;

create table task (
   id bigint not null auto_increment,
	description varchar(255) not null,
	status varchar(255) not null,
	string_id varchar(255) not null,
	user_fk bigint,
	primary key (id)
) engine=MyISAM;

create table timerecord (
   id bigint not null auto_increment,
	hours double precision not null,
	timestamp bigint not null,
	task_fk bigint not null,
	user_fk bigint not null,
	primary key (id)
) engine=MyISAM;

create table user (
   id bigint not null auto_increment,
	created_date datetime not null,
	first_name varchar(200) not null,
	last_name varchar(200) not null,
	login varchar(100) not null,
	password varchar(255) not null,
	role varchar(255) not null,
	status varchar(255) not null,
	department_fk bigint not null,
	primary key (id)
) engine=MyISAM;

alter table department
   add constraint department_parent_department_fk_const
   foreign key (parent_department_fk)
   references department (id);

alter table task
   add constraint task_user_fk_const
   foreign key (user_fk)
   references user (id);

alter table timerecord
   add constraint timerecord_task_fk_const
   foreign key (task_fk)
   references task (id);

alter table timerecord
   add constraint timerecord_user_fk_const
   foreign key (user_fk)
   references user (id);

alter table user
   add constraint user_department_fk_const
   foreign key (department_fk)
   references department (id);