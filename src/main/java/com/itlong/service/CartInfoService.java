package com.itlong.service;

import com.itlong.bean.CartInfo;
import com.itlong.bean.UserInfo;

import java.util.List;

public interface CartInfoService {


    int insert(String prodId, String  userId);
    int selectRow();

    List<CartInfo> selectByUserId(String userId);

    CartInfo selectProdDuplicate(String userId,String prodId);

    int deleteItemByProdId(String userId,List<String> prodIds);
}
