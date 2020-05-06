package com.itlong.service.impl;

import com.itlong.bean.CartInfo;
import com.itlong.bean.ProductInfo;
import com.itlong.constant.Cache;
import com.itlong.constant.WebConst;
import com.itlong.mapper.ProductInfoMapper;
import com.itlong.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productInfoService")
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    ProductInfoMapper productInfoMapper;



    @Override
    @Cacheable(value = Cache.PRODUCT,key = "#prodId")
    public ProductInfo selectByPrimaryKey(String prodId) {
        return productInfoMapper.selectByPrimaryKey(prodId);
    }

    @Override
    @Cacheable(value = Cache.PRODUCT,key = "'hotProds'")
    public List<ProductInfo> selectByMaxScore() {
        return productInfoMapper.selectByMaxScore();
    }

    @Override
    @Cacheable(value = Cache.PRODUCT,key = "'newProds'")
    public List<ProductInfo> selectByMinScore() {
        return  productInfoMapper.selectByMinScore();
    }

    @Override
    public List<ProductInfo> selectByRandom() {
        int count=productInfoMapper.selectRow()-WebConst.ROW;
        if(count<=0) count=0;
        int limit =(int)Math.floor(Math.random()*(count+1));
        return productInfoMapper.selectByRandom(limit,WebConst.ROW);
    }

    //list对应暂不会
    @Override
    public List<ProductInfo> selectByCartInfos(List<CartInfo> cartInfos) {
        return productInfoMapper.selectByCartInfos(cartInfos);
    }

    @Override
    public List<ProductInfo> selectByProdIds(List<String> prods) {
        return productInfoMapper.selectByProdIds(prods);
    }
}
