package com.youpu;

import com.youpu.service.PersonService;
import com.youpu.service.impl.ServiceImpl;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

/**
 * Hello world!
 *
 */
public class App 
{
    public static PersonService.Iface service;

    public static PersonService.Processor processor;

    public static void main( String[] args )
    {
        try{
            service = new ServiceImpl();
            processor = new PersonService.Processor(service);
            Runnable simplethread = new Runnable() {
                @Override
                public void run() {
                    simple(processor);
                }
            };

            new Thread(simplethread).start();

        }catch (Exception ex){
            ex.printStackTrace();
        }


    }

    public static void simple(PersonService.Processor processor){
        try {
            TServerTransport serverTransport = new TServerSocket(9090);
            TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));
            System.out.print("starting the simple server....");
            server.serve();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
