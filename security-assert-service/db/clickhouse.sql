CREATE DATABASE security_flow_assert;

CREATE TABLE security_flow_assert.flow_data_record
(
    id UUID  DEFAULT generateUUIDv4(),
    base_info String,
    extend_info String,
    create_time Int64
)
ENGINE = MergeTree
ORDER BY (id, create_time)
PRIMARY KEY id;

INSERT INTO security_flow_assert.flow_data_record (create_time, base_info, extend_info)
VALUES (1631337600, '{"name": "Alice", "age": 30}', '{"status": "active"}');

INSERT INTO security_flow_assert.flow_data_record (create_time, base_info, extend_info)
VALUES (1631337600, '{"name": "Alice", "age": 30}', '{"status": "active"}');
