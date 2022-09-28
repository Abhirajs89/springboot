insert into user_details(id,name,dob) values (10001,'Abhiraj',current_date());
insert into user_details(id,name,dob) values (10002,'Anuja',current_date());
insert into user_details(id,name,dob) values (10003,'Vivek',current_date());

insert into post(id,description,user_id) values (20001,'I want to learn JPA',10001);
insert into post(id,description,user_id) values (20002,'I want to learn Spring boot',10001);

insert into post(id,description,user_id) values (20003,'I want to learn AWS',10002);
insert into post(id,description,user_id) values (20004,'I want to learn Azure',10002);