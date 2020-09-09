package org.apache.dubbo.demo;

import org.apache.dubbo.common.Constants;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Consumer {
    public static void main(String[] args) throws Exception {
        System.setProperty(Constants.QOS_PORT,"33333");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {
                "classpath:consumer.xml"});
        context.start();
        DemoService demoService = (DemoService)context.getBean("demoService"); // 获取远程服务代理
        String hello = demoService.sayHello("world"); // 执行远程方法
        System.out.println( hello ); // 显示调用结果
    }
}