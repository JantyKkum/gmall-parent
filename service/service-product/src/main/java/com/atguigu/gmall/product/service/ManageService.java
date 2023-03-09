package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @author: Janty
 * @projectName: gmall-parent
 * @date: 2023/3/1 18:06
 * @description:
 */
public interface ManageService {

    List<BaseCategory1> getCategory1();

    List<BaseCategory2> getCategory2(Long category1Id);

    List<BaseCategory3> getCategory3(Long category2Id);

    //根据分类id查询平台属性
    List<BaseAttrInfo> attrInfoList(Long category1Id, Long category2Id, Long category3Id);

    //新增修改平台属性
    void saveAttrInfo(BaseAttrInfo baseAttrInfo);

    //根据属性id查询属性对象
    BaseAttrInfo getAttrInfo(Long attrId);

    //根据三级分页查询spu列表
    IPage<SpuInfo> getSpuInfoPage(Page<SpuInfo> spuInfoPage, SpuInfo spuInfo);

    //获取销售属性
    List<BaseSaleAttr> getBaseSaleAttrList();

    //保存spu
    void saveSpuInfo(SpuInfo spuInfo);
}
