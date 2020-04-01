create schema IF NOT EXISTS design_bureau;

create table department
(
    department_id          bigint auto_increment primary key,
    department_name        varchar(255)  not null
);
create table position_in_company
(
    position_id                 bigint auto_increment not null primary key,
    position_name                        varchar(255) not null
);

create table product
(
    id                 bigint auto_increment not null primary key,
    product_name               varchar(255) not null
);

create table user
(
    id                 bigint auto_increment not null primary key,
    enabled            bit not null,
    password           varchar(255),
    username           varchar(255)
);
create table user_role
(
    user_id            bigint auto_increment not null,
    authorities        varchar(255),
    constraint FK859n2jvi8ivhui0rl0esws6o foreign key (user_id) references user (id)
);
create table department_position
(
    department_id            bigint not null,
    position_id              bigint not null,
    constraint FKa5k28n8xar9ogeh38d1t7m03h foreign key (position_id) references position_in_company (position_id),
    constraint FKmqxa31b65gsvwrevblkvym9cw foreign key (department_id) references department (department_id)
);
create table employee
(
    id                          bigint auto_increment primary key,
    first_name                  varchar(255) not null,
    middle_name                 varchar(255) not null,
    last_name                   varchar(255) not null,
    phone_number                varchar(255) not null,
    department_department_id    bigint,
    position_position_id        bigint,
    constraint FKiojsy23dwh2w9geqmvn955dqp foreign key (department_department_id) references department (department_id),
    constraint FK5vxmq8mbfodf6ni285gmqss7 foreign key (position_position_id) references position_in_company (position_id)
);
create table drawing
(
    id                  bigint auto_increment not null primary key ,
    drawing_name                varchar(255),
    approved_id         bigint,
    checked_id          bigint,
    developed_id        bigint,
    product_id          bigint,
    constraint FKp5j1hg5oy34lsk885vy1v1gbf foreign key (approved_id) references employee (id),
    constraint FKi4xoib6pd93ae8frrhklwafbn foreign key (checked_id) references employee (id),
    constraint FKhhex284nsyxd50efs5q3r8y6j foreign key (developed_id) references employee (id),
    constraint FKafpiuwib3lby6pokiko1f50co foreign key (product_id) references product (id)

);






