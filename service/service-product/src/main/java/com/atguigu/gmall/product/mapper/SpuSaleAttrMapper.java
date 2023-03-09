package com.atguigu.gmall.product.mapper;

import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: Janty
 * @projectName: gmall-parent
 * @date: 2023/3/7 23:07
 * @description:
 */
@Mapper
public interface SpuSaleAttrMapper extends BaseMapper<SpuSaleAttr> {

    //根据spuId 查询（销售属性和销售属性值）集合
    List<SpuSaleAttr> selectSpuSaleAttrList();
}
