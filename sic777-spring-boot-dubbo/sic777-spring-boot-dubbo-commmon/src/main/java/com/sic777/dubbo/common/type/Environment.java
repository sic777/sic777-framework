package com.sic777.dubbo.common.type;

/**
 * <p>环境枚举</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-04-24
 */
public enum Environment {
    /**
     * 未配置
     */
    UNKNOWN("unknown"),
    /**
     * 开发环境
     */
    DEVELOP("develop"),
    /**
     * 测试环境
     */
    TEST("test"),
    /**
     * 生产环境
     */
    PRODUCT("product");

    private final String environment;

    Environment(String environment) {
        this.environment = environment;
    }

    public String getEnvironment() {
        return environment;
    }

    public static Environment fromEnvironment(String environment) {
        for (Environment e : Environment.values()) {
            if (e.environment.equals(environment)) {
                return e;
            }
        }
        return Environment.UNKNOWN;
    }
}
