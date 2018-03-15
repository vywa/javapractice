package com.youpu.simple;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class SimpleWord
{
    private static final Logger log = LoggerFactory.getLogger(SimpleWord.class);

    public static void main( String[] args )
    {
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("word",new TestWordSpout(),1);
        builder.setBolt("exclaim",new ExclamationBolt(),1).shuffleGrouping("word");
        builder.setBolt("print",new PrintBolt(),1).shuffleGrouping("exclaim");

        Config config = new Config();
        config.setDebug(true);

        if(args!=null &&args.length>0){
            config.setNumWorkers(3);
            try {
                StormSubmitter.submitTopologyWithProgressBar(args[0],config,builder.createTopology());
            } catch (AlreadyAliveException e) {
                e.printStackTrace();
            } catch (InvalidTopologyException e) {
                e.printStackTrace();
            } catch (AuthorizationException e) {
                e.printStackTrace();
            }
        }else {
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("test3",config,builder.createTopology());
            Utils.sleep(20000);
            cluster.killTopology("test3");
            cluster.shutdown();
        }

    }
}
