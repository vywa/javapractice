package com.youpu.mystorm;


import com.youpu.mystorm.bolt.ReportBolt;
import com.youpu.mystorm.bolt.SplitSentenceBolt;
import com.youpu.mystorm.bolt.WordCountBolt;

import com.youpu.mystorm.spout.SentenceSpout;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.kafka.*;
import org.apache.storm.spout.SchemeAsMultiScheme;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

import java.util.Arrays;

public class WordCountTopology {

    private static final String CENTENER_SPOUT_ID = "sentence-spout";
    private static final String SPLIT_BOLT_ID = "split-bolt";
    private static final String COUNT_BOLT_ID = "count-bolt";
    private static final String REPORT_BOLT_ID = "report-bolt";
    private static final String TOPOLOGY_NAME = "word-count-topology";




    public static void main(String[] args) {


        TopologyBuilder builder = new TopologyBuilder();

        String zookeeper = "localhost:2181";
        String topic = "test";
        String zkRoot = "/storm";
        String id = "test";
        BrokerHosts host = new ZkHosts(zookeeper);
        SpoutConfig spoutConfig = new SpoutConfig(host,topic,zkRoot,id);
        spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
        spoutConfig.zkServers = Arrays.asList(new String[]{"localhost"});
        spoutConfig.zkPort = 2181;

        builder.setSpout(CENTENER_SPOUT_ID, new SentenceSpout(), 5);

        //ShuffleGrouping：随机选择一个Task来发送。
        builder.setBolt(SPLIT_BOLT_ID, new SplitSentenceBolt(), 8).shuffleGrouping(CENTENER_SPOUT_ID);
        //FiledGrouping：根据Tuple中Fields来做一致性hash，相同hash值的Tuple被发送到相同的Task。
        builder.setBolt(COUNT_BOLT_ID, new WordCountBolt(), 12).fieldsGrouping(SPLIT_BOLT_ID, new Fields(Constant.SPLIT_DECLARE));
        //GlobalGrouping：所有的Tuple会被发送到某个Bolt中的id最小的那个Task。
        builder.setBolt(REPORT_BOLT_ID, new ReportBolt(), 6).globalGrouping(COUNT_BOLT_ID);

        Config conf = new Config();
        conf.setDebug(true);

        if (args != null && args.length > 0) {
            conf.setNumWorkers(3);

            try {
                StormSubmitter.submitTopologyWithProgressBar(args[0], conf, builder.createTopology());
            } catch (AlreadyAliveException e) {
                e.printStackTrace();
            } catch (InvalidTopologyException e) {
                e.printStackTrace();
            } catch (AuthorizationException e) {
                e.printStackTrace();
            }
        }
        else {
            conf.setMaxTaskParallelism(3);

            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology(TOPOLOGY_NAME, conf, builder.createTopology());

            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            cluster.shutdown();
        }

//        SentenceSpout spout = new SentenceSpout();
//        SplitSentenceBolt splitBolt = new SplitSentenceBolt();
//        WordCountBolt wordCountBolt = new WordCountBolt();
//        ReportBolt reportBolt = new ReportBolt();
//
//        TopologyBuilder builder = new TopologyBuilder();
//        builder.setSpout(CENTENER_SPOUT_ID,spout);
//
//        builder.setBolt(SPLIT_BOLT_ID,splitBolt).shuffleGrouping(CENTENER_SPOUT_ID);
//        builder.setBolt(COUNT_BOLT_ID,wordCountBolt).fieldsGrouping(SPLIT_BOLT_ID,new Fields("word"));
//        builder.setBolt(REPORT_BOLT_ID,reportBolt).globalGrouping(COUNT_BOLT_ID);
//
//        Config config = new Config();
//
//        LocalCluster cluster = new LocalCluster();
//        cluster.submitTopology(TOPOLOGY_NAME,config,builder.createTopology());
//
//        Utils.sleep(10000);
//        cluster.killTopology(TOPOLOGY_NAME);
//        cluster.shutdown();
    }
}
