package greedyAlgotithm;

import java.util.*;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 电台覆盖问题
 * @date 2022/11/26 19:59
 */
public class greedyAlgorithm {
    public static void main(String[] args) {
        List<String> list = greedy(); //[k1, k2, k3, k5]
        System.out.println(list);
    }


    /**
     * Description : 使用贪心算法实现最少电台覆盖所有地区问题
     * 将该问题分解成一个子问，即每次都选择一个能覆盖最多地区的电台
     *①将所有待覆盖地区全部加入一个集合areaList
     *②用一个集合selectList记录所选择的电台
     *③针对每个电台进行遍历，计算其能够覆盖的地区数，每一轮选择最多覆盖数的电台，
     *将此电台加入selectList，同时将此电台所覆盖的所有地区从areaList集合中去除。
     *④不断重复第③步，直至areaList集合的长度为0，此时代表已经完成对所有地区的覆盖。
     * @date 2022/11/26
     **/
    public static List<String> greedy(){
        //构建存储所有电台以及电台覆盖地区信息的map集合
        HashMap<String, String[]> broadcasts = new HashMap<>();
        broadcasts.put("k1",new String[]{"北京", "上海", "天津"});
        broadcasts.put("k2",new String[]{"广州", "北京", "深圳"});
        broadcasts.put("k3",new String[]{"成都", "上海", "杭州"});
        broadcasts.put("k4",new String[]{"上海", "天津"});
        broadcasts.put("k5",new String[]{"杭州", "大连"});

        // System.out.println(Arrays.toString(broadcasts.get("k1")));
        //存储所有待覆盖地区信息的set集合(自带去重复效果)
        HashSet<String> areaList = new HashSet<>();
        //返回所有(包含了所有待覆盖地区名)构成的Collection集合
        Collection<String[]> areaNames = broadcasts.values();
        for(String[] name : areaNames){
            //将所有待覆盖地区名加入到areaList集合中
            Collections.addAll(areaList, name);
        }
        // for (String area: areaList) {
        //     System.out.print(area + " ");
        // }
        // System.out.println("\n待覆盖地区的个数为：" + areaList.size());
        //维护已选择的电台信息的集合
        List<String> selectList = new ArrayList<>();
        String maxBCIndex = "";//维护选择的最大覆盖地区的广播的名称
        while (areaList.size() != 0){
            int max = 0;
            //包含所有电台名称构成的set集合
            Set<String> broadcastName = broadcasts.keySet();
            for (String bcName : broadcastName) {
                int needCoverNum = 0; //记录当前电台所覆盖地区的个数
                String[] coverAreaArray = broadcasts.get(bcName);//当前名称对应电台覆盖地区数组
                // 判断当前电台覆盖的地区在需要覆盖地区集合中的个数
                for(String area : coverAreaArray){
                    if (areaList.contains(area)){ //逐一判断当前电台的覆盖地区是否在待覆盖地区的集合中
                        needCoverNum++;
                    }
                }
                if (needCoverNum != 0){
                    //下面这个比较就体现出了贪心算法的特点，每次选择的都是局部最优解
                    if (max < needCoverNum){ //从所有电台中判断覆盖的地区最多的电台
                        max = needCoverNum;
                        maxBCIndex = bcName;
                    }
                }
            }
            selectList.add(maxBCIndex); //将覆盖最多地区的电台加入选择电台的list集合
            String[] broadcast = broadcasts.get(maxBCIndex); //获得对应电台覆盖地区的数组
            for (String areaName : broadcast){ //逐个从待覆盖地区的集合中删除对应已覆盖的地区名
                areaList.remove(areaName);
            }
            broadcasts.remove(maxBCIndex);//从所有电台中删除已加入选择电台集合中的电台名
        }

        // System.out.println("\n待覆盖地区的个数为：" + areaList.size());
        return selectList;
    }


}
