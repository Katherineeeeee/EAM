--  初始化管理员账号  --
insert into managertable(mid, memail, mname, mpassword, mphone, mstatus)
values (0,'admin0@exmaple.com','admin0','000000','13572982859',1) on conflict(mid) DO NOTHING;

insert into managertable(mid, memail, mname, mpassword, mphone, mstatus)
values (1,'admin1@exmaple.com','admin1','000000','13572969252',1) on conflict(mid) DO NOTHING;

insert into managertable(mid, memail, mname, mpassword, mphone, mstatus)
values (2,'admin2@exmaple.com','admin2','000000','13572937729',1) on conflict(mid) DO NOTHING;

insert into managertable(mid, memail, mname, mpassword, mphone, mstatus)
values (3,'admin3@exmaple.com','admin3','000000','13572923275',1) on conflict(mid) DO NOTHING;

insert into managertable(mid, memail, mname, mpassword, mphone, mstatus)
values (4,'admin4@exmaple.com','admin4','000000','13572922317',1) on conflict(mid) DO NOTHING;

insert into managertable(mid, memail, mname, mpassword, mphone, mstatus)
values (5,'admin5@exmaple.com','admin5','000000','13572920673',1) on conflict(mid) DO NOTHING;

insert into managertable(mid, memail, mname, mpassword, mphone, mstatus)
values (6,'admin6@exmaple.com','admin6','000000','13572912523',1) on conflict(mid) DO NOTHING;

insert into managertable(mid, memail, mname, mpassword, mphone, mstatus)
values (7,'admin7@exmaple.com','admin7','000000','13572911781',1) on conflict(mid) DO NOTHING;

insert into managertable(mid, memail, mname, mpassword, mphone, mstatus)
values (8,'admin8@exmaple.com','admin8','000000','13572958573',1) on conflict(mid) DO NOTHING;

insert into managertable(mid, memail, mname, mpassword, mphone, mstatus)
values (9,'admin9@exmaple.com','admin9','000000','13572921537',1) on conflict(mid) DO NOTHING;

insert into managertable(mid, memail, mname, mpassword, mphone, mstatus)
values (10,'admin10@exmaple.com','admin10','000000','13572903897',1) on conflict(mid) DO NOTHING;

insert into managertable(mid, memail, mname, mpassword, mphone, mstatus)
values (11,'admin11@exmaple.com','admin11','000000','18691877312',1) on conflict(mid) DO NOTHING;

insert into managertable(mid, memail, mname, mpassword, mphone, mstatus)
values (12,'admin12@exmaple.com','admin12','000000','18691877310',1) on conflict(mid) DO NOTHING;

insert into managertable(mid, memail, mname, mpassword, mphone, mstatus)
values (13,'admin13@exmaple.com','admin13','000000','18691877026',1) on conflict(mid) DO NOTHING;

insert into managertable(mid, memail, mname, mpassword, mphone, mstatus)
values (14,'admin14@exmaple.com','admin14','000000','18691876962',1) on conflict(mid) DO NOTHING;

insert into managertable(mid, memail, mname, mpassword, mphone, mstatus)
values (15,'admin15@exmaple.com','admin15','000000','18691879856',1) on conflict(mid) DO NOTHING;

insert into managertable(mid, memail, mname, mpassword, mphone, mstatus)
values (16,'admin16@exmaple.com','admin16','000000','18691876752',1) on conflict(mid) DO NOTHING;

insert into managertable(mid, memail, mname, mpassword, mphone, mstatus)
values (17,'admin17@exmaple.com','admin17','000000','17391839523',1) on conflict(mid) DO NOTHING;

insert into managertable(mid, memail, mname, mpassword, mphone, mstatus)
values (18,'admin18@exmaple.com','admin18','000000','17391838515',1) on conflict(mid) DO NOTHING;

insert into managertable(mid, memail, mname, mpassword, mphone, mstatus)
values (19,'admin19@exmaple.com','admin19','000000','17391839569',1) on conflict(mid) DO NOTHING;

insert into managertable(mid, memail, mname, mpassword, mphone, mstatus)
values (20,'admin20@exmaple.com','admin20','000000','17391836778',1) on conflict(mid) DO NOTHING;

insert into managertable(mid, memail, mname, mpassword, mphone, mstatus)
values (21,'admin21@exmaple.com','admin21','000000','17391838832',1) on conflict(mid) DO NOTHING;

