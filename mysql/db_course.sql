USE c;

-- 1. 角色表
CREATE TABLE course_role (
    role_id     INT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '编号',
    role_name   VARCHAR(64)  NULL COMMENT '名称',
    create_by   VARCHAR(64) DEFAULT '' NULL COMMENT '创建者',
    create_time DATETIME     NULL COMMENT '创建时间',
    update_by   VARCHAR(64) DEFAULT '' NULL COMMENT '更新者',
    update_time DATETIME     NULL COMMENT '更新时间',
    undock      INT NOT NULL DEFAULT 0 COMMENT '伪删除',
    state       INT NOT NULL DEFAULT 1 COMMENT '状态',
    remark      VARCHAR(500) NULL COMMENT '备注'
) COMMENT '角色表';

-- 2. 用户表
CREATE TABLE course_user (
    user_id     INT AUTO_INCREMENT COMMENT '编号' PRIMARY KEY,
    user_pic    VARCHAR(500)           NULL COMMENT '头像',
    user_name   VARCHAR(64)            NULL COMMENT '名称',
    user_gender VARCHAR(1)             NULL COMMENT '性别',
    user_birth  VARCHAR(64)            NULL COMMENT '生日',
    user_acc    VARCHAR(64)            NULL COMMENT '账号',
    user_pwd    VARCHAR(64)            NULL COMMENT '密码',
    user_role   INT                    NULL COMMENT '角色@',
    user_start  VARCHAR(64)            NULL COMMENT '入学年份',
    create_by   VARCHAR(64) DEFAULT '' NULL COMMENT '创建者',
    create_time DATETIME               NULL COMMENT '创建时间',
    update_by   VARCHAR(64) DEFAULT '' NULL COMMENT '更新者',
    update_time DATETIME               NULL COMMENT '更新时间',
    undock      INT DEFAULT 0  NOT NULL COMMENT '伪删除',
    state       INT DEFAULT 1  NOT NULL COMMENT '状态',
    remark      VARCHAR(500)           NULL COMMENT '备注',
    CONSTRAINT course_user_course_role_role_id_fk
        FOREIGN KEY (user_role) REFERENCES course_role (role_id)
) COMMENT '用户表';

-- 3. 菜单表
CREATE TABLE course_menu (
    menu_id      INT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '编号',
    menu_name    VARCHAR(64)  NULL COMMENT '名称',
    menu_icon    VARCHAR(64)  NULL COMMENT '图标',
    menu_keypath VARCHAR(64)  NULL COMMENT '标识符',
    create_by    VARCHAR(64) DEFAULT '' NULL COMMENT '创建者',
    create_time  DATETIME     NULL COMMENT '创建时间',
    update_by    VARCHAR(64) DEFAULT '' NULL COMMENT '更新者',
    update_time  DATETIME     NULL COMMENT '更新时间',
    undock       INT NOT NULL DEFAULT 0 COMMENT '伪删除',
    state        INT NOT NULL DEFAULT 1 COMMENT '状态',
    remark       VARCHAR(500) NULL COMMENT '备注'
) COMMENT '菜单表';

-- 4. 角色-菜单关联表
CREATE TABLE course_rm (
    rm_id       INT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '编号',
    rm_role     INT NULL COMMENT '角色',
    rm_menu     INT NULL COMMENT '菜单',
    create_by   VARCHAR(64) DEFAULT '' NULL COMMENT '创建者',
    create_time DATETIME     NULL COMMENT '创建时间',
    update_by   VARCHAR(64) DEFAULT '' NULL COMMENT '更新者',
    update_time DATETIME     NULL COMMENT '更新时间',
    undock      INT NOT NULL DEFAULT 0 COMMENT '伪删除',
    state       INT NOT NULL DEFAULT 1 COMMENT '状态',
    remark      VARCHAR(500) NULL COMMENT '备注'
) COMMENT '关联表';

