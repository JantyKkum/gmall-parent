package com.atguigu.gmall.item.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * @author: Janty
 * @projectName: gmall-parent
 * @date: 2023/3/11 23:10
 * @description:
 */
@RestController
@RequestMapping("api/item")
public class ItemApiController {


    @Autowired
    private ItemService itemService;

    /**
     * 获取sku详情信息
     * @param skuId
     * @return
     */
    @GetMapping("{skuId}")
    public Result getItem(@PathVariable Long skuId){
        Map<String,Object> result = itemService.getItemBySkuId(skuId);
        return Result.ok(result);
    }
}

