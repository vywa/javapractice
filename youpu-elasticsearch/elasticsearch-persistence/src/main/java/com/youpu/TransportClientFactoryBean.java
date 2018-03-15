package com.youpu;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

import static org.apache.commons.lang3.StringUtils.*;


public class TransportClientFactoryBean implements FactoryBean<TransportClient>,InitializingBean,DisposableBean{

    private String clusterNode = "localhost:9300";
    private String clusterName = "elasticsearch";
    private Boolean clientTransportSniff = true;
    private Boolean clientIgnoreClusterName = false;
    private String clientPingTimeout = "5s";
    private String clientNodesSamplerInterval = "5s";

    private TransportClient client;
    private Properties properties;
    static final String COLON = ":";
    static final String COMMA = ",";



    @Override
    public void destroy() throws Exception {

        if(client!=null){
            client.close();
        }
    }

    @Override
    public TransportClient getObject() throws Exception {
        return client;
    }

    @Override
    public Class<?> getObjectType() {
        return TransportClient.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        buildClient();
    }

    private void buildClient() {
        client = new PreBuiltTransportClient(settings());
        Assert.hasText(clusterNode,"[Assertion failed] clusterNodessettings missing");
        for(String node : split(clusterNode,COMMA)){
            String hostName = substringBeforeLast(clusterNode,COLON);
            String port = substringAfterLast(clusterNode,COLON);
            try {
                client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(hostName),Integer.valueOf(port)));
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        client.connectedNodes();
    }

    private Settings settings() {
        if(properties !=null){
            return Settings.builder().put(properties).build();
        }
        return Settings.EMPTY;
    }


    public String getClusterNode() {
        return clusterNode;
    }

    public void setClusterNode(String clusterNode) {
        this.clusterNode = clusterNode;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public Boolean getClientTransportSniff() {
        return clientTransportSniff;
    }

    public void setClientTransportSniff(Boolean clientTransportSniff) {
        this.clientTransportSniff = clientTransportSniff;
    }

    public Boolean getClientIgnoreClusterName() {
        return clientIgnoreClusterName;
    }

    public void setClientIgnoreClusterName(Boolean clientIgnoreClusterName) {
        this.clientIgnoreClusterName = clientIgnoreClusterName;
    }

    public String getClientPingTimeout() {
        return clientPingTimeout;
    }

    public void setClientPingTimeout(String clientPingTimeout) {
        this.clientPingTimeout = clientPingTimeout;
    }

    public String getClientNodesSamplerInterval() {
        return clientNodesSamplerInterval;
    }

    public void setClientNodesSamplerInterval(String clientNodesSamplerInterval) {
        this.clientNodesSamplerInterval = clientNodesSamplerInterval;
    }

    public TransportClient getClient() {
        return client;
    }

    public void setClient(TransportClient client) {
        this.client = client;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
