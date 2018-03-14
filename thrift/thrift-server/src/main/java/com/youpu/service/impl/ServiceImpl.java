package com.youpu.service.impl;

import com.youpu.dto.Person;
import com.youpu.dto.QueryParam;
import com.youpu.service.PersonService;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ServiceImpl implements PersonService.Iface {

//    private static final Logger logger = LoggerFactory.getLogger(ServiceImpl.class);
    @Override
    public String ping() throws TException {
//        logger.info("-------心跳信息-------");
        System.out.println("-------心跳信息-------");
        return "ping";
    }

    @Override
    public List<Person> getPersonList(QueryParam parameter) throws TException {
        List<Person> list = new ArrayList<Person>(10);
        for (short i = 0; i < 10; i++) {
            Person p = new Person();
            p.setAge(i);
            p.setChildrenCount(Byte.valueOf(i + ""));
            p.setName("test" + i);
            p.setSalary(10000D);
            p.setSex(true);
            list.add(p);
        }
        return list;
    }
}
