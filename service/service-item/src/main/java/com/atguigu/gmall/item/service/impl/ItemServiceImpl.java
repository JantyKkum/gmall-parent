package com.atguigu.gmall.item.service.impl;

import com.atguigu.gmall.item.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Janty
 * @projectName: gmall-parent
 * @date: 2023/3/11 23:11
 * @description:
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Override
    public Map<String, Object> getBySkuId(Long skuId) {
        Map<String, Object> result = new HashMap<>();


        return result;
    }
}

