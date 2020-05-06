package com.itlong.mapper;

import com.itlong.bean.UserGoodsAddress;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userGoodsAddressMapper")
public interface UserGoodsAddressMapper {


    int insert(UserGoodsAddress record);

    UserGoodsAddress selectByPrimaryKey(String ugdId);

    int updateByPrimaryKey(UserGoodsAddress record);

    int deleteByPrimaryKey(String ugdId,String userId);
    List<UserGoodsAddress> selectAll();




    List<UserGoodsAddress> selectByUserId(String userId);

    List<String> selectUgdId(String userId);

    int selectRow(String userId);


    UserGoodsAddress selectByDefaultAddress(String userId);
    int updateDefaultAddress(UserGoodsAddress record);
}