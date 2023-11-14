create table tasks (is_done bit, id bigint not null, content varchar(255), primary key (id)) engine=InnoDB;
create table tasks_seq (next_val bigint) engine=InnoDB;
insert into tasks_seq values ( 1 );
