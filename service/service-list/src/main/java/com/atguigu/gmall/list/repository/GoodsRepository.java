package com.atguigu.gmall.list.repository;

import com.atguigu.gmall.model.list.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
/**
 * @author: Janty
 * @projectName: gmall-parent
 * @date: 2023/3/25 17:42
 * @description:
 */

public interface GoodsRepository extends ElasticsearchRepository<Goods,Long> {

}

