package com.youpu.mystorm.spout;

import com.youpu.mystorm.Constant;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;

import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SentenceSpout extends BaseRichSpout{

    private ConcurrentHashMap<UUID,Values> pending;

    private SpoutOutputCollector collector;

    private String[] sentences = {
      "connecting the dots",
      "love and loss",
      "keep looking",
      "do not settle",
      "stay hungry",
      "stay foolish"
    };
    private int index;

    /**
     * 在storm中，这个方法相当于storm的构造方法，类初始化时被调用，所以一般会把spout初始化操作放在这个方法里
     * @param map
     * @param topologyContext
     * @param spoutOutputCollector
     */
    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.index = 0;
        this.collector = spoutOutputCollector;
        this.pending = new ConcurrentHashMap<UUID, Values>();

    }


    /**
     * 在storm运行期间，这个方法会一直被调用，可以理解为死循环
     */
    @Override
    public void nextTuple() {
        Values value = new Values(sentences[new Random().nextInt(sentences.length)]);
        UUID msgId = UUID.randomUUID();
        this.pending.put(msgId,value);
        this.collector.emit(value,msgId);
        //休眠0.1毫秒
        Utils.sleep(100);
    }

    /**
     * 声明输出元组的字段信息
     * @param outputFieldsDeclarer
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields(Constant.SPOUT_DECLARE));
    }

    /**
     * 元组被正常处理后
     * @param msgId
     */
    @Override
    public void ack(Object msgId){
        this.pending.remove(msgId);
    }

    /**
     *  元组未被正常处理就重发
     * @param msgId
     */
    @Override
    public void fail(Object msgId) {
        super.fail(msgId);
        this.collector.emit(this.pending.get(msgId),msgId);
    }
}
