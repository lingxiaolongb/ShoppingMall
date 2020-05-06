package com.itlong.service;

import com.itlong.bean.UserGoodsAddress;

import java.util.List;

public interface UserGoodsAddressService {

    List<UserGoodsAddress> selectByUserId(String userId);

    List<String> selectUgdId(String userId);

    int updateByPrimaryKey(String newUgdId,String userId);

    int deleteByPrimaryKey(String ugdId,String userId);

    int selectRow(String userId);

    UserGoodsAddress selectByDefaultAddress(String userId);

    int saveUserAddress(UserGoodsAddress userGoodsAddress);

    int updateUserAddress(UserGoodsAddress userGoodsAddress);
}
