--ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'YOUR_ROOT_USER_PASSWORD';

create table `user` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`email` varchar(255) NOT NULL, 
	`password` varchar(255) NOT NULL, 
	`create_by` varchar(255) NOT NULL DEFAULT 'system_user',
    `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_modified_by` varchar(255),
    `last_modified_date` datetime,
	primary key (id),
	constraint username_unique unique (email) 
);

insert into user (id, email, password) values (1, 'fontestz@gmail.com', '$2a$10$4q2I1/BSLFOx64ji5oDz2uH.ZLOtQFi9N821ILDmjxO7wgt/gagnS');
insert into user (id, email, password) values (2, 'jmessias@gmail.com', '$2a$10$4q2I1/BSLFOx64ji5oDz2uH.ZLOtQFi9N821ILDmjxO7wgt/gagnS');
insert into user (id, email, password) values (3, 'iago@gmail.com', '$2a$10$4q2I1/BSLFOx64ji5oDz2uH.ZLOtQFi9N821ILDmjxO7wgt/gagnS');

create table `role` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`role` varchar(255),
	`create_by` varchar(255) NOT NULL DEFAULT 'system_user',
    `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_modified_by` varchar(255),
    `last_modified_date` datetime,
	primary key (id)
);

insert into role (id, role) values (1, 'USER');
insert into role (id, role) values (2, 'ADMIN');

create table `user_role` (
    `user_id` bigint(20) NOT NULL,
    `role_id` bigint(20) NOT NULL
);

insert into user_role (user_id, role_id) values (1, 1);
insert into user_role (user_id, role_id) values (1, 2);

