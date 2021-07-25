-- user
drop table if exists `user`;
create table `user`
(
    `id`                 bigint       not null,
    `account`            varchar(20)  not null,
    `password`           varchar(255) not null,
    `last_login_time`    datetime default null,
    `created_date`       datetime default now(),
    `last_modified_date` datetime default now() ON UPDATE now(),
    primary key (`id`),
    unique key idx_account (account)
) engine = innodb
  default charset = utf8mb4;

insert into user (id, account, created_date, last_login_time, last_modified_date, password)
values (1000, 'admin', '2020-12-21 21:12:20', '2020-12-21 21:12:27', '2020-12-21 21:12:27',
        '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');


-- article_column
drop table if exists `post_column`;
create table `post_column`
(
    `id`                 bigint(20)   not null,
    `path`               varchar(255) not null,
    `title`              varchar(255) not null,
    `content`            longtext    default null,
    `sharable`           varchar(10) default null,
    `created_date`       datetime    default now(),
    `last_modified_date` datetime    default now() ON UPDATE now(),
    primary key (`id`),
    unique key idx_path (path)
) engine = innodb
  default charset = utf8mb4;

-- post
drop table if exists `post`;
create table `post`
(
    `id`                 bigint      not null,
    `title`              varchar(20) not null,
    `content`            longtext    default null,
    `parent_id`          bigint      default 0,
    `column_id`          bigint      not null,
    `sharable`           varchar(10) default null,
    `status`             tinyint     default 0 comment '状态，0-正常，-1 回收站中',
    `version`            int         default 0,
    `created_date`       datetime    default now(),
    `last_modified_date` datetime    default now() ON UPDATE now(),
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4;

-- post_history
drop table if exists `post_history`;
create table `post_history`
(
    `id`           bigint(20)  not null,
    `post_id`      bigint(20)  not null,
    `title`        varchar(20) not null,
    `content`      longtext    default null,
    `parent_id`    bigint(20)  default 0,
    `column_id`    bigint(20)  not null,
    `sharable`     varchar(10) default null,
    `version`      int         default 1,
    `created_date` datetime    default now(),
    primary key (`id`),
    index idx_post_id (post_id)
) engine = innodb
  default charset = utf8mb4;

-- post_view_history
drop table if exists `post_view_history`;
create table `post_view_history`
(
    `id`                 bigint not null,
    `post_id`            bigint not null,
    `view_count`         int    not null,
    `created_date`       datetime default now(),
    `last_modified_date` datetime default now() ON UPDATE now(),
    primary key (`id`),
    index idx_post_id (post_id)
) engine = innodb
  default charset = utf8mb4;

-- storage
drop table if exists `storage`;
create table `storage`
(
    `id`                 bigint(20)   not null,
    `name`               varchar(100) not null,
    `description`        varchar(255) default null,
    `work_type`          tinyint(1)   not null,
    `ref_storage_type`   tinyint(1)   default null,
    `ref_storage_id`     bigint(20)   default null,
    `main_storage_id`    bigint(20)   default null,
    `writable`           bit(1)       default 1,
    `created_date`       datetime     default now(),
    `last_modified_date` datetime     default now() ON UPDATE now(),
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4;

-- local_storage
drop table if exists `local_storage`;
create table `local_storage`
(
    `id`                 bigint(20)   not null,
    `name`               varchar(100) not null,
    `description`        varchar(255) default null,
    `base_path`          varchar(255) default null,
    `created_date`       datetime     default now(),
    `last_modified_date` datetime     default now() ON UPDATE now(),
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4;

-- github_storage
drop table if exists `github_storage`;
create table `github_storage`
(
    `id`                 bigint(20)   not null,
    `name`               varchar(100) not null,
    `description`        varchar(255) default null,
    `token`              varchar(255) default null,
    `owner`              varchar(100) default null,
    `repo`               varchar(100) default null,
    `branch`             varchar(50)  default null,
    `base_path`          varchar(255) default null,
    `created_date`       datetime     default now(),
    `last_modified_date` datetime     default now() ON UPDATE now(),
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4;

-- gitee_storage
drop table if exists `gitee_storage`;
create table `gitee_storage`
(
    `id`                 bigint(20)   not null,
    `name`               varchar(100) not null,
    `description`        varchar(255) default null,
    `token`              varchar(255) default null,
    `owner`              varchar(100) default null,
    `repo`               varchar(100) default null,
    `branch`             varchar(50)  default null,
    `base_path`          varchar(255) default null,
    `created_date`       datetime     default now(),
    `last_modified_date` datetime     default now() ON UPDATE now(),
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4;

-- storage_file
drop table if exists `storage_file`;
create table `storage_file`
(
    `id`                 bigint(20) not null,
    `target_id`          bigint(20) not null,
    `original_file_name` varchar(255) default null,
    `suffix`             varchar(20)  default null,
    `file_size`          bigint(20)   default null,
    `content_type`       varchar(100) default null,
    `relative_path`      varchar(255) default null,
    `storage_id`         bigint(20)   default null,
    `created_date`       datetime     default now(),
    `last_modified_date` datetime     default now() ON UPDATE now(),
    primary key (`id`),
    index idx_target_id (target_id)
) engine = innodb
  default charset = utf8mb4;

-- app_config
drop table if exists `app_config`;
create table `app_config`
(
    `id`                 bigint(20)  not null,
    `type`               varchar(50) not null,
    `key`                varchar(50) not null,
    `value`              longtext     default null,
    `description`        varchar(255) default null,
    `created_by`         bigint(20)   default null,
    `created_date`       datetime     default now(),
    `last_modified_date` datetime     default now() ON UPDATE now(),
    primary key (`id`),
    unique key idx_type_key (`type`, `key`)
) engine = innodb
  default charset = utf8mb4;
