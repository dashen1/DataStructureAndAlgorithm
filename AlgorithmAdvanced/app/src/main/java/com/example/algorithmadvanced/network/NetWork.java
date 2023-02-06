package com.example.algorithmadvanced.network;

public class NetWork {

  public static void main(String[] args) {
    /**
     *
     * 在trunk带vlan tag标签就知道是哪个vlan了 VLAN ID(12 bit)最多支持4096个
     * Qos 标识帧优先级
     */
    /**
     * VLAN 的划分
     * 1.基于接口
     * 2.基于MAC地址
     * 3.基于IP子网划分（不推荐）
     * 4.基于协议划分（不推荐）
     * 5.基于策略
     */
    //port link-type access
    //port link-type trunk
    //trunk只能一个vlan不打标签
    //hybrid 可以多个vlan不打标签
    /**
     * Vlan间通信
     * 1.使用路由器（可扩展性差）【可利用单臂路由解决】 将一个接口划分为多个子接口 g 0/0/1->g 0/0/1.1, g /0/0/1.2
     * 开启arp huawei arp broadcast enable
     * 2.或者三层交换机[更常用]
     */
    //生成树防环
    /**
     * 因为环产生的问题：二层环
     * 1、广播风暴 导致网络资源被耗尽
     * 2、MAC地址漂移
     */
    /**
     * 1、选出根桥 2、选出根端口 3、选出指定端口 4、
     */
  }
}
