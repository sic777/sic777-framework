package com.sic777.common.utils.generator;


/**
 * <p>Hex16ID生成器
 *
 * @author sic777
 * @since 0.0.1
 */
public class Hex16Generator {
    private static final Hex16Generator singleton = new Hex16Generator();

    public static final Hex16Generator instance() {
        return singleton;
    }

    private Hex16Generator() {
    }

    public String next() throws Exception {
        long guid = GuidManager.instance().generateGuid(8);
        return Long.toHexString(guid);
    }
}
