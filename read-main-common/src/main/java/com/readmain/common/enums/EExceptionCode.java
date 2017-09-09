package com.readmain.common.enums;


import com.readmain.common.exception.CustomerException;

public enum EExceptionCode {

    SYS_ERROR(999999, "通信失败，请稍后再试！"),
    SYS_ACCOUNT_UNAVAILABLE(600001, "该账号不可用, 请联系系统管理员!"),
    SYS_LOGIN_INPUT_ERROR(600002, "输入账号或者密码错误, 请重新输入!"),
    ACCOUNT_UNAVAILABLE(500001, "您的账户已被冻结，请与客服取得联系！"),
    ACCOUNT_NOT_LOGIN(500002, "账户未登录"),
    SEND_CHECK_CODE_LIMIT(500003, "验证码发送次数已达上线"),
    IP_DISABLED(500004, "IP被禁用"),
    SEND_CHECK_CODE_FREQUENTLY(500005, "短信验证码发送过于频繁, 请稍后再试!"),
    ILLEGAL_ARGUMENT(500006, "非法的参数"),
    CHECK_CODE_ERROR(500007, "短信验证码错误"),
    CHECK_CODE_EXPIRED(500008, "短信验证码已失效, 请重新获取!"),
    EXCEED_MAX_CHECK_TIMES(500009, "短信验证码错误超过6次, 请重新获取"),
    ROLE_UNIQUE_CONSTRAINT(500010, "当前用户只能只能有一个角色"),
    REQUEST_NOT_ALLOW(500011, "不被支持的访问"),
    PCA_CANNOT_NULL(500012, "省市地区信息不能为空"),
    CANNOT_EDIT_CERT_INFO(500013, "认证已经通过, 您不能修改"),
    CANNOT_CONTINUE_BEFORE_CERT_PASSED(500014, "认证通过前您不能继续当前操作"),
    CONTACTS_EXISTS_VERIFY(500015, "联系人已经存在"),
    CONTACTS_EXISTS_NOT_VERIFY(500016, "联系人已经存在，等待审核"),
    ERROR_CONTACTS_INFO(500017, "填写信息无法通过认证, 请检查!"),
    HOUSE_CANNOT_RENT(500018, "当前房源状态不可租赁"),
    HOUSE_IN_RENTAL_STATUS(500019, "房屋处于租赁状态"),
    UNKNOWN_ERROR(500020, "系统错误请重新提交"),
    HOUSE_ORDER_NON_EXISTENT(500022, "当前订单不存在"),
    CANCEL_ORDER_FAIL(500023, "订单取消失败, 请稍后重试！"),
    AVAILABLE_MONEY_NOT_ENOUGH(500024, "账户可用余额不足"),
    INCONSISTENT_USER(500025, "用户信息不一致"),
    ORDER_PAY_FAIL(500026, "订单支付失败，请您重新支付"),
    USER_NOT_NULL(500027, "用户不能为空"),
    NOT_ALLOWED_TO_MODIFY(500028, "已被出租的房源不允许修改或下架"),
    NOT_FIND_THIS_HOUSE(500029, "很遗憾，未找到此房源"),
    INCONSISTENT_USER_AND_NOT_MODIFY(500030, "用户信息不一致,或房源状态不许修改"),
    NOT_AUDITED(500031, "很遗憾，此房源未经过审核不能上架"),
    NOT_ON_SHELVES_CAN_NOT_BE_OFF_THE_SHELF(500032, "很遗憾，未上架的房源是不能下架的哦"),
    PAY_ORDER_COMPLETED(500033, "该订单已完成支付，或者已失效"),
    INFORMATION_IS_NOT_AVAILABLE(500034, "身份信息不可用"),
    WAIT_FOR_FINAL_ROLE_CERT(500035, "您已经使用其他角色提交认证信息"),
    CERT_FAILED(500036, "输入信息有误，鉴权认证失败"),
    CANNOT_USE_BEFORE_BANK_CARD_CERT(500037, "该银行卡鉴权信息校验未通过"),
    ACCOUNT_IS_UNDERFUNDED(500038, "账户可用资金不足"),
    WITHDRAW_ERROR(500039, "提现失败, 未知原因"),
    WAIT_FOR_FINAL_ORDER_STATUE(500040, "订单当前状态不可支付, 请稍后重试"),
    CANNOT_REPAY_ORDER(500041, "订单已经支付完成, 无需重复支付"),
    CANNOT_SUBMIT_ESTATE_INFO(500042, "您当前无法提交房产认证"),
    USER_ACCOUNT_NOT_NULL(500043, "未找到该用户账户信息"),
    ORDER_HAS_BEEN_PROCESSED(500044, "此比订单已被处理"),
    ORDER_DOES_NOT_EXIST(500045, "此比订单不存在"),
    CALLBACK_CONTENT_IS_EMPTY(500046, "此比订单异步回调数据为空"),
    RECHARGE_WAS_NOT_FOUND(500047, "未找到该充值流水"),
    VOUCHERS_NOT_AVAILABLE(500048, "优惠券已过期"),
    RECHARGE_IS_NOT_YOUS(500049, "请选择自己名下优惠券"),
    NOT_FIND_VOUCHERS(500050, "未找到优惠券"),
    FUQIANLA_CALLBACK(500051, "付钱拉异步回调失败"),
    NOT_CHECK_IN_TIME(500052, "未到入住时间不能完成订单"),
    BACK_CODE_IS_EXIST(500053, "当前银行卡已经成功绑定"),
    CLEAN_SET_IS_NULL(500054, "清洁规则设定为空"),
    ARRIVAL_TIME(500055, "已到入住时间无法取消订单"),
    ACTIVITY_IS_NOT_EXIST(500056, "此打包服务不存在"),
    ACTIVITY_STATUS_NOT_ORDER(500057, "此活动状态不许预定"),
    ACTIVITY_ORDER_PEOPLE_IS_FULL(500058, "此活动已满额不可预定"),
    ACTIVITY_ORDER_NUMBER_IS_LIMITED(500059, "购买数量超出限制"),
    HOUSEKEEPER_HAS_BOUND(500060, "管家已绑定"),
    ORDERS_CANNOT_BE_MODIFIED(500061, "有未完成的订单，不能修改"),
    ACTIVITY_CAN_NOT_BE_SCHEDULED(500062, "已到出发日不能预定"),
    YOU_CAN_ENTRUST_TO_YOURSELF(500063, "您不能把该房源委托给自己"),
    TOKEN_IS_NULL(500064, "token为空或失效"),
    CAN_CANCEL_THE_ORDER_ON_CHECK_OUT_DATE(500065, "退房日不能取消订单"),
    REPEATED_WITHDRAWALS_ARE_NOT_ALLOWED(500066, "15天内不允许重复提现"),
    YOU_NOT_SPECIFY_YOUR_HOUSE(500067, "不能指定自己管理自己的房源"),
    PLEASE_RE_MODIFY_THE_PRICE(500067, "发现有已租的日期，请重新修改价格"),
    INCOMPLETE_INFORMATION(500068, "信息不完整，请补全后提交"),
    THE_USER_WAS_NOT_FOUND(500069, "未找到该用户"),
    NOT_FIND_CAR_TRAVEL(500070, "未找到此拼车行程"),
    BEFORE_THE_DEPARTURE_TIME_UNABLE_TO_COMPLETE_ORDERS(500071, "未到出发时间不能完成订单哦"),
    ORDER_IS_BEING_PAID(500072, "订单正在支付中,请稍后再试"),
    PLEASE_PAY_THE_DEPOSIT(500073, "请去缴纳押金，以便您顺利发布行程"),
    CAR_TRAVEL_IS_CANCEL(500074, "行程已过期或已取消"),
    CAR_TRAVEL_TIME_OF_DEPARTURE(500075, "已到出发时间"),
    CAR_TRAVEL_IS_UNDER_WAY(500076, "行程正在进行中，暂不能提现");





    private Integer code;
    private String message;

    EExceptionCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static EExceptionCode findByCode(Integer code) {
        for (EExceptionCode sCode : EExceptionCode.values()) {
            if (sCode.getCode().equals(code)) {
                return sCode;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CustomerException buildCustomerException() {
        return new CustomerException(this.getMessage(), this.getCode());
    }

}
