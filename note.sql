-- post
drop table if exists `note`;
create table `note`
(
    `id`                 bigint(20) not null,
    `content`            longtext default null,
    `created_by`         bigint(20) default null,
    `created_date`       datetime   default now(),
    `last_modified_by`   bigint(20) default null,
    `last_modified_date` datetime   default now() ON UPDATE now(),
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4;