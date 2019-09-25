package com.netease.kg.main;

import com.alibaba.fastjson.JSON;
import com.netease.kg.pojo.ProjectPO;
import com.netease.kg.pojo.RootPO;
import com.netease.kg.util.UrlPostUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


/**
 * @author likeguo
 * @since 2019-09-23
 */
public class GetMain {

    public static void main(String []args) throws Exception {

        //获得178个应用带有com.netease.haitao
        String result = UrlPostUtils.getResult("org.springframework",10000,0);
        System.out.println("请求响应结果："+ result);
        System.out.println("==>"+result);
        RootPO rootPO = JSON.parseObject(result, RootPO.class);
        System.out.println(JSON.toJSONString(rootPO.getData().size()));

        HashSet map1 = new HashSet(400);

        int count = 0;

        for(ProjectPO projectPO : rootPO.getData()){
            map1.add(projectPO.getProjectName());
            count = count + projectPO.getData().size();
        }

        System.out.println("count==>"+count);


//        result = UrlPostUtils.getResult("com.netease",10000,0);
//        System.out.println("请求响应结果："+ result);
//        rootPO = JSON.parseObject(result, RootPO.class);
//        System.out.println(JSON.toJSONString(rootPO.getData().size()));
//
//        map1 = new HashSet(400);
//
//        count = 0;
//
//        for(ProjectPO projectPO : rootPO.getData()){
//            map1.add(projectPO.getProjectName());
//            count = count + projectPO.getData().size();
//        }
//
//        System.out.println("count==>"+count);



//        //获得178个应用带有com.netease.haitao
//        result = UrlPostUtils.getResult("com.netease",1000);
//        System.out.println("请求响应结果："+ result);
//        rootPO = JSON.parseObject(result, RootPO.class);
//        System.out.println(JSON.toJSONString(rootPO.getData().size()));
//        HashSet map2 = new HashSet(200);
//        for(ProjectPO projectPO : rootPO.getData()){
//            map2.add(projectPO.getProjectName());
//        }
//
//        map1.removeAll(map2);
//
//        System.out.println(map1);
//        System.out.println(map1.size());














    }

}
