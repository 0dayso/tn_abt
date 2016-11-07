DROP TABLE IF EXISTS `abt_refund_order`;
CREATE TABLE `abt_refund_order` (
  `id`  bigint(12) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `order_id`  bigint(12) UNSIGNED NOT NULL DEFAULT 0 COMMENT '途牛订单号' ,
  `flight_item_id`  bigint(12) UNSIGNED NOT NULL DEFAULT 0 COMMENT '请求ID（1个途牛订单拆分为多次占位，用以区分）' ,
  `session_id`  varchar(32) NOT NULL DEFAULT '' COMMENT '会话ID' ,
  `aop_order`  varchar(15) NOT NULL DEFAULT '' COMMENT '开放平台出票单号' ,
  `aop_refund_order`  varchar(15) NOT NULL DEFAULT '' COMMENT '开放平台退票单号' ,
  `received_segment`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否收到行程单 0：收到 1：未收到' ,
  `segment_position`  varchar(10) NOT NULL DEFAULT '' COMMENT '行程单位置 客人、票台、机场 . 未知 . 未打印. 配送中. 办理挂失. 其他' ,
  `refund_type`  varchar(5) NOT NULL DEFAULT '' COMMENT '退票类型' ,
  `refund_source`  char(1) NOT NULL DEFAULT '' COMMENT 'A=自动退票，M=客服退票' ,
  `remark`  varchar(500) NOT NULL DEFAULT '' COMMENT '退票备注' ,
  `status`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态标识，0=待退票,1=已退票，2=退票失败' ,
  `add_time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间' ,
  `update_time`  timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间' ,
  PRIMARY KEY (`id`),
  INDEX `session_id` (`session_id`),
  INDEX `order_flight` (`order_id`, `flight_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='退票记录表';


DROP TABLE IF EXISTS `abt_refund_item`;
CREATE TABLE `abt_refund_item` (
  `id`  bigint(12) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `refund_id`  bigint(12) UNSIGNED NOT NULL DEFAULT 0 COMMENT '退票单号，对应abt_refund_order表id' ,
  `order_id`  bigint(12) UNSIGNED NOT NULL DEFAULT 0 COMMENT '途牛订单号' ,
  `ticket_main_id`  bigint(12) UNSIGNED NOT NULL DEFAULT 0 COMMENT '出票记录主表id' ,
  `refund_item_type`  tinyint(1) NOT NULL DEFAULT 1 COMMENT '类型：1=全退' ,
  `leg`  varchar(10) NOT NULL DEFAULT '' COMMENT '航段信息（出发城市三字码/到达城市三字码）' ,
  `person_id`  bigint(12) NOT NULL DEFAULT 0 COMMENT '乘客fab系统id' ,
  `pnr_passenger_id`  bigint(12) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'abt_pnr_passenger表id' ,
  `real_refund_amount`  decimal(7,2) NOT NULL DEFAULT 0.00 COMMENT '接口实际操作后的退票费' ,
  `refund_amount`  decimal(7,2) NOT NULL DEFAULT 0.00 COMMENT '请求的原始退票费用' ,
  `commission_fee`  decimal(7,2) NOT NULL DEFAULT 0.00 COMMENT '请求的手续费' ,
  `ticket_no`  varchar(30) NOT NULL DEFAULT '' COMMENT '电子客票票号' ,
  `pnr`  varchar(100) NOT NULL DEFAULT '' COMMENT 'pnr' ,
  `remark`  varchar(500) NOT NULL DEFAULT '' COMMENT '退票备注' ,
  `status`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态标识，0=待退票,1=成功，2=失败' ,
  `add_time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间' ,
  `update_time`  timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间' ,
  PRIMARY KEY (`id`),
  INDEX `refund_id` (`refund_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='退票项目表';



DROP TABLE IF EXISTS `abt_job_item_execution_context`;
create TABLE `abt_job_item_execution_context`(
   `id` bigint(12) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键' ,
   `entry_id`  bigint(12) UNSIGNED NOT NULL COMMENT '任务的每个条目的唯一标识',
   `job_id` bigint(12) NOT NULL COMMENT '任务id abt_job_item_context主键',
   `retry_count` int NOT NULL DEFAULT 0 COMMENT '已重试次数',
   `add_time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间' ,
   `update_time`  timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保存任务每条记录执行情况';

DROP TABLE IF EXISTS `abt_job_item_context`;
create table `abt_job_item_context`(
  `id` bigint(12) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `job_name` varchar(100) NOT NULL DEFAULT '' COMMENT '任务名称',
  `max_retry_count` int NOT NULL DEFAULT 0 COMMENT '最大重试次数',
  `add_time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间' ,
  `update_time`  timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  INDEX `job_name` (`job_name`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务';
delete FROM abt_job_item_context where job_name='obtainTicketNo';
insert into abt_job_item_context(job_name, max_retry_count) VALUES('obtainTicketNo', 10);


DROP TABLE IF EXISTS `abt_ticket_request`;
CREATE TABLE `abt_ticket_request` (
  `id`  bigint(12) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `order_id`  bigint(12) UNSIGNED NOT NULL DEFAULT 0 COMMENT '途牛订单号' ,
  `session_id`  varchar(32) NOT NULL DEFAULT '' COMMENT '会话ID' ,
  `system_id`  tinyint(3) NOT NULL DEFAULT 0 COMMENT '系统ID' ,
  `flight_item_id`  bigint(12) UNSIGNED NOT NULL DEFAULT 0 COMMENT '请求ID（1个途牛订单拆分为多次占位，用以区分）' ,
  `vendor_id`  tinyint(3) NOT NULL DEFAULT 0 COMMENT '供应商ID，1=航信，6=携程，7=51，8=开放平台,9=航司直连' ,
  `aop_vendor_id`  varchar(15) NOT NULL DEFAULT '' COMMENT '开放平台供应商id' ,
  -- `pnr`  varchar(30) NOT NULL DEFAULT '' COMMENT 'PNR编号 或 外部子订单号（携程）' ,
  `status`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态标识，0=待出票,1=已出票，2=出票失败, 3= 已全部支付,未出票 4=存在支付失败的订单' ,
  `ticket_order_id` bigint(12) NOT NULL DEFAULT 0 COMMENT '开放平台订单号',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间' ,
  `update_time`  timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间' ,
  `call_back_status`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '订单回调状态:1已反馈,0未反馈',
  `callback` varchar(300) DEFAULT '' COMMENT '出票反馈地址',
  PRIMARY KEY (`id`),
  INDEX `order_flight` (`order_id`, `flight_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出票请求表';


DROP TABLE IF EXISTS `abt_ticket_main`;
CREATE TABLE `abt_ticket_main` (
  `id`  bigint(12) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `request_id`  bigint(12) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'abt_ticket_request表ID' ,
  `pnr`  varchar(64) NOT NULL DEFAULT '' COMMENT 'pnr' ,
  `new_pnr`  varchar(64) NOT NULL DEFAULT '' COMMENT '换编出票，记录新pnr' ,
  `ticket_no`  varchar(30) NOT NULL DEFAULT '' COMMENT '电子客票票号' ,
  `passenger_name`  varchar(20) NOT NULL DEFAULT '' COMMENT '乘客姓名' ,
  `passenger_type`  tinyint(1) NOT NULL DEFAULT 1 COMMENT '乘客类型' ,
  `flight_no`  varchar(30) NOT NULL DEFAULT '' COMMENT '航班号' ,
  `rph`  tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '航班序号' ,
  `seat_code`  varchar(5) NOT NULL DEFAULT '' COMMENT '航班舱位' ,
  `org_airport_code`  varchar(5) NOT NULL DEFAULT '' COMMENT '出发机场三字码' ,
  `dst_airport_code`  varchar(5) NOT NULL DEFAULT '' COMMENT '目的机场三字码' ,
  `org_time`  datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '出发时间' ,
  `org_price`  decimal(7,2) NOT NULL DEFAULT 0.00 COMMENT '票面价' ,
  `floor_price`  decimal(7,2) NOT NULL DEFAULT 0.00 COMMENT '结算价' ,
  `fuel_surcharge`  decimal(5,2) NOT NULL DEFAULT 0.00 COMMENT '燃油附加费' ,
  `airport_tax`  decimal(5,2) NOT NULL DEFAULT 0.00 COMMENT '机场建设费' ,
  `status`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态:0=已出票，1=已退票' ,
  `add_time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建日期' ,
  `update_time`  timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '记录修改日期' ,
  `solution_id`  varchar(100) NOT NULL DEFAULT '' COMMENT '真实出票的供应商id' ,
  `solution_name`  varchar(100) NOT NULL DEFAULT '' COMMENT '真实出票的供应商名' ,
  `person_id`  bigint(12) NOT NULL DEFAULT 0 COMMENT 'fab系统人员id' ,
  `sale_price`  decimal(7,2) NOT NULL DEFAULT 0.00 COMMENT '售卖价' ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出票记录主表';



DROP TABLE IF EXISTS `abt_booking_request`;
CREATE TABLE `abt_booking_request` (
  `id`  bigint(12) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `order_id`  bigint(12) UNSIGNED NOT NULL DEFAULT 0 COMMENT '途牛订单号' ,
  `session_id`  varchar(32) NOT NULL DEFAULT '' COMMENT '会话ID' ,
  `system_id`  tinyint(3) NOT NULL DEFAULT 0 COMMENT '系统ID' ,
  `vendor_id`  tinyint(3) NOT NULL DEFAULT 0 COMMENT '供应商ID，0：ibe 1：ibe+ 2:abe 3:policy 4：待定，5：廉价航司 6：携程' ,
  `flight_item_id`  bigint(12) UNSIGNED NOT NULL DEFAULT 0 COMMENT '请求ID（1个途牛订单拆分为多次占位，用以区分）' ,
  `status`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态:0=占位中，1=占位成功，2=占位失败，3=已取消' ,
  `back_time`  datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '返回时间' ,
  `call_back_status`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '(该字段已弃用)订单回调状态:0占位中,1已反馈,2未反馈' ,
  `add_time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间' ,
  `update_time`  timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间' ,
  PRIMARY KEY (`id`),
  INDEX `add_time` (`add_time`),
  INDEX `order_item` (`order_id`, `flight_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='占位请求表';


DROP TABLE IF EXISTS `abt_pnr_main`;
CREATE TABLE `abt_pnr_main` (
  `id`  bigint(12) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `request_id`  bigint(12) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'abt_booking_request表ID' ,
  `order_id`  bigint(12) UNSIGNED NOT NULL DEFAULT 0 COMMENT '途牛订单号' ,
  `pnr`  varchar(64) NOT NULL DEFAULT '' COMMENT 'PNR编号 或 外部订单号（携程/51）' ,
  `out_order_id`  varchar(64) NOT NULL DEFAULT '' COMMENT '外部订单号（PNR纬度）' ,
  `out_main_order_id`  varchar(64) NOT NULL DEFAULT '' COMMENT '外部主单号（依赖订单号、请求纬度）' ,
  `external_no`  varchar(64) NOT NULL DEFAULT '' COMMENT '携程置收款号' ,
  `time_limit`  datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '清位时间' ,
  `act_time_limit`  datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '实际保留时限' , -- ?
  `policy_id`  varchar(30) NOT NULL DEFAULT '' COMMENT '政策号' ,
  `policy_type`  tinyint(3) NOT NULL DEFAULT 0 COMMENT '政策类型，1：普通政策，2：特殊政策，3：伪直连，4：其他',
  `dfp_action_code`  varchar(30) NOT NULL DEFAULT '' COMMENT '政策活动号' ,
  `air_company_code`  varchar(10) NOT NULL DEFAULT '' COMMENT '航司大编码' ,
  `ticketing_carrier`  varchar(30) NOT NULL DEFAULT '' COMMENT '航司直连实际出票航司' ,
  `order_status_display`  varchar(30) NOT NULL DEFAULT 'NA' COMMENT '订单状态显示Unsubmit 待提交Processing 确认中ProcessingConfirmed 待出票Cancel 已取消PrintedTicket 已出票RefundApplying 退票申请中 FullRetire 已退票PartRetire 部分退票SendingTicket 已送票NA 未知' ,
  `status`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态：0=初始，1=已取消，2=取消失败，3=已出票，4=出票失败，5=出票中' ,
  `pay_status`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '支付状态，0=待支付，1=支付中，2=支付成功，3=支付失败',
  `add_time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间' ,
  `update_time`  timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间' ,
  `vendor_id`  tinyint(3) NOT NULL DEFAULT 0 COMMENT '供应商ID，1：航信 6：携程 7：51book 8：开放平台 9：航司直连',
  PRIMARY KEY (`id`),
  INDEX `add_time` (`add_time`),
  INDEX `request_id` (`request_id`),
  INDEX `order_pnr` (`order_id`,`pnr`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='占位PNR纬度表';


DROP TABLE IF EXISTS `abt_pnr_passenger`;
CREATE TABLE `abt_pnr_passenger` (
  `id`  bigint(12) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `pnr_id`  bigint(12) UNSIGNED NOT NULL DEFAULT 0 COMMENT '关联abt_pnr_main id' ,
  `full_name`  varchar(100) NOT NULL DEFAULT '' COMMENT '乘客姓名' ,
  `book_name`  varchar(100) NOT NULL DEFAULT '' COMMENT '占位名' ,
  `first_name`  varchar(50) NOT NULL DEFAULT '' COMMENT '乘客的姓' ,
  `last_name`  varchar(50) NOT NULL DEFAULT '' COMMENT '乘客的名' ,
  `passenger_type`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '乘客类型  1成人 2儿童 3婴儿' ,
  `ref_psg_id` bigint(12) UNSIGNED NOT NULL DEFAULT 0 COMMENT '关联abt_pnr_passenger id, 用于备注婴儿跟随的成人' ,
  `identity_type`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '证件类型 1 身份证2 护照3 军官证..' ,
  `person_id`  bigint(12) NOT NULL DEFAULT 0 COMMENT 'fab系统人员id' ,
  `birth_date`  varchar(10) NOT NULL DEFAULT '' COMMENT '出生日期 yyyyMMdd' ,
  `gender`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '乘客的性别' ,
  `status`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态：0=初始，1=已取消，2=取消失败' ,
  `add_time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间' ,
  `update_time`  timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间' ,
  PRIMARY KEY (`id`),
  INDEX `pnr_id` (`pnr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='PNR乘客信息';


DROP TABLE IF EXISTS `abt_pnr_flight`;
CREATE TABLE `abt_pnr_flight` (
  `id`  bigint(12) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `pnr_id`  bigint(12) UNSIGNED NOT NULL DEFAULT 0 COMMENT '关联abt_pnr_main id' ,
  `flight_no`  varchar(30) NOT NULL DEFAULT '' COMMENT '航班号' ,
  `rph`  tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '航班序号' ,
  `seat_code`  varchar(5) NOT NULL DEFAULT '' COMMENT '航班舱位' ,
  `plane_type`  varchar(30) NOT NULL DEFAULT '' COMMENT '机型' ,
  `org_city_code`  varchar(5) NOT NULL DEFAULT '' COMMENT '出发城市三字码' ,
  `dst_city_code`  varchar(5) NOT NULL DEFAULT '' COMMENT '目的城市三字码' ,
  `org_city_name`  varchar(30) NOT NULL DEFAULT '' COMMENT '出发城市名称' ,
  `dst_city_name`  varchar(30) NOT NULL DEFAULT '' COMMENT '目的城市名称' ,
  `org_airport_code`  varchar(5) NOT NULL DEFAULT '' COMMENT '出发机场三字码' ,
  `dst_airport_code`  varchar(5) NOT NULL DEFAULT '' COMMENT '目的机场三字码' ,
  `org_airport_name`  varchar(30) NOT NULL DEFAULT '' COMMENT '出发机场名称' ,
  `dst_airport_name`  varchar(30) NOT NULL DEFAULT '' COMMENT '抵达机场名称' ,
  `org_airport_terminal`  varchar(10) NOT NULL DEFAULT '' COMMENT '出发航站楼名称' ,
  `dst_airport_terminal`  varchar(10) NOT NULL DEFAULT '' COMMENT '抵达航站楼名称' ,
  `org_time`  datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '出发时间' ,
  `dst_time`  datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '抵达时间' ,
  `add_time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间' ,
  `update_time`  timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间' ,
  PRIMARY KEY (`id`),
  INDEX `pnr_id` (`pnr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='PNR航班信息';


DROP TABLE IF EXISTS `abt_pnr_price`;
CREATE TABLE `abt_pnr_price` (
  `id`  bigint(12) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `pnr_id`  bigint(12) UNSIGNED NOT NULL DEFAULT 0 COMMENT '关联abt_booking_pnr id' ,
  `passenger_type`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '乘客类型，1=成人，2=儿童，3=婴儿',
  `price_type`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '价格类型，1=成人，2=儿童，3=婴儿',
  `org_price`  decimal(7,2) NOT NULL DEFAULT 0.00 COMMENT '票面价' ,
  `sale_price`  decimal(7,2) NOT NULL DEFAULT 0.00 COMMENT '售卖价' ,
  `floor_price`  decimal(7,2) NOT NULL DEFAULT 0.00 COMMENT '结算价' ,
  `back_bate`  decimal(7,2) NOT NULL DEFAULT 0.00 COMMENT '返点' ,
  `back_cash`  decimal(7,2) NOT NULL DEFAULT 0.000 COMMENT '返现' ,
  `invoice_cost`  decimal(7,2) NOT NULL DEFAULT 0.000 COMMENT '手续费' ,
  `fuel_surcharge`  decimal(5,2) NOT NULL DEFAULT 0.00 COMMENT '燃油附加费' ,
  `airport_tax`  decimal(5,2) NOT NULL DEFAULT 0.00 COMMENT '机场建设费' ,
  `sale_id`  bigint(12) NOT NULL DEFAULT 0 COMMENT '销控平台的调价区间id' ,
  `add_time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间' ,
  `update_time`  timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间' ,
  PRIMARY KEY (`id`),
  INDEX `pnr_id` (`pnr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='PNR价格信息';


DROP TABLE IF EXISTS `abt_book_contact`;
CREATE TABLE `abt_book_contact` (
  `request_id`  bigint(12) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'abt_booking_request表ID' ,
  `name`  varchar(50) NOT NULL DEFAULT '' COMMENT '联系人姓名' ,
  `tel`  varchar(50) NOT NULL DEFAULT '' COMMENT '联系人电话' ,
  `email`  varchar(100) NOT NULL DEFAULT '' COMMENT '联系人邮箱' ,
  PRIMARY KEY (`request_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='占位出票联系人信息';


DROP TABLE IF EXISTS `abt_aop_policy`;
CREATE TABLE `abt_aop_policy` (
  `id`  bigint(12) UNSIGNED NOT NULL COMMENT '主键,政策ID' ,
  `vendor_id`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '供应商ID 8：开放平台' ,
  `ticketing_office_no`  varchar(50) NOT NULL DEFAULT '' COMMENT '出票商office号' ,
  `ticketing_account_no`  varchar(100) NOT NULL DEFAULT '' COMMENT '出票商的账号' ,
  `ticketing_account_pwd`  varchar(100) NOT NULL DEFAULT '' COMMENT '出票商的密码' ,
  `supplier_office_no`  varchar(50) NOT NULL DEFAULT '' COMMENT '供应商office账号' ,
  `change_pnr_flag`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否换编码  0不换编 1换编' ,
  `policy_effective_flag`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '政策是否失效标示 0有效 1无效' ,
  `actual_ticketing_office_no`  varchar(50) NOT NULL DEFAULT '' COMMENT '实际出票商office号' ,
  `sub_vendor_id`  int(11) NOT NULL DEFAULT 0 COMMENT '开放平台真实供应商ID' ,
  `sub_vendor_name`  varchar(100) NOT NULL DEFAULT '' COMMENT '开放平台真实供应商名' ,
  `del_flag`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否有效 0有效 1无效' ,
  `add_time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间' ,
  `update_time`  timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间' ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET = utf8 COMMENT = '国内机票政策信息记录表';



DROP TABLE IF EXISTS `abt_back_meal`;
CREATE TABLE `abt_back_meal` (
  `id`  bigint(12) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `airline_company`  varchar(5) NOT NULL DEFAULT '' COMMENT '航司码' ,
  `airline_company_name`  varchar(20) NOT NULL DEFAULT '' COMMENT '航司名' ,
  `cabin`  varchar(200) NOT NULL DEFAULT '' COMMENT '限定舱位，多个 /S/Y/T/' ,
  `journey_type`  tinyint(1) NOT NULL DEFAULT 1 COMMENT '航程类型, 1=单程，2=往返' ,
  `passenger_type`  tinyint(1) NOT NULL DEFAULT 1 COMMENT '乘客类型, 1=成人，2=儿童' ,
  `ticket_date_start`  varchar(10) NOT NULL DEFAULT '' COMMENT '有效的出票日期-开始, YYYY-MM-dd' ,
  `ticket_date_end`  varchar(10) NOT NULL DEFAULT '' COMMENT '有效的出票日期-结束, YYYY-MM-dd' ,
  `departure_date_start`  varchar(10) NOT NULL DEFAULT '' COMMENT '有效的起飞日期-开始, YYYY-MM-dd' ,
  `departure_date_end`  varchar(10) NOT NULL DEFAULT '' COMMENT '有效的起飞日期-结束, YYYY-MM-dd' ,
  `freight_channel`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '运价渠道，0=中航信' ,
  `rule_type`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '所属类型，0=非商旅优选，1=商旅优选' ,
  `city_option_type`  tinyint(1) NOT NULL DEFAULT 1 COMMENT '出发到达城市限定类型：1=适用，2=非适用' ,
  `city_options`  varchar(500) NOT NULL DEFAULT '' COMMENT '适用出发到达城市对' ,
  `status`  tinyint(2) NOT NULL DEFAULT 0 COMMENT '状态，1=已保存，2=生效，3=失效，4=删除' ,
  `operator_id`  int(10) NOT NULL DEFAULT 0 COMMENT 'operator_id' ,
  `operator_name`  varchar(120) NOT NULL DEFAULT '' COMMENT 'operator_name' ,
  `add_time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间' ,
  `update_time`  timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间' ,
  PRIMARY KEY (`id`),
  INDEX `airline_company` (`airline_company`),
  INDEX `update_time` (`update_time`),
  INDEX `ticket_date_end` (`ticket_date_end`),
  INDEX `departure_date_end` (`departure_date_end`)
) ENGINE=InnoDB DEFAULT CHARSET = utf8 COMMENT = '退改签规则条件筛选表';


DROP TABLE IF EXISTS `abt_back_meal_rule`;
CREATE TABLE `abt_back_meal_rule` (
  `id`  bigint(12) UNSIGNED NOT NULL COMMENT '主键, abt_back_meal表id' ,
  `re_rule`	varchar(200) NOT NULL DEFAULT '' COMMENT '退票规则：起飞前字符串' ,
  `re_calculate_type`  tinyint(1) NOT NULL DEFAULT 1 COMMENT '换算规则：1=FD运价，2=舱等全价，3=票面价' ,
  `re_remark`	varchar(200) NOT NULL DEFAULT '' COMMENT '退票规则备注' ,
  `same_rule`	varchar(200) NOT NULL DEFAULT '' COMMENT '同舱改期规则：起飞前字符串' ,
  `same_calculate_type`  tinyint(1) NOT NULL DEFAULT 1 COMMENT '换算规则：1=FD运价，2=舱等全价，3=票面价' ,
  `same_remark`	varchar(200) NOT NULL DEFAULT '' COMMENT '同舱改期规则备注' ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET = utf8 COMMENT = '退改签规则详情表';

DROP TABLE IF EXISTS `abt_back_meal_alert_record`;
CREATE TABLE `abt_back_meal_alert_record` (
  `id`  bigint(12) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `back_meal_id`	bigint(12) UNSIGNED NOT NULL DEFAULT 0 COMMENT '退改规则ID' ,
  `alert_type`  tinyint(2) NOT NULL DEFAULT 0 COMMENT '警告类型，0=失效前1天告警，1=失效后1小时告警' ,
  `add_time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间' ,
  PRIMARY KEY (`id`),
  INDEX `back_meal_id` (`back_meal_id`)
) ENGINE=InnoDB DEFAULT CHARSET = utf8 COMMENT = '退改签规则提醒记录表';


DROP TABLE IF EXISTS `abt_change_order`;
CREATE TABLE `abt_change_order` (
  `id`  bigint(12) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `order_id`  bigint(12) UNSIGNED NOT NULL DEFAULT 0 COMMENT '途牛订单号' ,
  `flight_item_id`  bigint(12) UNSIGNED NOT NULL DEFAULT 0 COMMENT '请求ID（1个途牛订单拆分为多次占位，用以区分）' ,
  `session_id`  varchar(32) NOT NULL DEFAULT '' COMMENT '会话ID' ,
  `aop_order`  varchar(15) NOT NULL DEFAULT '' COMMENT '开放平台出票单号' ,
  `aop_change_order`  varchar(15) NOT NULL DEFAULT '' COMMENT '开放平台改期单号' ,
  `change_type`  varchar(5) NOT NULL DEFAULT '' COMMENT '改期类型，1=改名, 2=改证件, 3=同舱改期, 4=升舱换开, 5=航变改升, 9=其他' ,
  `failure_time`  timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '改期单失效时间' ,
  `remark`  varchar(500) NOT NULL DEFAULT '' COMMENT '改期备注' ,
  `total_amount`  decimal(7,2) NOT NULL DEFAULT 0.00 COMMENT '改期涉及总金额' ,
  `ctrip_order_id`  varchar(64) NOT NULL DEFAULT '' COMMENT '携程订单号' ,
  `status`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态标识，0=待改期,1=已改期，2=改期失败' ,
  `add_time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间' ,
  `update_time`  timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间' ,
  PRIMARY KEY (`id`),
  INDEX `session_id` (`session_id`),
  INDEX `order_flight` (`order_id`, `flight_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='改期记录表';


DROP TABLE IF EXISTS `abt_change_item`;
CREATE TABLE `abt_change_item` (
  `id`  bigint(12) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `change_id`  bigint(12) UNSIGNED NOT NULL DEFAULT 0 COMMENT '改期单号，对应abt_change_order表id' ,
  `order_id`  bigint(12) UNSIGNED NOT NULL DEFAULT 0 COMMENT '途牛订单号' ,
  `ticket_main_id`  bigint(12) UNSIGNED NOT NULL DEFAULT 0 COMMENT '出票记录主表id' ,
  `ticket_no`  varchar(30) NOT NULL DEFAULT '' COMMENT '待改期电子客票票号' ,
  `change_fee`  decimal(7,2) NOT NULL DEFAULT 0.00 COMMENT '更改费' ,
  `upgradeFee`  decimal(7,2) NOT NULL DEFAULT 0.00 COMMENT '升舱费' ,
  `commissionFee`  decimal(7,2) NOT NULL DEFAULT 0.00 COMMENT '手续费' ,
  `pnr`  varchar(100) NOT NULL DEFAULT '' COMMENT '原pnr' ,
  `new_pnr`  varchar(100) NOT NULL DEFAULT '' COMMENT '新PNR' ,
  `leg`  varchar(10) NOT NULL DEFAULT '' COMMENT '航段信息（出发城市三字码/到达城市三字码）' ,
  `person_id`  bigint(12) NOT NULL DEFAULT 0 COMMENT '乘客fab系统id' ,
  `pnr_passenger_id`  bigint(12) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'abt_pnr_passenger表id' ,
  `new_person_id`  bigint(12) NOT NULL DEFAULT 0 COMMENT '新的乘客fab系统id' ,
  `new_segments`  varchar(2000) NOT NULL DEFAULT '' COMMENT '新航段信息（json字符串）' ,
  `remark`  varchar(500) NOT NULL DEFAULT '' COMMENT '改期备注' ,
  `add_time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间' ,
  `update_time`  timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间' ,
  PRIMARY KEY (`id`),
  INDEX `change_id` (`change_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='改期项目表';


DROP TABLE IF EXISTS `abt_tracer_log`;
CREATE TABLE `abt_tracer_log` (
  `id`  bigint(12) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `trace_id`	varchar(32) NOT NULL DEFAULT '' COMMENT '跟踪uuid，对应一次完整的业务处理请求' ,
  `level`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '层级：0=action,1=cmd' ,
  `biz_action`  varchar(32) NOT NULL DEFAULT '' COMMENT '定位一次外部的请求或完整的执行功能' ,
  `biz_cmd`  varchar(32) NOT NULL DEFAULT '' COMMENT 'action执行中的某一个模块记录' ,
  `biz_type`  varchar(100) NOT NULL DEFAULT '' COMMENT '区分cmd的不同内容' ,
  `exec_start`  datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '开始' ,
  `exec_end`  datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '结束' ,
  `exec_duration`  int(10) NOT NULL DEFAULT 0 COMMENT '执行时长，单位毫秒' ,
  `exec_status`  tinyint(2) NOT NULL DEFAULT 0 COMMENT '执行结果状态：0=成功，1=失败，2=部分失败' ,
  `error_code`  varchar(10) NOT NULL DEFAULT '' COMMENT '异常码' ,
  `error_desc`  varchar(1000) NOT NULL DEFAULT '' COMMENT '异常信息' ,
  `session_id`  varchar(32) NOT NULL DEFAULT '' COMMENT '会话id' ,
  `vendor_id`  varchar(5) NOT NULL DEFAULT '' COMMENT '供应商id' ,
  `system_id`  varchar(5) NOT NULL DEFAULT '' COMMENT '系统id' ,
  `order_id`  varchar(32) NOT NULL DEFAULT '' COMMENT '订单id' ,
  `add_time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间' ,
  PRIMARY KEY (`id`) ,
  INDEX `trace_id` (`trace_id`),
  INDEX `order_id` (`order_id`),
  INDEX `session_id` (`session_id`),
  INDEX `add_time` (`add_time`)
) ENGINE=InnoDB DEFAULT CHARSET = utf8 COMMENT = '业务跟踪日志';

DROP TABLE IF EXISTS `abt_tracer_log_detail`;
CREATE TABLE `abt_tracer_log_detail` (
  `id`  bigint(12) UNSIGNED NOT NULL COMMENT '主键' ,
  `input_param`  text NOT NULL COMMENT '输入报文' ,
  `output_result`  text NOT NULL COMMENT '输出报文' ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET = utf8 COMMENT = '业务跟踪日志-报文记录';