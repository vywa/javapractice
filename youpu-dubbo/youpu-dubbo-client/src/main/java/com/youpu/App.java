package com.youpu;

import com.youpu.service.impl.UserAccountServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try{

            ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:dubboConsumer.xml");
            UserAccountServiceImpl myService = (UserAccountServiceImpl) ctx.getBean("myService");

            Long start = System.currentTimeMillis();
            System.out.println("开始发送请求");
            for(int i=0;i<10000;i++){
                myService.sayHello();
            }

            Long end = System.currentTimeMillis();
            Long expire = (end-start)/1000;

            System.out.print("结束请求,用时"+expire+"秒,平均速率"+10000/expire+"次/秒");
            System.in.read();

        }catch(Exception ex){
            System.out.println("出错了");
            ex.printStackTrace();
        }
        System.out.println( "Hello World!" );
    }
}
