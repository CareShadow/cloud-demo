package cn.itcast.order.web;

import cn.itcast.feign.clients.UserClient;
import cn.itcast.feign.pojo.User;
import cn.itcast.order.pojo.Order;
import cn.itcast.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("order")
public class OrderController {

   @Autowired
   private OrderService orderService;

   @Autowired
   private RestTemplate restTemplate;

   @Autowired
   private UserClient userClient;

    // @GetMapping("{orderId}")
    // public Order queryOrderByUserId(@PathVariable("orderId") Long orderId) {
    //     // 根据id查询订单并返回
    //     Order order = orderService.queryOrderById(orderId);
    //     // 模块之间的通信
    //     // String url = "http://127.0.0.1:8081/user/"+ order.getUserId();
    //     String url = "http://userservice/user/" + order.getUserId();
    //     User user = restTemplate.getForObject(url, User.class);
    //     order.setUser(user);
    //     return order;
    // }

    @GetMapping("{orderId}")
    public Order queryOrderByUserId(@PathVariable("orderId") Long orderId) {
        // 查询到订单ID
        Order order = orderService.queryOrderById(orderId);
        // 利用UserId查询到相应的User
        User user = userClient.findById(order.getUserId());
        // 把user封装到Order中
        order.setUser(user);
        return order;
    }

    @GetMapping("header")
    @ResponseBody
    public String testFiltersHeader(@RequestHeader("Truth") String truth) {
        return "Truth:" + truth;
    }
}
