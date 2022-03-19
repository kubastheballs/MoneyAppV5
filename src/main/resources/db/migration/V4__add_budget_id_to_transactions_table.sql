alter table TRANSACTIONS add column budget_id int;
alter table TRANSACTIONS add foreign key (budget_id) references budgets (id);