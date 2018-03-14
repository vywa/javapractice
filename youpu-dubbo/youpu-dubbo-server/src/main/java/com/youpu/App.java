package com.youpu;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try{

            ApplicationContext ctx = new FileSystemXmlApplicationContext("youpu-dubbo/youpu-dubbo-server/src/main/java/applicationProvider.xml");
            System.out.println(" dubbo 正在提供服务");
            System.in.read();

        }catch(Exception ex){
            System.out.println("出错了 ");
            ex.printStackTrace();
        }
        System.out.println( "Hello World!" );
    }
}
