package com.one.collection;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author One
 * @description 跳表实验
 * @date 2018/09/19
 */
public class ConcurrentSkipListMapLab {
    public static void main(String[] args) {
        Map<Integer,Integer> map = new ConcurrentSkipListMap();
        for (int i=0;i<30;i++){
            map.put(i,i);
        }
        for (Map.Entry<Integer,Integer> entry:map.entrySet()){
            System.out.println(entry.getKey());
            //0...29
        }
    }
}
