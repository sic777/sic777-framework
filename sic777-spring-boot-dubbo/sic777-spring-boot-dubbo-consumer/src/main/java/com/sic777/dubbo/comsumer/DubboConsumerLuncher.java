package com.sic777.dubbo.comsumer;

import com.sic777.dubbo.common.constants.DubboConstant;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import java.util.concurrent.CountDownLatch;

/**
 * <p>
 * 服务消费者启动类</br>
 * 开发者可以实现org.springframework.boot.CommandLineRunner接口来做一些初始化工作(调用时机为容器启动之后),
 * 该接口可以使用注解org.springframework.core.annotation.Order,
 * 在拥有多个初始化数据操作的时候设置初始化的顺序
 * (请从下标10开始，0-9为默认占用，值越小越先执行)
 * </p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-04-23
 */
@Order(0)
@SpringBootApplication(scanBasePackages = DubboConstant.BASE_SPRING_SCAN_PACKAGE)
@DubboComponentScan(DubboConstant.BASE_DUBBO_SCAN_PACKAGE)
public class DubboConsumerLuncher implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(DubboConsumerLuncher.class);

    @Bean
    public CountDownLatch closeLatch() {
        return new CountDownLatch(1);
    }

    public static void start(Thread hook) {

        try {
            ApplicationContext ctx = new SpringApplicationBuilder()
                    .sources(DubboConsumerLuncher.class)
                    .web(true)
                    .run();
            //TODO tomcat hook
            Runtime.getRuntime().addShutdownHook(hook);
            CountDownLatch closeLatch = ctx.getBean(CountDownLatch.class);
            closeLatch.await();
        } catch (Exception e) {
            logger.error("", e);
            System.exit(-1);
        }
    }

    @Override
    public void run(String... strings) throws Exception {
        logger.info("The program is started successfully, and initialization is to be executed ...");
    }
}
