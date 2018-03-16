package com.youpu.kafka;

import org.apache.storm.kafka.*;
import org.apache.storm.spout.Scheme;
import org.apache.storm.spout.SchemeAsMultiScheme;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class MyKafkaSpout implements IRichSpout {

    SpoutOutputCollector collector;

    public  static KafkaSpout getKafkaSpout() {
        String zookeeper = "localhost:2181";
        String topic = "test";
        String zkRoot = "/storm";
        String id = "test";
        BrokerHosts host = new ZkHosts(zookeeper);
        SpoutConfig spoutConfig = new SpoutConfig(host,topic,zkRoot,id);
        spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
        spoutConfig.zkServers = Arrays.asList(new String[]{"localhost"});
        spoutConfig.zkPort = 2181;
        return new KafkaSpout(spoutConfig);


    }


    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {

    }

    @Override
    public void close() {

    }

    @Override
    public void activate() {

    }

    @Override
    public void deactivate() {

    }

    @Override
    public void nextTuple() {

    }

    @Override
    public void ack(Object o) {

    }

    @Override
    public void fail(Object o) {

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
