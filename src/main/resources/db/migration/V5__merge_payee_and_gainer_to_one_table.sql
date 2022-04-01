alter table payees add column role varchar ;

alter table transactions drop column payee_id;
alter table transactions drop column gainer_id;

alter table transactions add column is_paid_id int;
alter table transactions add column for_whom_id int;

alter table transactions add foreign key (is_paid_id) references payees(id);
alter table transactions add foreign key (for_whom_id) references payees(id);
