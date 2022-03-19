create table if not exists main_categories
(
    id int primary key auto_increment,
    main_category varchar not null
--     category_id int not null
--     description varchar
);

create table if not exists sub_categories
(
    id int primary key auto_increment,
    sub_category varchar not null,
--     category_id int not null,
    main_category_id int not null
--     description varchar
);

create table if not exists categories
(
    id int primary key auto_increment,
--     category varchar not null,
    main_category_id int not null,
    sub_category_id int not null,
    type varchar not null,
    description varchar
);

-- alter table sub_categories add foreign key (category_id) references categories (id);
alter table categories add foreign key (main_category_id) references main_categories (id);
alter table categories add foreign key (sub_category_id) references sub_categories (id);
alter table sub_categories add foreign key (main_category_id) references main_categories (id);

create table if not exists payees
(
    id int primary key auto_increment,
    payee varchar not null,
    description varchar
);

create table if not exists gainers
(
    id int primary key auto_increment,
    gainer varchar not null,
    description varchar
);

create TABLE IF NOT EXISTS accounts
(
    id int primary key  auto_increment,
    name varchar not null,
    description varchar,
    deadline date,
    target double,
    init_balance double not null,
    actual_balance double not null
);

create table if not exists month_balances
(
    id int primary key  auto_increment,
    balance_date date not null,
    balance double not null,
    account_id int not null
);

alter table month_balances add foreign key (account_id) references accounts (id);

create table if not exists budgets
(
    id int primary key auto_increment,
    month int not null,
    year int not null,
    description varchar
);

create table if not exists budget_positions
(
    id int primary key auto_increment,
    category_id int not null,
    budget_id int not null,
    planned_amount double,
    actual_amount double,
    balance double,
    description varchar
);

alter table budget_positions add foreign key (category_id) references categories (id);
alter table budget_positions add foreign key (budget_id) references budgets (id);

CREATE TABLE IF NOT EXISTS transactions
(
    id int primary key auto_increment,
    transaction_date date,
    account_id int not null,
    amount double not null,
    category_id int not null,
    payee_id int not null,
    gainer_id int not null,
    description varchar,
    budget_position_id int
);

    alter table transactions add foreign key (account_id) references accounts (id);
    alter table transactions add foreign key (category_id) references categories (id);
    alter table transactions add foreign key (payee_id) references payees (id);
    alter table transactions add foreign key (gainer_id) references gainers (id);
    alter table transactions add foreign key (budget_position_id) references budget_positions (id);