package com.itlong.service;

import com.itlong.bean.OrderInfo;
import com.itlong.dto.ShopInfo;

import java.util.List;

public interface OrderInfoService {

    List<OrderInfo>  selectOrderInfoByUgdId(String userId);

    void UpdateCartWithOrder(String ugdId,String userId, List<ShopInfo> tempShopInfos);


}
