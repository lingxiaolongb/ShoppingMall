package com.itlong.controller;

import com.itlong.bean.ProductInfo;
import com.itlong.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/market")
@Controller
public class ProductController {

    @Autowired
    ProductInfoService productInfoService;

    @GetMapping("/{prodId}")
    public String toDetailed(@PathVariable String prodId, Model model){
        ProductInfo productInfo=productInfoService.selectByPrimaryKey(prodId);//可以做缓存
        List<ProductInfo> randProd = productInfoService.selectByRandom();
        model.addAttribute("productInfo",productInfo);
        model.addAttribute("randProd",randProd);
        return "site/productDetail";

    }
    @RequestMapping("")
    public String toPreview(){
        return "site/preview";
    }

}
