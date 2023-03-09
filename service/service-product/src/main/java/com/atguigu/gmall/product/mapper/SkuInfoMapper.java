package com.atguigu.gmall.product.mapper;

import com.atguigu.gmall.model.product.SkuInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: Janty
 * @projectName: gmall-parent
 * @date: 2023/3/9 22:07
 * @description:
 */
@Mapper
public interface SkuInfoMapper extends BaseMapper<SkuInfo> {
}

