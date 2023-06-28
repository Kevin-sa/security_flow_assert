create table flow_origin_data
(
    `id`                 bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `business`           varchar(255)                             NOT NULL DEFAULT '0' COMMENT 'business id',
    `api_host`           varchar(255) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT 'API HOST',
    `api_path`           varchar(1024) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'API PATH',
    `headers_info`       json                                              DEFAULT NULL COMMENT 'headers',
    `request_payload`    json                                              DEFAULT NULL COMMENT 'request payload',
    `request_json_tree`  json                                              DEFAULT NULL COMMENT 'request payload json tree',
    `response_body`      json                                              DEFAULT NULL COMMENT 'request body',
    `response_json_tree` json                                              DEFAULT NULL COMMENT 'request body json tree',
    `data_source`        tinyint(1) DEFAULT 0 COMMENT 'source: 1 burpsuite;',
    `status`             tinyint(1) DEFAULT 0 COMMENT 'status: 0 disable; 1 enable;',
    `version`            tinyint(4) DEFAULT 0 COMMENT 'version',
    `api_hash`           VARCHAR(32)                              NOT NULL DEFAULT '' COMMENT 'hash',
    `create_time`        varchar(300)                             NOT NULL DEFAULT '0' COMMENT 'time',
    PRIMARY KEY (`id`),
    KEY                  idx_biz_exec (`business`, `status`),
    UNIQUE KEY `uniq_hash` (api_hash)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='flow origin data from plugin';

CREATE TABLE `assert_json_path_rule`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `business`    varchar(255)                             NOT NULL DEFAULT '0' COMMENT 'business id',
    `api_host`    varchar(255) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT 'API HOST',
    `api_path`    varchar(1024) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'API PATH',
    `data`        JSON COMMENT '规则具体内容',
    `status`      int(1) NOT NULL DEFAULT '1' COMMENT 'status: 0 disable; 1 enable;',
    `create_time` varchar(300)                             NOT NULL DEFAULT '0' COMMENT '创建时间',
    `update_time` varchar(300)                             NOT NULL DEFAULT '0' COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='assert json path rule'
