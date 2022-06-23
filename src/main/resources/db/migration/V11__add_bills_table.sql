create table if not exists bills
(
    id int primary key auto_increment,
    day int not null,
    payee_id int not null,
    account_id int not null,
    budget_id int not null,
    hash int not null
);

alter table bills add foreign key (payee_id) references payees (id);
alter table bills add foreign key (account_id) references accounts (id);
alter table bills add foreign key (budget_id) references budgets (id);