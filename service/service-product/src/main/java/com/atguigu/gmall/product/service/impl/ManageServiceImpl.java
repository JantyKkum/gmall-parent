package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.*;
import com.atguigu.gmall.product.mapper.*;
import com.atguigu.gmall.product.service.ManageService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Janty
 * @projectName: gmall-parent
 * @date: 2023/3/1 18:06
 * @description:
 */
@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    private BaseCategory1Mapper baseCategory1Mapper;

    @Autowired
    private BaseCategory2Mapper baseCategory2Mapper;

    @Autowired
    private BaseCategory3Mapper baseCategory3Mapper;

    @Autowired
    private BaseAttrInfoMapper baseAttrInfoMapper;

    @Autowired
    private BaseAttrValueMapper baseAttrValueMapper;

    @Autowired
    private SpuInfoMapper spuInfoMapper;

    @Autowired
    private BaseSaleAttrMapper baseSaleAttrMapper;

    @Autowired
    private SpuSaleAttrMapper spuSaleAttrMapper;

    @Autowired
    private SpuSaleAttrValueMapper spuSaleAttrValueMapper;

    @Autowired
    private SpuImageMapper spuImageMapper;

    @Autowired
    private SpuPosterMapper spuPosterMapper;

    @Autowired
    private SkuInfoMapper skuInfoMapper;

    @Autowired
    private SkuImageMapper skuImageMapper;

    @Autowired
    private SkuAttrValueMapper skuAttrValueMapper;

    @Autowired
    private SkuSaleAttrValueMapper skuSaleAttrValueMapper;




    /**
     * 查询一级分类
     * @return
     */
    @Override
    public List<BaseCategory1> getCategory1() {

        List<BaseCategory1> baseCategory1List = baseCategory1Mapper.selectList(null);       //条件设置为null表示查询所有
        return baseCategory1List;
    }

    /**
     * 根据一级分类Id 查询二级分类数据
     */
    @Override
    public List<BaseCategory2> getCategory2(Long category1Id) {
        //SQL语句：select * from XX where id = category1Id

        //创建查询条件
        QueryWrapper<BaseCategory2> queryWrapper = new QueryWrapper<>();
        //添加条件
        queryWrapper.eq("category1_id",category1Id);
        //查询结果
        return baseCategory2Mapper.selectList(queryWrapper);
    }

    /**
     * 根据二级分类Id 查询三级分类数据
     */
    @Override
    public List<BaseCategory3> getCategory3(Long category2Id) {
        //SQL语句：select * from XX where id = category1Id

        //创建查询条件
        QueryWrapper<BaseCategory3> queryWrapper = new QueryWrapper<>();
        //添加条件
        queryWrapper.eq("category2_id",category2Id);
        //查询结果
        return baseCategory3Mapper.selectList(queryWrapper);
    }

    /**
     * 根据分类id查询平台属性
     */
    @Override
    public List<BaseAttrInfo> attrInfoList(Long category1Id, Long category2Id, Long category3Id) {
        //调用mapper查询
        return baseAttrInfoMapper.selectAttrInfoList(category1Id,category2Id,category3Id);
    }

    /**
     * 新增修改平台属性
     * @param baseAttrInfo
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {
        //判断当前操作是save还是修改,null为新增
        if(baseAttrInfo.getId() != null){                       //修改
            //修改平台属性
            baseAttrInfoMapper.updateById(baseAttrInfo);
            //根据平台属性删除属性值集合 逻辑删除 ，sql文件物理删除
            //UPDATE base_attr_value SET is_deleted=1 WHERE is_deleted=0 AND (attr_id = ?)
            //创建删除条件对象
            QueryWrapper<BaseAttrValue> queryWrapper = new QueryWrapper();
            queryWrapper.eq("attr_id", baseAttrInfo.getId());
            baseAttrValueMapper.delete(queryWrapper);

        }else {                                                //新增
            //保存平台属性
            baseAttrInfoMapper.insert(baseAttrInfo);
        }

        //保存平台属性值
        //新增，获取平台属性值集合
        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();

        if (attrValueList != null && attrValueList.size() > 0) {
            // 循环遍历
            for (BaseAttrValue baseAttrValue : attrValueList) {
                // 获取平台属性Id 给attrId
                baseAttrValue.setAttrId(baseAttrInfo.getId()); // ?
                baseAttrValueMapper.insert(baseAttrValue);
            }
        }

    }

    /**
     * 根据属性id查询属性对象
     * @param attrId
     * @return
     */
    @Override
    public BaseAttrInfo getAttrInfo(Long attrId) {
        //获取属性对象
        BaseAttrInfo baseAttrInfo = baseAttrInfoMapper.selectById(attrId);
        //获取属性对象值集合
        List<BaseAttrValue> list = getAttrValueList(attrId);
        //设置属性值集合
        baseAttrInfo.setAttrValueList(list);
        return baseAttrInfo;
    }

    /**
     * 根据三级分页查询spu列表
     * @param spuInfoPage
     * @param spuInfo
     * @return
     */
    @Override
    public IPage<SpuInfo> getSpuInfoPage(Page<SpuInfo> spuInfoPage, SpuInfo spuInfo) {
        //创建条件对象
        QueryWrapper<SpuInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category3_id",spuInfo.getCategory3Id());

        return spuInfoMapper.selectPage(spuInfoPage,queryWrapper);
    }


    /**
     * 得到属性值列表
     * @param attrId
     * @return
     */
    private List<BaseAttrValue> getAttrValueList(Long attrId) {
        //创建条件对象
        QueryWrapper<BaseAttrValue> wrapper = new QueryWrapper<>();
        wrapper.eq("attr_id",attrId);
        //查询数据
        List<BaseAttrValue> baseAttrValueList = baseAttrValueMapper.selectList(wrapper);
        return baseAttrValueList;
    }

    /**
     * 获取销售属性
     * @return
     */
    @Override
    public List<BaseSaleAttr> getBaseSaleAttrList() {
        return baseSaleAttrMapper.selectList(null);
    }

    /**
     * 保存spu
     * @param spuInfo
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSpuInfo(SpuInfo spuInfo) {
    /*
        spuInfo;
        spuImage;
        spuSaleAttr;
        spuSaleAttrValue;
        spuPoster
     */
        spuInfoMapper.insert(spuInfo);

        //  获取到spuImage 集合数据
        List<SpuImage> spuImageList = spuInfo.getSpuImageList();
        //  判断不为空
        if (!CollectionUtils.isEmpty(spuImageList)){
            //  循环遍历
            for (SpuImage spuImage : spuImageList) {
                //  需要将spuId 赋值
                spuImage.setSpuId(spuInfo.getId());
                //  保存spuImge
                spuImageMapper.insert(spuImage);
            }
        }

        //  获取销售属性集合
        List<SpuSaleAttr> spuSaleAttrList = spuInfo.getSpuSaleAttrList();
        //  判断
        if (!CollectionUtils.isEmpty(spuSaleAttrList)){
            //  循环遍历
            for (SpuSaleAttr spuSaleAttr : spuSaleAttrList) {
                //  需要将spuId 赋值
                spuSaleAttr.setSpuId(spuInfo.getId());
                spuSaleAttrMapper.insert(spuSaleAttr);
                ArrayList<Object> objects = new ArrayList<>();
                //  再此获取销售属性值集合
                List<SpuSaleAttrValue> spuSaleAttrValueList = spuSaleAttr.getSpuSaleAttrValueList();
                //  判断
                if (!CollectionUtils.isEmpty(spuSaleAttrValueList)){
                    //  循环遍历
                    for (SpuSaleAttrValue spuSaleAttrValue : spuSaleAttrValueList) {
                        //   需要将spuId， sale_attr_name 赋值
                        spuSaleAttrValue.setSpuId(spuInfo.getId());
                        spuSaleAttrValue.setSaleAttrName(spuSaleAttr.getSaleAttrName());
                        spuSaleAttrValueMapper.insert(spuSaleAttrValue);
                    }
                }
            }
        }

        //  获取到posterList 集合数据
        List<SpuPoster> spuPosterList = spuInfo.getSpuPosterList();
        //  判断不为空
        if (!CollectionUtils.isEmpty(spuPosterList)){
            for (SpuPoster spuPoster : spuPosterList) {
                //  需要将spuId 赋值
                spuPoster.setSpuId(spuInfo.getId());
                //  保存spuPoster
                spuPosterMapper.insert(spuPoster);
            }
        }
    }

    /**
     * 根据spuId 查询（销售属性和销售属性值）集合
     * @param spuId
     * @return
     */
    @Override
    public List<SpuSaleAttr> getSpuSaleAttrList(Long spuId) {

        return spuSaleAttrMapper.selectSpuSaleAttrList();
    }

    /**
     * 根据spuId 查询spuImageList
     * @param spuId
     * @return
     */
    @Override
    public List<SpuImage> getSpuImageList(Long spuId) {
        QueryWrapper<SpuImage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("spu_id",spuId);

        return spuImageMapper.selectList(queryWrapper);
    }

    /**
     * 保存sku
     * @param skuInfo
     */
    @Override
    public void saveSkuInfo(SkuInfo skuInfo) {
        /*
        skuInfo 库存单元表 --- spuInfo！
        skuImage 库存单元图片表 --- spuImage!
        skuSaleAttrValue sku销售属性值表{sku与销售属性值的中间表} --- skuInfo ，spuSaleAttrValue
        skuAttrValue sku与平台属性值的中间表 --- skuInfo ，baseAttrValue
     */
        skuInfoMapper.insert(skuInfo);
        List<SkuImage> skuImageList = skuInfo.getSkuImageList();
        if (skuImageList != null && skuImageList.size() > 0) {
            // 循环遍历
            for (SkuImage skuImage : skuImageList) {
                skuImage.setSkuId(skuInfo.getId());
                skuImageMapper.insert(skuImage);
            }
        }

        List<SkuSaleAttrValue> skuSaleAttrValueList = skuInfo.getSkuSaleAttrValueList();
        // 调用判断集合方法
        if (!CollectionUtils.isEmpty(skuSaleAttrValueList)) {
            for (SkuSaleAttrValue skuSaleAttrValue : skuSaleAttrValueList) {
                skuSaleAttrValue.setSkuId(skuInfo.getId());
                skuSaleAttrValue.setSpuId(skuInfo.getSpuId());
                skuSaleAttrValueMapper.insert(skuSaleAttrValue);
            }
        }

        List<SkuAttrValue> skuAttrValueList = skuInfo.getSkuAttrValueList();
        if (!CollectionUtils.isEmpty(skuAttrValueList)) {
            for (SkuAttrValue skuAttrValue : skuAttrValueList) {
                skuAttrValue.setSkuId(skuInfo.getId());
                skuAttrValueMapper.insert(skuAttrValue);
            }
        }


    }

    /**
     * SKU分页列表
     * @param pageParam
     * @return
     */
    @Override
    public IPage<SkuInfo> getPage(Page<SkuInfo> pageParam) {
        QueryWrapper<SkuInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        IPage<SkuInfo> page = skuInfoMapper.selectPage(pageParam, queryWrapper);
        return page;
    }

    /**
     * 商品上架
     * @param skuId
     */
    @Override
    @Transactional
    public void onSale(Long skuId) {
        // 更改销售状态
        SkuInfo skuInfoUp = new SkuInfo();
        skuInfoUp.setId(skuId);
        skuInfoUp.setIsSale(1);
        skuInfoMapper.updateById(skuInfoUp);
    }

    /**
     * 商品下架
     * @param skuId
     */
    @Override
    @Transactional
    public void cancelSale(Long skuId) {
        // 更改销售状态
        SkuInfo skuInfoUp = new SkuInfo();
        skuInfoUp.setId(skuId);
        skuInfoUp.setIsSale(0);
        skuInfoMapper.updateById(skuInfoUp);
    }




}
