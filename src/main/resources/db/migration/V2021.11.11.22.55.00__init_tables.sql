CREATE SCHEMA IF NOT EXISTS budget;


create table if not exists budget.currency
(
    mnemonic varchar not null primary key,
    name varchar not null
);


create table if not exists budget.currency_exchange_rate
(
    from_currency_mnemonic varchar not null
        constraint fk_currency_exchange_rate_from references budget.currency (mnemonic),
    to_currency_mnemonic   varchar not null
        constraint fk_currency_exchange_rate_to references budget.currency (mnemonic),
    exchange_rate           real    not null,
    primary key (from_currency_mnemonic, to_currency_mnemonic)
);


create table if not exists budget.wallet
(
    id integer not null generated by default as identity primary key,
    name varchar not null unique,
    sort_order integer not null,
    currency_mnemonic varchar not null constraint fk_wallet_currency_id references budget.currency(mnemonic)
);


create table if not exists budget.category
(
    id integer not null generated by default as identity primary key,
    name varchar not null unique,
    sort_order integer not null,
    income boolean not null default false,
    parent_id integer constraint fk_category_parent_id references budget.category
);


create table if not exists budget.record
(
    id integer not null generated by default as identity primary key,
    creation_date timestamp with time zone not null default current_timestamp,
    description varchar,
    income boolean not null default false,
    wallet_id integer not null constraint fk_record_wallet_id references budget.wallet,
    category_id integer not null constraint fk_record_category_id references budget.category
)
