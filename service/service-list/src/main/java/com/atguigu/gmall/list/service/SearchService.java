package com.atguigu.gmall.list.service;

/**
 * @author: Janty
 * @projectName: gmall-parent
 * @date: 2023/3/25 17:39
 * @description:
 */
public interface SearchService {

    /**
     * 上架商品列表
     * @param skuId
     */
    void upperGoods(Long skuId);

    /**
     * 下架商品列表
     * @param skuId
     */
    void lowerGoods(Long skuId);

    /**
     * 更新热点
     * @param skuId
     */
    void incrHotScore(Long skuId);

}

