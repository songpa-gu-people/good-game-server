create table member (id bigint not null auto_increment, member_number VARCHAR(32) COMMENT '회원번호' not null, primary key (id)) engine=InnoDB;
alter table member add constraint uk_member unique (member_number);
create table member (id bigint not null auto_increment, member_number VARCHAR(32) COMMENT '회원번호' not null, primary key (id)) engine=InnoDB;
alter table member add constraint uk_member unique (member_number);
