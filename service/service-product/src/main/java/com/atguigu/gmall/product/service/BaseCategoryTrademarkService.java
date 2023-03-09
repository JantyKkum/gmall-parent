package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.BaseCategoryTrademark;
import com.atguigu.gmall.model.product.BaseTrademark;
import com.atguigu.gmall.model.product.CategoryTrademarkVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author: Janty
 * @projectName: gmall-parent
 * @date: 2023/3/2 19:49
 * @description:
 */
public interface BaseCategoryTrademarkService extends IService<BaseCategoryTrademark> {

    List<BaseTrademark> findCurrentTrademarkList(Long category3Id);

    void remove(Long category3Id, Long trademarkId);

    List<BaseTrademark> findTrademarkList(Long category3Id);

    void save(CategoryTrademarkVo categoryTrademarkVo);

}