-- 5. 课程时间表
CREATE TABLE course_time (
    time_id     INT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '编号',
    time_name   VARCHAR(64)  NULL COMMENT '名称',
    time_start  VARCHAR(64)  NULL COMMENT '开始时间',
    time_end    VARCHAR(64)  NULL COMMENT '结束时间',
    create_by   VARCHAR(64) DEFAULT '' NULL COMMENT '创建者',
    create_time DATETIME     NULL COMMENT '创建时间',
    update_by   VARCHAR(64) DEFAULT '' NULL COMMENT '更新者',
    update_time DATETIME     NULL COMMENT '更新时间',
    undock      INT NOT NULL DEFAULT 0 COMMENT '伪删除',
    state       INT NOT NULL DEFAULT 1 COMMENT '状态',
    remark      VARCHAR(500) NULL COMMENT '备注'
) COMMENT '课程时间表';

-- 6. 课程类型表
CREATE TABLE course_type (
    type_id     INT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '编号',
    type_name   VARCHAR(64)  NULL COMMENT '名称',
    create_by   VARCHAR(64) DEFAULT '' NULL COMMENT '创建者',
    create_time DATETIME     NULL COMMENT '创建时间',
    update_by   VARCHAR(64) DEFAULT '' NULL COMMENT '更新者',
    update_time DATETIME     NULL COMMENT '更新时间',
    undock      INT NOT NULL DEFAULT 0 COMMENT '伪删除',
    state       INT NOT NULL DEFAULT 1 COMMENT '状态',
    remark      VARCHAR(500) NULL COMMENT '备注'
) COMMENT '课程类型表';

-- 7. 课程表
CREATE TABLE course_list (
    list_id      INT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '编号',
    list_name    VARCHAR(64)  NULL COMMENT '名称',
    list_teacher INT          NULL COMMENT '老师@',
    list_grade   VARCHAR(64)  NULL COMMENT '学分',
    list_type    INT          NULL COMMENT '类型@',
    create_by    VARCHAR(64) DEFAULT '' NULL COMMENT '创建者',
    create_time  DATETIME     NULL COMMENT '创建时间',
    update_by    VARCHAR(64) DEFAULT '' NULL COMMENT '更新者',
    update_time  DATETIME     NULL COMMENT '更新时间',
    undock       INT NOT NULL DEFAULT 0 COMMENT '伪删除',
    state        INT NOT NULL DEFAULT 1 COMMENT '状态',
    remark       VARCHAR(500) NULL COMMENT '备注'
) COMMENT '课程表';

-- 8. 课程开设表
CREATE TABLE course_table (
    table_id    INT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '编号',
    table_time  INT NULL COMMENT '时间@',
    table_week  INT NULL COMMENT '星期（1~7)',
    table_list  VARCHAR(64)  NULL COMMENT '课程@',
    create_by   VARCHAR(64) DEFAULT '' NULL COMMENT '创建者',
    create_time DATETIME     NULL COMMENT '创建时间',
    update_by   VARCHAR(64) DEFAULT '' NULL COMMENT '更新者',
    update_time DATETIME     NULL COMMENT '更新时间',
    undock      INT NOT NULL DEFAULT 0 COMMENT '伪删除',
    state       INT NOT NULL DEFAULT 1 COMMENT '状态',
    remark      VARCHAR(500) NULL COMMENT '备注'
) COMMENT '课程开设';

-- 9. 学院表
CREATE TABLE course_academic (
    academic_id   INT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '编号',
    academic_name VARCHAR(64)  NULL COMMENT '名称',
    create_by     VARCHAR(64) DEFAULT '' NULL COMMENT '创建者',
    create_time   DATETIME     NULL COMMENT '创建时间',
    update_by     VARCHAR(64) DEFAULT '' NULL COMMENT '更新者',
    update_time   DATETIME     NULL COMMENT '更新时间',
    undock        INT NOT NULL DEFAULT 0 COMMENT '伪删除',
    state         INT NOT NULL DEFAULT 1 COMMENT '状态',
    remark        VARCHAR(500) NULL COMMENT '备注'
) COMMENT '学院';

