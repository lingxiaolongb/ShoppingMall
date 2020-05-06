package com.itlong.mapper;

import com.itlong.bean.CartInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("cartInfoMapper")
public interface CartInfoMapper {
    int deleteByPrimaryKey(String cartId);

    int insert(CartInfo record);

    CartInfo selectByPrimaryKey(String cartId);

    List<CartInfo> selectAll();

    int updateByPrimaryKey(CartInfo record);

    int selectRow();

    List<CartInfo> selectByUserId(String userId);

    CartInfo selectProdDuplicate(String userId,String prodId);

    int deleteItemByProdId(@Param("userId") String userId,@Param("prodIds") List<String> prodIds);
}