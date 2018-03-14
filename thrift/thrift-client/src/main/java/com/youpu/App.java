package com.youpu;

import com.youpu.dto.QueryParam;
import com.youpu.service.PersonService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try{
            TTransport tTransport;
            tTransport = new TSocket("localhost",9090);
            tTransport.open();

            TProtocol protocol = new TBinaryProtocol(tTransport);
            PersonService.Client client = new PersonService.Client(protocol);
            System.out.println(client.ping());

            int max = 10000;

            Long start = System.currentTimeMillis();
            for (int i=0;i<max;i++){
                call(client);
            }

            Long end = System.currentTimeMillis();
            Long elapse = end-start;

            int perform = Double.valueOf(max/(elapse/1000d)).intValue();

            System.out.println("thrift"+max+"次rpc调用，耗时："+elapse+"毫秒，平均"+perform+"次／秒 ");
            tTransport.close();



        }catch(Exception ex){

        }
        System.out.println( "Hello World!" );
    }

    private static void call(PersonService.Client client) throws TException{
        QueryParam queryParam = new QueryParam();
        queryParam.setAgeStart(Short.valueOf("5"));
        queryParam.setAgeEnd(Short.valueOf("50"));
        client.getPersonList(queryParam);
    }
}