insert into managertable(mid, memail, mname, mpassword, mphone, mstatus)
values (22,'admin22@exmaple.com','admin22','000000','17391837732',1) on conflict(mid) DO NOTHING;

insert into managertable(mid, memail, mname, mpassword, mphone, mstatus)
values (23,'admin23@exmaple.com','admin23','000000','17391838832',1) on conflict(mid) DO NOTHING;

--  初始化用户账号  --
insert into usertable(uid, uemail, uname, upassword, ustatus)
values (0, 'user0@example.com', 'user0', '000000', 1) on conflict(uid) DO NOTHING;

insert into usertable(uid, uemail, uname, upassword, ustatus)
values (1, 'user1@example.com', 'user1', '000000', 1) on conflict(uid) DO NOTHING;

insert into usertable(uid, uemail, uname, upassword, ustatus)
values (2, 'user2@example.com', 'user2', '000000', 1) on conflict(uid) DO NOTHING;

insert into usertable(uid, uemail, uname, upassword, ustatus)
values (3, 'user3@example.com', 'user3', '000000', 1) on conflict(uid) DO NOTHING;

insert into usertable(uid, uemail, uname, upassword, ustatus)
values (4, 'user4@example.com', 'user4', '000000', 1) on conflict(uid) DO NOTHING;

insert into usertable(uid, uemail, uname, upassword, ustatus)
values (5, 'user5@example.com', 'user5', '000000', 1) on conflict(uid) DO NOTHING;

insert into usertable(uid, uemail, uname, upassword, ustatus)
values (6, 'user6@example.com', 'user6', '000000', 1) on conflict(uid) DO NOTHING;

insert into usertable(uid, uemail, uname, upassword, ustatus)
values (7, 'user7@example.com', 'user7', '000000', 1) on conflict(uid) DO NOTHING;

insert into usertable(uid, uemail, uname, upassword, ustatus)
values (8, 'user8@example.com', 'user8', '000000', 1) on conflict(uid) DO NOTHING;

insert into usertable(uid, uemail, uname, upassword, ustatus)
values (9, 'user9@example.com', 'user9', '000000', 1) on conflict(uid) DO NOTHING;

insert into usertable(uid, uemail, uname, upassword, ustatus)
values (10, 'user10@example.com', 'user10', '000000', 1) on conflict(uid) DO NOTHING;

insert into usertable(uid, uemail, uname, upassword, ustatus)
values (11, 'user11@example.com', 'user11', '000000', 1) on conflict(uid) DO NOTHING;

insert into usertable(uid, uemail, uname, upassword, ustatus)
values (12, 'user12@example.com', 'user12', '000000', 1) on conflict(uid) DO NOTHING;

insert into usertable(uid, uemail, uname, upassword, ustatus)
values (13, 'user13@example.com', 'user13', '000000', 1) on conflict(uid) DO NOTHING;

insert into usertable(uid, uemail, uname, upassword, ustatus)
values (14, 'user14@example.com', 'user14', '000000', 1) on conflict(uid) DO NOTHING;

insert into usertable(uid, uemail, uname, upassword, ustatus)
values (15, 'user15@example.com', 'user15', '000000', 1) on conflict(uid) DO NOTHING;

insert into usertable(uid, uemail, uname, upassword, ustatus)
values (16, 'user16@example.com', 'user16', '000000', 1) on conflict(uid) DO NOTHING;

insert into usertable(uid, uemail, uname, upassword, ustatus)
values (17, 'user17@example.com', 'user17', '000000', 1) on conflict(uid) DO NOTHING;

insert into usertable(uid, uemail, uname, upassword, ustatus)
values (18, 'user18@example.com', 'user18', '000000', 1) on conflict(uid) DO NOTHING;

insert into usertable(uid, uemail, uname, upassword, ustatus)
values (19, 'user19@example.com', 'user19', '000000', 1) on conflict(uid) DO NOTHING;

insert into usertable(uid, uemail, uname, upassword, ustatus)
values (20, 'user20@example.com', 'user20', '000000', 1) on conflict(uid) DO NOTHING;

insert into usertable(uid, uemail, uname, upassword, ustatus)
values (21, 'user21@example.com', 'user21', '000000', 1) on conflict(uid) DO NOTHING;

