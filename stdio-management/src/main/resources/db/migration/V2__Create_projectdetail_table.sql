CREATE TABLE project_detail(
    id BIGINT(20) NOT NULL PRIMARY KEY,
    project_id BIGINT(20) NOT NULL,
    status ENUM('ACTIVE', 'END'),
    time_start DATETIME,
    time_end DATETIME,
    technical VARCHAR(256)
);