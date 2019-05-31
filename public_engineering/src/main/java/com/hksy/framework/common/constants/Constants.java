package com.hksy.framework.common.constants;

/**
 * @Auther: cel
 * @Date: 2018/11/5 14:25
 * @Description:
 */
public class Constants {
    /**
     * Long类型0常量
     */
    public static final Long ONE_DAY = 24 * 3600 * 1000L;
    /**
     * Long类型0常量
     */
    public static final Long FIVE_MINIUTE = 5 * 60 * 1000L;

    /**
     * 查询Pro站支持的所有交易对及精度的默认参数USDT(大写)
     */
    public static final String USDT_UPPER = "USDT";

    /**
     * USDT(小写)
     */
    public static final String USDT_LOWER = "usdt";

    /**
     * 主流交易区
     */
    public static final String SYMBOL_MAIN = "mian";

    /**
     * 创新交易区
     */
    public static final String SYMBOL_INNOVATION = "innovation";

    /**
     * Integer类型0
     */
    public static final Integer INTEGER_ZERO = 0;

    /**
     * Integer类型1
     */
    public static final Integer INTEGER_ONE = 1;

    /**
     * Integer类型1
     */
    public static final Integer INTEGER_TWO = 2;

    /**
     * Redis 锁超时时间,30秒
     */
    public static final Long INVEST_REDISLOCK_KEY_TIMEOUT = 30 * 1000L;

}
