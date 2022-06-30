alter table TRANSACTIONS drop column FOR_WHOM_ID;
alter table TRANSACTIONS add column gainer_id int;
alter table TRANSACTIONS add foreign key (gainer_id) references PAYEES(ID);