login as root;
create DATABASE brand_new;
grant all privileges on brand_new.* to 'runjia'@localhost identified by '123456';
FLUSH PRIVILEGES;

create table brand (
	`id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`name` varchar(30) NOT NULL DEFAULT '' COMMENT 'brand name',
	`creator` varchar(30) NOT NULL DEFAULT '' COMMENT 'creator name',
	`business` varchar(200) NOT NULL DEFAULT '' COMMENT 'business',
	`head_quarter` VARCHAR(30) NOT NULL COMMENT 'head quarter address',
	`create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌表';
create UNIQUE index uniq_name_brand_idx on brand(`name`);

create table shop (
	`id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`brand_id` bigint(12) not null COMMENT 'brand id',
	`name` varchar(60) NOT NULL DEFAULT '' COMMENT 'shop name',
	`address` varchar(150) NOT NULL DEFAULT '' COMMENT 'shop address',
	`manager` varchar(100) NOT NULL DEFAULT '' COMMENT 'manager name',
	`status` TINYINT NOT NULL DEFAULT 0 COMMENT 'shop status, 0非在营,1在营,2不明确,3计划关闭',
	`create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (`id`),
	FOREIGN KEY (`brand_id`) REFERENCES brand(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='店铺表';
create UNIQUE index uniq_name_shop_idx on shop(`name`);
