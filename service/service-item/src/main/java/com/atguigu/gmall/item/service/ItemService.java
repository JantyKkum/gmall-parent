package com.atguigu.gmall.item.service;

import java.util.Map;

/**
 * @author: Janty
 * @projectName: gmall-parent
 * @date: 2023/3/11 23:11
 * @description:
 */
public interface ItemService {

    /**
     * 获取sku详情信息
     * @param skuId
     * @return
     */
    Map<String, Object> getItemBySkuId(Long skuId);
}

