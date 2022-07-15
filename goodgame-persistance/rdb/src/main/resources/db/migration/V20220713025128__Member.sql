CREATE TABLE IF NOT EXISTS member
(
    id               bigint         NOT NULL AUTO_INCREMENT COMMENT 'ID',
    member_number    varchar(32)    NOT NULL COMMENT '회원번호',

    created_by       varchar(64)    NULL COMMENT '생성자',
    created_at       datetime(6)    NOT NULL COMMENT '생성일시',
    modified_by      varchar(64)    NULL COMMENT '수정자',
    modified_at      datetime(6)    NOT NULL COMMENT '수정일시',
    version          bigint         NOT NULL COMMENT 'Data Version',

    CONSTRAINT pk_member PRIMARY KEY (id),
    CONSTRAINT uk_member UNIQUE KEY (member_number)
) ENGINE = InnoDB COMMENT ='회원';

