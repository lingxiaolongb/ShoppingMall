package com.itlong.service;

import com.itlong.bean.CartInfo;
import com.itlong.bean.ProductInfo;

import java.util.List;

public interface ProductInfoService {

    ProductInfo selectByPrimaryKey(String prodId);

    List<ProductInfo> selectByMaxScore();

    List<ProductInfo> selectByMinScore();

    List<ProductInfo> selectByRandom();

    List<ProductInfo> selectByCartInfos(List<CartInfo> cartInfos);

    List<ProductInfo> selectByProdIds(List<String> prods);

}
