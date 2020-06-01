package com.itlong.service;

import com.itlong.bean.CartInfo;

import java.util.List;

public interface CartInfoService {


    int insert(String prodId, String  userId);
    int selectRow();

    List<CartInfo> selectByUserId(String userId);

    int updateNumById(CartInfo cartInfo, String prodId);

    CartInfo selectProdDuplicate(String userId,String prodId);

    int deleteItemByProdId(String userId,List<String> prodIds);


    List<CartInfo> selectCartInfoByUserId(String userId, List<String> prodIds);
}
