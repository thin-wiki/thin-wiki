alter table article add column `status` tinyint(1) default 0 comment '状态，0-正常，-1 回收站中';