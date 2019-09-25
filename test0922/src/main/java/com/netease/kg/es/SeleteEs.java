package com.netease.kg.es;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author likeguo
 * @since 2019-09-24
 */
public class SeleteEs {

    public static void main(String[] args) throws UnknownHostException {


        Settings esSettings = Settings.builder()
                //设置ES实例名称
                .put("cluster.name", "haitao_trace_log")
                //自动嗅探整个集群的状态，把集群中其他ES节点的ip添加到本地的客户端列表中
                .put("client.transport.sniff", true)
                .build();

        /**
         * 这里的连接方式指的是没有安装x-pack插件,如果安装了x-pack则参考{@link ElasticsearchXPackClient}
         * 1. java客户端的方式是以tcp协议在7100端口上进行通信
         * 2. http客户端的方式是以http协议在7000端口上进行通信
         */
        TransportClient client = new PreBuiltTransportClient(esSettings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("10.196.30.112"), 7100));


        SearchResponse response = client.prepareSearch("music-source-pom")
                .setTypes("dependencies")
                .setFrom(10)
                .setSize(60)
                .get();

        int totalHits = (int) response.getHits().getTotalHits();
        System.out.println("totalHits==>"+totalHits);

        List<EsPO> esPOList = new ArrayList<>();
        for (SearchHit hit : response.getHits().getHits()) {
            EsPO esPO = JSON.parseObject(hit.getSourceAsString(), EsPO.class);
            esPOList.add(esPO);
            System.out.println(JSON.toJSONString(esPO));
        }

        System.out.println("ElasticsearchClient 连接成功");
        System.out.println("size==>"+esPOList.size());
    }

    public List select(Integer size, Integer from) throws UnknownHostException {

        Settings esSettings = Settings.builder()
                //设置ES实例名称
                .put("cluster.name", "haitao_trace_log")
                //自动嗅探整个集群的状态，把集群中其他ES节点的ip添加到本地的客户端列表中
                .put("client.transport.sniff", true)
                .build();

        /**
         * 这里的连接方式指的是没有安装x-pack插件,如果安装了x-pack则参考{@link ElasticsearchXPackClient}
         * 1. java客户端的方式是以tcp协议在7100端口上进行通信
         * 2. http客户端的方式是以http协议在7000端口上进行通信
         */
        TransportClient client = new PreBuiltTransportClient(esSettings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("10.196.30.112"), 7100));


        SearchResponse response = client.prepareSearch("music-source-pom")
                .setTypes("dependencies")
//                .setQuery(QueryBuilders.termQuery("multi", "test"))
                .setFrom(0)
                .setSize(60)
                .get();

        int totalHits = (int) response.getHits().getTotalHits();
        System.out.println("totalHits==>"+totalHits);

        List<EsPO> esPOList = new ArrayList<>();
        for (SearchHit hit : response.getHits().getHits()) {
            EsPO item = getFromHit(hit);
            esPOList.add(item);
        }
        return esPOList;
    }
    public EsPO getFromHit(SearchHit hit){
        EsPO esPO = JSON.parseObject(hit.getSourceAsString(), EsPO.class);
        return esPO;
    }
}