insert into usertable(uid, uemail, uname, upassword, ustatus)
values (22, 'user22@example.com', 'user22', '000000', 1) on conflict(uid) DO NOTHING;

insert into usertable(uid, uemail, uname, upassword, ustatus)
values (23, 'user23@example.com', 'user23', '000000', 1) on conflict(uid) DO NOTHING;

--  初始化资产信息  --
insert into propertytable(pid, pbrand, pmodel, pname, pspec, ptime, user_uid)
values (0, 'Lenovo', 'ThinkPad X1 Carbon', 'ThinkPad01', 'intel core i7-10710u 16G 2T固4K专业版', '2020-10-01 10:00:00', null) on conflict(pid) DO NOTHING;

insert into propertytable(pid, pbrand, pmodel, pname, pspec, ptime, user_uid)
values (1, 'Lenovo', 'ThinkPad X1 Carbon', 'ThinkPad02', 'intel core i7-10710u 16G 2T固4K专业版', '2020-10-01 10:00:00', null) on conflict(pid) DO NOTHING;

insert into propertytable(pid, pbrand, pmodel, pname, pspec, ptime, user_uid)
values (2, 'Lenovo', 'ThinkPad X1 Carbon', 'ThinkPad03', 'intel core i7-10710u 16G 2T固4K专业版', '2020-10-01 10:00:00', null) on conflict(pid) DO NOTHING;

insert into propertytable(pid, pbrand, pmodel, pname, pspec, ptime, user_uid)
values (3, 'Lenovo', 'ThinkPad X1 Carbon', 'ThinkPad04', 'intel core i7-10710u 16G 2T固4K专业版', '2020-10-01 10:00:00', null) on conflict(pid) DO NOTHING;

insert into propertytable(pid, pbrand, pmodel, pname, pspec, ptime, user_uid)
values (4, 'Lenovo', 'ThinkPad X1 Carbon', 'ThinkPad05', 'intel core i7-10710u 16G 2T固4K专业版', '2020-10-01 10:00:00', null) on conflict(pid) DO NOTHING;

insert into propertytable(pid, pbrand, pmodel, pname, pspec, ptime, user_uid)
values (5, 'Apple', 'MacBook Pro', 'MacBook Pro01', '十代i5 16G 512G 2.0GHz', '2020-10-02 12:00:00', null) on conflict(pid) DO NOTHING;

insert into propertytable(pid, pbrand, pmodel, pname, pspec, ptime, user_uid)
values (6, 'Apple', 'MacBook Pro', 'MacBook Pro02', '十代i5 16G 512G 2.0GHz', '2020-10-02 12:00:00', null) on conflict(pid) DO NOTHING;

insert into propertytable(pid, pbrand, pmodel, pname, pspec, ptime, user_uid)
values (7, 'Apple', 'MacBook Pro', 'MacBook Pro03', '十代i5 16G 512G 2.0GHz', '2020-10-02 12:00:00', null) on conflict(pid) DO NOTHING;

insert into propertytable(pid, pbrand, pmodel, pname, pspec, ptime, user_uid)
values (8, 'Apple', 'MacBook Pro', 'MacBook Pro04', '十代i5 16G 512G 2.0GHz', '2020-10-02 12:00:00', null) on conflict(pid) DO NOTHING;

insert into propertytable(pid, pbrand, pmodel, pname, pspec, ptime, user_uid)
values (9, 'Apple', 'MacBook Pro', 'MacBook Pro05', '十代i5 16G 512G 2.0GHz', '2020-10-02 12:00:00', null) on conflict(pid) DO NOTHING;

insert into propertytable(pid, pbrand, pmodel, pname, pspec, ptime, user_uid)
values (10, '惠普（HP）', 'M437dn A3', 'HP Printer01', '黑白激光支持自动双面打印', '2020-10-03 08:00:00', null) on conflict(pid) DO NOTHING;

insert into propertytable(pid, pbrand, pmodel, pname, pspec, ptime, user_uid)
values (11, '惠普（HP）', 'M437dn A3', 'HP Printer01', '黑白激光支持自动双面打印', '2020-10-03 08:00:00', null) on conflict(pid) DO NOTHING;

insert into propertytable(pid, pbrand, pmodel, pname, pspec, ptime, user_uid)
values (12, '惠普（HP）', 'M437dn A3', 'HP Printer01', '黑白激光支持自动双面打印', '2020-10-03 08:00:00', null) on conflict(pid) DO NOTHING;

