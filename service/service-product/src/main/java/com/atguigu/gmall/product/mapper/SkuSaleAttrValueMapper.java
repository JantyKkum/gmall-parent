package com.atguigu.gmall.product.mapper;

import com.atguigu.gmall.model.product.SkuSaleAttrValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author: Janty
 * @projectName: gmall-parent
 * @date: 2023/3/9 22:08
 * @description:
 */
@Mapper
public interface SkuSaleAttrValueMapper extends BaseMapper<SkuSaleAttrValue> {

    // 根据spuId 查询map 集合数据
    List<Map> selectSaleAttrValuesBySpu(Long spuId);
}
