create table assets
(
    id         bigint primary key generated by default as identity,
    issuer     text    not null,
    name       text    not null,
    unique (issuer, name),
    created_at timestamp with time zone default now()
);