insert into propertytable(pid, pbrand, pmodel, pname, pspec, ptime, user_uid)
values (13, '惠普（HP）', 'M437dn A3', 'HP Printer01', '黑白激光支持自动双面打印', '2020-10-03 08:00:00', null) on conflict(pid) DO NOTHING;

insert into propertytable(pid, pbrand, pmodel, pname, pspec, ptime, user_uid)
values (14, '惠普（HP）', 'M437dn A3', 'HP Printer01', '黑白激光支持自动双面打印', '2020-10-03 08:00:00', null) on conflict(pid) DO NOTHING;

-- 初始化申请信息
insert into applicationtable(aid, astatus, begintime, endtime, operation, reviewtime, manager_mid, property_pid, user_uid)
values (0, 0, '2020-10-03 08:00:00', null, 1, null, null, 1, 1)  on conflict(aid) DO NOTHING;

insert into applicationtable(aid, astatus, begintime, endtime, operation, reviewtime, manager_mid, property_pid, user_uid)
values (1, 0, '2020-10-03 09:00:00', null, 1, null, null, 2, 1)  on conflict(aid) DO NOTHING;

insert into applicationtable(aid, astatus, begintime, endtime, operation, reviewtime, manager_mid, property_pid, user_uid)
values (2, 0, '2020-10-03 10:00:00', null, 1, null, null, 3, 1)  on conflict(aid) DO NOTHING;

insert into applicationtable(aid, astatus, begintime, endtime, operation, reviewtime, manager_mid, property_pid, user_uid)
values (3, 0, '2020-10-03 11:00:00', null, 1, null, null, 4, 1)  on conflict(aid) DO NOTHING;

insert into applicationtable(aid, astatus, begintime, endtime, operation, reviewtime, manager_mid, property_pid, user_uid)
values (4, 0, '2020-10-03 12:00:00', null, 1, null, null, 5, 1)  on conflict(aid) DO NOTHING;

insert into applicationtable(aid, astatus, begintime, endtime, operation, reviewtime, manager_mid, property_pid, user_uid)
values (5, 0, '2020-10-03 13:00', null, 1, null, null, 6, 1)  on conflict(aid) DO NOTHING;

insert into applicationtable(aid, astatus, begintime, endtime, operation, reviewtime, manager_mid, property_pid, user_uid)
values (6, 0, '2020-10-04 08:00:00', null, 1, null, null, 7, 1)  on conflict(aid) DO NOTHING;

insert into applicationtable(aid, astatus, begintime, endtime, operation, reviewtime, manager_mid, property_pid, user_uid)
values (7, 0, '2020-10-04 09:00:00', null, 1, null, null, 8, 1)  on conflict(aid) DO NOTHING;

insert into applicationtable(aid, astatus, begintime, endtime, operation, reviewtime, manager_mid, property_pid, user_uid)
values (8, 0, '2020-10-04 10:00:00', null, 1, null, null, 9, 1)  on conflict(aid) DO NOTHING;

insert into applicationtable(aid, astatus, begintime, endtime, operation, reviewtime, manager_mid, property_pid, user_uid)
values (9, 0, '2020-10-04 11:00:00', null, 1, null, null, 10, 1)  on conflict(aid) DO NOTHING;

insert into applicationtable(aid, astatus, begintime, endtime, operation, reviewtime, manager_mid, property_pid, user_uid)
values (10, 0, '2020-10-04 12:00:00', null, 1, null, null, 11, 1)  on conflict(aid) DO NOTHING;

insert into applicationtable(aid, astatus, begintime, endtime, operation, reviewtime, manager_mid, property_pid, user_uid)
values (11, 0, '2020-10-04 13:00:00', null, 1, null, null, 12, 1)  on conflict(aid) DO NOTHING;

insert into applicationtable(aid, astatus, begintime, endtime, operation, reviewtime, manager_mid, property_pid, user_uid)
values (12, 0, '2020-10-05 08:00:00', null, 1, null, null, 13, 1)  on conflict(aid) DO NOTHING;

insert into applicationtable(aid, astatus, begintime, endtime, operation, reviewtime, manager_mid, property_pid, user_uid)
values (13, 0, '2020-10-05 09:00:00', null, 1, null, null, 14, 1)  on conflict(aid) DO NOTHING;
