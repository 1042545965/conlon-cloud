package conlon.cloud.mq.controller;

import conlon.cloud.mq.service.MqService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mq/common")
public class MqCommonController {

    @Resource(name = "mqCommonService")
    private MqService mqCommonService;

    @Resource(name = "mqOrderService")
    private MqService mqOrderService;

    @GetMapping("/sendCommonMsg")
    public String sendCommonMsg() {
        mqCommonService.sendMsg("my_name_is_common");
        return "success";
    }

    @GetMapping("/sendOrderMsg")
    public String sendOrderMsg() {
        mqOrderService.sendMsg("my_name_is_order");
        return "success";
    }

}