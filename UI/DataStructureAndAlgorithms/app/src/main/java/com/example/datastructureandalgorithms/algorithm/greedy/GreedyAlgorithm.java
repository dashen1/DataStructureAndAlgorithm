package com.example.datastructureandalgorithms.algorithm.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GreedyAlgorithm {

    public static void main(String[] args) {
        //创建电台集合，放入map
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();

        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        broadcasts.put("k1", hashSet1);
        broadcasts.put("k2", hashSet2);
        broadcasts.put("k3", hashSet3);
        broadcasts.put("k4", hashSet4);
        broadcasts.put("k5", hashSet5);

        HashSet<String> allArea = new HashSet<>();
        allArea.add("北京");
        allArea.add("上海");
        allArea.add("天津");
        allArea.add("广州");
        allArea.add("深圳");
        allArea.add("成都");
        allArea.add("杭州");
        allArea.add("大连");

        //创建一个ArrayList 存放选择的电台集合
        ArrayList<String> selects = new ArrayList<>();
        //定义一个临时集合，在遍历的过程中，存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交际
        HashSet<String> tmpSet = new HashSet<>();

        //定义maxKey，保存在一次遍历过程中，能够覆盖最大未覆盖的地区对应的的电台的key
        //如果maxKey不为null,则会加入到selects中
        String maxKey;
        int maxCount = 0;
        while (allArea.size() != 0) {
            maxKey = null;
            maxCount = 0;
            for (String key : broadcasts.keySet()) {
                //tmpSet.clear();
                //当前这个key能够覆盖的地区
                HashSet<String> areas = broadcasts.get(key);
                //tmpSet.addAll(areas);
                //求出tmpSet和allArea集合的交集
                //tmpSet.retainAll(allArea);//保留共有部分
                areas.retainAll(allArea);
                if (areas.size() > 0 && (maxKey == null || areas.size() > maxCount)) {//这里就体现贪心算法 每次都选最优的
                    maxKey = key;
                    maxCount = areas.size();//maxCount 保存当前遍历中，能够覆盖最大覆盖地区的数量
                }
            }
            if (maxKey != null) {
                selects.add(maxKey);
                //将maxKey指向的电台覆盖的地区，从allArea中去掉
                allArea.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println("得到的结果：" + selects);
    }
}
