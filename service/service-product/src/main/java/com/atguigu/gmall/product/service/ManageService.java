package com.atguigu.gmall.product.service;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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

    //根据spuId 查询（销售属性和销售属性值）集合
    List<SpuSaleAttr> getSpuSaleAttrList(Long spuId);

    //根据spuId 查询spuImageList
    List<SpuImage> getSpuImageList(Long spuId);

    //保存sku
    void saveSkuInfo(SkuInfo skuInfo);

    /**
     * SKU分页列表
     * @param pageParam
     * @return
     */
    IPage<SkuInfo> getPage(Page<SkuInfo> pageParam);
    /**
     * 商品上架
     * @param skuId
     */
    void onSale(Long skuId);

    /**
     * 商品下架
     * @param skuId
     */
    void cancelSale(Long skuId);

    /**
     * 根据skuId 查询skuInfo
     * @param skuId
     * @return
     */
    SkuInfo getSkuInfo(Long skuId);

    /**
     * 通过三级分类id查询分类信息
     * @param category3Id
     * @return
     */
    BaseCategoryView getCategoryViewByCategory3Id(Long category3Id);

    /**
     * 获取sku价格
     * @param skuId
     * @return
     */
    BigDecimal getSkuPrice(Long skuId);

    /**
     * 根据spuId，skuId 查询销售属性集合
     * @param skuId
     * @param spuId
     * @return
     */
    List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(Long skuId, Long spuId);


    /**
     * 根据spuId 查询map 集合属性
     * @param spuId
     * @return
     */
    Map getSkuValueIdsMap(Long spuId);

    /**
     * 根据spuid获取商品海报
     * @param spuId
     * @return
     */
    List<SpuPoster> findSpuPosterBySpuId(Long spuId);


    /**
     * 通过skuId 集合来查询数据
     * @param skuId
     * @return
     */
    List<BaseAttrInfo> getAttrList(Long skuId);

    BigDecimal getPrice(Long skuId);

    /**
     * 获取全部分类信息
     * @return
     */
    List<JSONObject> getBaseCategoryList();

    /**
     * 通过品牌Id 来查询数据
     * @param tmId
     * @return
     */
    BaseTrademark getTrademarkByTmId(Long tmId);

}
