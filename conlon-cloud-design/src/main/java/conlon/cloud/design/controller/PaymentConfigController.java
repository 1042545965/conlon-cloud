package conlon.cloud.design.controller;


import conlon.cloud.common.utils.Result;
import conlon.cloud.design.from.ThirdPaymentFrom;
import conlon.cloud.design.service.PaymentConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author conlon
 * @since 2021-03-04
 */
@RestController
@RequestMapping("/paymentConfig")
@Validated
@Api(tags = "支付配置控制器")
@Slf4j
public class PaymentConfigController {

    private final PaymentConfigService paymentConfigService;

    @Autowired
    public PaymentConfigController(PaymentConfigService paymentConfigService) {
        this.paymentConfigService = paymentConfigService;
    }

    /**
     * 新增
     */
    @PostMapping(value="/paymentConfigInsert")
    @ApiOperation(value = "新增")
    public Result<String> paymentConfigInsert(@Valid ThirdPaymentFrom thirdPaymentFrom){
        paymentConfigService.configInsert(thirdPaymentFrom);
        return Result.ok();
    }
}