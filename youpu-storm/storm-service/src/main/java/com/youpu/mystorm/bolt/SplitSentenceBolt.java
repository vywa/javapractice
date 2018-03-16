package com.youpu.mystorm.bolt;

import com.youpu.mystorm.Constant;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

public class SplitSentenceBolt extends BaseRichBolt{


    private OutputCollector collector;

    /**
     * 在storm中，这个方法相当于bolt的构造函数，类初始化时被调用。一般把bolt的初始化操作放在这个方法里
     * @param map
     * @param topologyContext
     * @param outputCollector
     */
    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.collector = outputCollector;
    }

    /**
     * 在storm运行期间，一旦这个bolt订阅的元组到达，这个方法就会被调用
     * @param tuple
     */
    @Override
    public void execute(Tuple tuple) {
        String sentence = tuple.getStringByField(Constant.SPOUT_DECLARE);
        String[] words = sentence.split(" ");
        for(String word : words){
            word = word.trim();
            //将输出的tuple和输入的tuple锚定
            this.collector.emit(tuple,new Values(word));
        }
        //告诉spout，这个元组已经被成功处理
        this.collector.ack(tuple);
    }

    @Override
    public void cleanup() {

    }

    /**
     * 声明输出元组的字段信息
     * @param outputFieldsDeclarer
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields(Constant.SPLIT_DECLARE));
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
