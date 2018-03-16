package com.youpu.mystorm.bolt;

import com.youpu.mystorm.Constant;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.HashMap;
import java.util.Map;

public class WordCountBolt extends BaseRichBolt {

    private OutputCollector collector;
    private HashMap<String,Integer> counters = new HashMap<>();


    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.collector = outputCollector;
        counters = new HashMap<String,Integer>();
    }

    public void execute(Tuple tuple) {
        String word = tuple.getStringByField(Constant.SPLIT_DECLARE);
        Integer count = counters.get(word);
        if(null == count){
            count = 0;
        }
        count++;
        this.counters.put(word,count);
        //将输出的tuple和输入的tuple锚定
        Values values = new Values(word,count);
//        emit(tuple,values);
        this.collector.emit(tuple,new Values(word,count));
        //告诉上游bolt，这个元组已经被成功处理
        this.collector.ack(tuple);
    }



    @Override
    public void cleanup() {

    }



    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields(Constant.WORD_COUNT_KEY,Constant.WORD_COUNT_VALUE));
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
