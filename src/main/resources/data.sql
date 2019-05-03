insert into logistic_manager.roles (id, created_at, deleted_at, updated_at, name) values (1, '2019-04-06 20:11:24', null, '2019-04-06 20:11:36', 'ROLE_ADMIN');
insert into logistic_manager.roles (id, created_at, deleted_at, updated_at, name) values (2, '2019-04-06 20:11:42', null, '2019-04-06 20:11:44', 'ROLE_MANAGER');
insert into logistic_manager.roles (id, created_at, deleted_at, updated_at, name) values (3, '2019-04-06 20:12:13', null, '2019-04-06 20:12:14', 'ROLE_DRIVER');
insert into logistic_manager.roles (id, created_at, deleted_at, updated_at, name) values (4, '2019-04-06 20:12:27', null, '2019-04-06 20:12:29', 'ROLE_CLIENT');

insert into logistic_manager.orders_statuses (id, created_at, deleted_at, updated_at, name) values (1, '2019-04-06 20:14:01', null, '2019-04-06 20:14:03', 'RECEIVED');
insert into logistic_manager.orders_statuses (id, created_at, deleted_at, updated_at, name) values (2, '2019-04-06 20:14:23', null, '2019-04-06 20:14:22', 'CANCELED');
insert into logistic_manager.orders_statuses (id, created_at, deleted_at, updated_at, name) values (3, '2019-04-06 20:14:52', null, '2019-04-06 20:14:53', 'IN_PROCESS');
insert into logistic_manager.orders_statuses (id, created_at, deleted_at, updated_at, name) values (4, '2019-04-06 20:15:24', null, '2019-04-06 20:15:25', 'UNDER_CONSIDERATION');

insert into logistic_manager.car_types (id, created_at, deleted_at, updated_at, name) values (1, '2019-04-13 17:49:33', null, '2019-04-13 17:49:33', 'TRACK');
insert into logistic_manager.car_types (id, created_at, deleted_at, updated_at, name) values (2, '2019-04-13 17:49:33', null, '2019-04-13 17:49:33', 'CAR');
insert into logistic_manager.car_types (id, created_at, deleted_at, updated_at, name) values (3, '2019-04-13 17:49:33', null, '2019-04-13 17:49:33', 'MINIVAN');

insert into logistic_manager.users (id, created_at, deleted_at, updated_at, first_name, last_name, login, password, phone_number, role_id) values (1, '2019-04-18 19:59:59', null, '2019-04-18 19:59:59', 'Admin', 'Admin', 'admin', '$2a$10$H2PqH5KUkeooBkWeLkKVzOwCcX.6F6Z/HBy4f//rg9/GHiBR8493u', '877777777777', 1);