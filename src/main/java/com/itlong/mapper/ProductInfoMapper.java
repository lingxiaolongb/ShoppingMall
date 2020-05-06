package com.itlong.mapper;

import com.itlong.bean.CartInfo;
import com.itlong.bean.ProductInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("productInfoMapper")
public interface ProductInfoMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ProductInfo record);

    ProductInfo selectByPrimaryKey(String prodId);

    List<ProductInfo> selectAll();

    int updateByPrimaryKey(ProductInfo record);

    /**
     *  1.按积分查询前四个热商品
     */
    List<ProductInfo> selectByMaxScore();

    List<ProductInfo> selectByMinScore();

    List<ProductInfo> selectByRandom(int limit,int row);

    int selectRow();

    List<ProductInfo> selectByCartInfos(@Param("cartInfos") List<CartInfo> cartInfos);

    List<ProductInfo> selectByProdIds( @Param("prods") List<String> prods);

}