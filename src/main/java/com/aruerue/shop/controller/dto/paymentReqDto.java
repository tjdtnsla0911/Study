package com.aruerue.shop.controller.dto;

import java.util.List;

import com.aruerue.shop.model.user.Orders;
import com.aruerue.shop.model.user.OrdersDetail;

public class paymentReqDto {
	Orders orders;
	List<OrdersDetail> ordersDetail;
}
