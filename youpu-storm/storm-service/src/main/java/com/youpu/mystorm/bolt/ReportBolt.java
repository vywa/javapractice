package com.youpu.mystorm.bolt;

import com.youpu.mystorm.Constant;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.HashMap;
import java.util.Map;

public class ReportBolt extends BaseRichBolt{

    private HashMap<String,Integer> counts;


    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.counts = new HashMap<String,Integer>();
    }

    @Override
    public void execute(Tuple tuple) {
        String word = tuple.getStringByField(Constant.WORD_COUNT_KEY);
        Integer count = tuple.getIntegerByField(Constant.WORD_COUNT_VALUE);
        this.counts.put(word,count);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }

    @Override
    public void cleanup() {
        System.out.println("==================开始计算================");
        for(Map.Entry<String,Integer> entry : counts.entrySet()){
            System.out.println(entry.getKey()+"出现次数为:"+entry.getValue());
        }
        System.out.println("=================结束计算===================");
    }
}