-- 10. 专业表
CREATE TABLE course_profess (
    profess_id   INT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '编号',
    profess_name VARCHAR(64)  NULL COMMENT '名称',
    table_list   VARCHAR(64)  NULL COMMENT '课程@',
    create_by    VARCHAR(64) DEFAULT '' NULL COMMENT '创建者',
    create_time  DATETIME     NULL COMMENT '创建时间',
    update_by    VARCHAR(64) DEFAULT '' NULL COMMENT '更新者',
    update_time  DATETIME     NULL COMMENT '更新时间',
    undock       INT NOT NULL DEFAULT 0 COMMENT '伪删除',
    state        INT NOT NULL DEFAULT 1 COMMENT '状态',
    remark       VARCHAR(500) NULL COMMENT '备注'
) COMMENT '专业';

-- 11. 班级表
CREATE TABLE course_team (
    team_id    INT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '编号',
    team_name  VARCHAR(64)  NULL COMMENT '名称',
    create_by   VARCHAR(64) DEFAULT '' NULL COMMENT '创建者',
    create_time DATETIME     NULL COMMENT '创建时间',
    update_by   VARCHAR(64) DEFAULT '' NULL COMMENT '更新者',
    update_time DATETIME     NULL COMMENT '更新时间',
    undock      INT NOT NULL DEFAULT 0 COMMENT '伪删除',
    state       INT NOT NULL DEFAULT 1 COMMENT '状态',
    remark      VARCHAR(500) NULL COMMENT '备注'
) COMMENT '班级';

-- 12. 教室表
CREATE TABLE course_classroom (
    classroom_id   INT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '编号',
    classroom_name VARCHAR(64)  NULL COMMENT '名称',
    classroom_room VARCHAR(64)  NULL COMMENT '教室',
    classroom_site INT          NULL COMMENT '座位',
    create_by      VARCHAR(64) DEFAULT '' NULL COMMENT '创建者',
    create_time    DATETIME     NULL COMMENT '创建时间',
    update_by      VARCHAR(64) DEFAULT '' NULL COMMENT '更新者',
    update_time    DATETIME     NULL COMMENT '更新时间',
    undock         INT NOT NULL DEFAULT 0 COMMENT '伪删除',
    state          INT NOT NULL DEFAULT 1 COMMENT '状态',
    remark         VARCHAR(500) NULL COMMENT '备注'
) COMMENT '教室';

-- 13. 学生成绩表
CREATE TABLE course_grade (
    grade_id    INT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '编号',
    grade_user  INT NULL COMMENT '用户@',
    grade_list  VARCHAR(64)  NULL COMMENT '课程@',
    grade_score INT NULL COMMENT '分数',
    grade_grade INT NULL COMMENT '学分',
    create_by   VARCHAR(64) DEFAULT '' NULL COMMENT '创建者',
    create_time DATETIME     NULL COMMENT '创建时间',
    update_by   VARCHAR(64) DEFAULT '' NULL COMMENT '更新者',
    update_time DATETIME     NULL COMMENT '更新时间',
    undock      INT NOT NULL DEFAULT 0 COMMENT '伪删除',
    state       INT NOT NULL DEFAULT 1 COMMENT '状态',
    remark      VARCHAR(500) NULL COMMENT '备注'
) COMMENT '学生成绩';

-- 14. 班级课表
CREATE TABLE course_schedule (
    schedule_id    INT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '编号',
    schedule_class INT NULL COMMENT '班级@',
    schedule_time  INT NULL COMMENT '时间@',
    schedule_week  INT NULL COMMENT '星期（1~7)',
    schedule_list  INT NULL COMMENT '课程@',
    create_by   VARCHAR(64) DEFAULT '' NULL COMMENT '创建者',
    create_time DATETIME     NULL COMMENT '创建时间',
    update_by   VARCHAR(64) DEFAULT '' NULL COMMENT '更新者',
    update_time DATETIME     NULL COMMENT '更新时间',
    undock      INT NOT NULL DEFAULT 0 COMMENT '伪删除',
    state       INT NOT NULL DEFAULT 1 COMMENT '状态',
    remark      VARCHAR(500) NULL COMMENT '备注'
) COMMENT '班级课表';