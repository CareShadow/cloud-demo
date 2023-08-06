package cn.itcast.order.service;

import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        // 4.返回
        return order;
    }

    // OrderService中的方法是不被Sentinel监控的,需要我们自己通过注解来标记要监控的方法
    // 给OrderService的queryGoods方法添加@SentinelResource注解
    @SentinelResource("goods")
    public void queryGoods() {
        System.err.println("查询商品");
    }
}
