package com.netease.kg.es;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequestBuilder;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class Scroll {

    public static void main(String[] args) {

        try {
            long startTime = System.currentTimeMillis();
            /*创建客户端*/
            //client startup
            //设置集群名称
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


            List<String> result = new ArrayList<String>();

            String scrollId = "";

            //第一次请求
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();


//            //TODO: 设置查询条件
//            RangeQueryBuilder rangequerybuilder = QueryBuilders
//                    .rangeQuery("inputtime")
//                    .from("2016-12-14 02:00:00").to("2016-12-14 07:59:59");
//            sourceBuilder.query(QueryBuilders.boolQuery()
//                    .must(QueryBuilders
//                            .matchPhraseQuery("pointid", "W3.UNIT1.10HFC01CT013"))
//                    .must(rangequerybuilder))
//                    //如果开启游标，则滚动获取
//                    .size(100)
//                    .sort("inputtime", SortOrder.ASC);

            sourceBuilder.size(100);
            //查询
            SearchRequest request = Requests.searchRequest("music-source-pom").types("dependencies");
            request.scroll("2m");
            request.source(sourceBuilder);
            SearchResponse response = client.search(request).actionGet();
            //TODO:处理数据
            SearchHits hits = response.getHits();
            for (int i = 0; i < hits.getHits().length; i++) {
                //System.out.println(hits.getHits()[i].getSourceAsString());
                result.add(hits.getHits()[i].getSourceAsString());
            }
            //记录滚动ID
            scrollId = response.getScrollId();


            while (true) {
                //后续的请求
                //scrollId = query.getScollId();
                SearchScrollRequestBuilder searchScrollRequestBuilder = client
                        .prepareSearchScroll(scrollId);
                // 重新设定滚动时间            
                //TimeValue timeValue = new TimeValue(30000);
                searchScrollRequestBuilder.setScroll("2m");
                // 请求            
                SearchResponse response1 = searchScrollRequestBuilder.get();

                //TODO:处理数据
                SearchHits hits2 = response1.getHits();
                if (hits2.getHits().length == 0) {
                    break;
                }
                for (int i = 0; i < hits2.getHits().length; i++) {
                    result.add(hits2.getHits()[i].getSourceAsString());
                }
                //下一批处理
                scrollId = response1.getScrollId();
            }

            System.out.println(result.size());
            long endTime = System.currentTimeMillis();
            System.out.println("Java程序运行时间：" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}