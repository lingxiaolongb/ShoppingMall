package com.itlong.mapper;

import com.itlong.bean.OrderInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("orderInfoMapper")
public interface OrderInfoMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(OrderInfo record);

    OrderInfo selectByPrimaryKey(String orderId);

    List<OrderInfo> selectAll();

    int updateByPrimaryKey(OrderInfo record);

    List<OrderInfo>  selectOrderInfoByUgdId( @Param("ugdIdList") List<String> ugdIdList);

    int insertOrders(List<OrderInfo> list);

    List<OrderInfo> selectOrderById(String orderId);

    int  updateOrderById(String orderId, int value);

    int delItemByOrderId(String orderId);


}