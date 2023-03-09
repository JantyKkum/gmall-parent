package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.BaseTrademark;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author: Janty
 * @projectName: gmall-parent
 * @date: 2023/3/2 18:18
 * @description:
 */
public interface BaseTrademarkService  extends IService<BaseTrademark> {

    /**
     * Banner分页列表
     * @param pageParam
     * @return
     */
    IPage<BaseTrademark> getPage(Page<BaseTrademark> pageParam);

}

