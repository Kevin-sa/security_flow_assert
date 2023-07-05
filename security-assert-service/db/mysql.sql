create table flow_origin_data
(
    `id`                      bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `business`                varchar(255)                             NOT NULL DEFAULT '0' COMMENT 'business id',
    `api_host`                varchar(255) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT 'API HOST',
    `api_path`                varchar(1024) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'API PATH',
    `method`                  VARCHAR(10)                              NOT NULL DEFAULT '' COMMENT 'method',
    `headers_info`            json                                              DEFAULT NULL COMMENT 'headers',
    `request_payload`         json                                              DEFAULT NULL COMMENT 'request payload',
    `request_json_tree`       json                                              DEFAULT NULL COMMENT 'request payload json tree',
    `request_json_tree_hash`  VARCHAR(32)                              NOT NULL DEFAULT '' COMMENT 'hash',
    `response_body`           json                                              DEFAULT NULL COMMENT 'request body',
    `response_json_tree`      json                                              DEFAULT NULL COMMENT 'request body json tree',
    `response_json_tree_hash` VARCHAR(32)                              NOT NULL DEFAULT '' COMMENT 'hash',
    `data_source`             tinyint(1) DEFAULT 0 COMMENT 'source: 1 burpsuite;',
    `status`                  tinyint(1) DEFAULT 0 COMMENT 'status: 0 disable; 1 enable;',
    `version`                 tinyint(4) DEFAULT 0 COMMENT 'version',
    `api_hash`                VARCHAR(32)                              NOT NULL DEFAULT '' COMMENT 'hash',
    `create_time`             varchar(300)                             NOT NULL DEFAULT '0' COMMENT 'time',
    PRIMARY KEY (`id`),
    KEY                       idx_biz_exec (`business`, `status`),
    UNIQUE KEY `uniq_req_tree_hash` (request_json_tree_hash),
    UNIQUE KEY `uniq_resp_tree_hash` (response_json_tree_hash)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='flow origin data from plugin';

CREATE TABLE `assert_json_path_rule`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `business`    varchar(255)                             NOT NULL DEFAULT '0' COMMENT 'business id',
    `api_host`    varchar(255) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT 'API HOST',
    `api_path`    varchar(1024) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'API PATH',
    `data`        JSON COMMENT '规则具体内容',
    `type`        int(1) NOT NULL DEFAULT '1' COMMENT 'type',
    `status`      int(1) NOT NULL DEFAULT '1' COMMENT 'status: 0 disable; 1 enable;',
    `create_time` varchar(300)                             NOT NULL DEFAULT '0' COMMENT '创建时间',
    `update_time` varchar(300)                             NOT NULL DEFAULT '0' COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='assert json path rule';


INSERT INTO assert_json_path_rule (`business`, `api_host`, `api_path`, `data`, `type`, `status`)
VALUES ('kwai-shop', 's.kwaixiaodian.com', '/rest/app/tts/ks/seller/order/query/v2', '{"jsonPath": "$.result", "value": 1}', 1, 1);

INSERT INTO assert_json_path_rule (`business`, `api_host`, `api_path`, `data`, `type`, `status`)
VALUES ('kwai-shop', 's.kwaixiaodian.com', '/rest/app/tts/ks/seller/order/query/v2', '{"jsonPath": "$.total", "value": 0}', 2, 1);


-- insert into default rule: check response body tree diff with origin data
INSERT INTO assert_json_path_rule (`business`, `api_host`, `api_path`, `data`, `type`, `status`)
VALUES ('*', '', '', '', 0, 1);

CREATE TABLE `security_asset_result`
(
     `id`                           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
     `rule_id`                      bigint(20) unsigned NOT NULL,
     `flow_id`                      bigint(20) unsigned NOT NULL,
     `replay_flow_id`               bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT 'replay_flow_id',
     `response_body`                json  DEFAULT NULL COMMENT 'request body',
     `diff_value`                   json DEFAULT NULL COMMENT 'data compare diff value',
     `create_time`                  varchar(300)     NOT NULL DEFAULT '0' COMMENT '创建时间',
     PRIMARY KEY (`id`),
     FOREIGN KEY (flow_id) REFERENCES flow_origin_data(id),
     FOREIGN KEY (rule_id) REFERENCES assert_json_path_rule(id),
     FOREIGN KEY (replay_flow_id) REFERENCES assert_json_path_rule(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='assert result table';
