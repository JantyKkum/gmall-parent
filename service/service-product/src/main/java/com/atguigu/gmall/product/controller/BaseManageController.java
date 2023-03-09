package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.*;
import com.atguigu.gmall.product.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Janty
 * @projectName: gmall-parent
 * @date: 2023/3/1 17:56
 * @description:
 */
@RestController
@RequestMapping("/admin/product/")
public class BaseManageController {

    @Autowired
    private ManageService manageService;

    /**
     * admin/product/getAttrValueList/{attrId}
     * 根据属性id查询属性值集合
     * @param attrId
     * @return
     */
    @GetMapping("/getAttrValueList/{attrId}")
    public Result getAttrValueList(@PathVariable Long attrId){

        //调用service
        BaseAttrInfo baseAttrInfo= manageService.getAttrInfo(attrId);
        List<BaseAttrValue> list=baseAttrInfo.getAttrValueList();

        return Result.ok(list);

    }

    /**
     * 根据分类id查询平台属性
     * @param category1Id
     * @param category2Id
     * @param category3Id
     * @return
     */
    @GetMapping("/attrInfoList/{category1Id}/{category2Id}/{category3Id}")
    public Result attrInfoList(@PathVariable Long category1Id,
                                       @PathVariable Long category2Id,
                                       @PathVariable Long category3Id){
        //调用service
        List<BaseAttrInfo> list = manageService.attrInfoList(category1Id,category2Id,category3Id);
        return Result.ok(list);
    }

    /**
     * 新增修改平台属性
     * @param baseAttrInfo
     * @return
     */
    @PostMapping("/saveAttrInfo")
    public Result saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo){
        //调用service
        manageService.saveAttrInfo(baseAttrInfo);
        return Result.ok();
    }

    /**
     * 查询一级分类
     * @return
     */
    @GetMapping("/getCategory1")
    public Result getCategory1(){

        //查询数据
        List<BaseCategory1> baseCategory1List = manageService.getCategory1();
        return Result.ok(baseCategory1List);
    }

    /**
     * 根据一级分类Id 查询二级分类数据
     * 注意:PathVariable对应路径后面的名称 ("category1Id")
     * @return
     */
    @GetMapping("/getCategory2/{category1Id}")
    public Result getCategory2(@PathVariable Long category1Id){

        //查询数据
        List<BaseCategory2> baseCategory2List = manageService.getCategory2(category1Id);
        return Result.ok(baseCategory2List);
    }

    /**
     * 根据二级分类Id 查询三级分类数据
     * 注意:PathVariable对应路径后面的名称 ("category1Id")
     * @return
     */
    @GetMapping("/getCategory3/{category2Id}")
    public Result getCategory3(@PathVariable Long category2Id){

        //查询数据
        List<BaseCategory3> baseCategory3List = manageService.getCategory3(category2Id);
        return Result.ok(baseCategory3List);
    }


}
