---系统创建权限组，每个权限组可以配置多个操作权限，如
---1)"Adminstrator"权限组，具有超级权限
---2)"Users" 权限组，具有购买商品修改个人信息等权限

---用户表
create table users(
	username varchar(50) not null primary key,
	password varchar(50) not null,
	
	enabled boolean not null,
	
	salt varchar(25) not null
);
 
---权限表
create table authorities(
	username varchar(50) not null,
	authority varchar(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities(username,authority);

---权限组表
create table groups(
	id bigint primary key auto_increment,
	group_name varchar(50) not null
);

---权限组对应的权限表
create table group_authorities(
	group_id bigint not null,
	authority varchar(50) not null,
	constraint fk_group_authorities_group foreign key(group_id) references groups(id)
);

---组成员表
create table group_members (
  id bigint  primary key auto_increment, 
  username varchar(50) not null, 
  group_id bigint not null, 
  constraint fk_group_members_group foreign key(group_id) references groups(id)
 );
