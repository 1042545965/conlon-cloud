package conlon.cloud.auth.event.listener;

import conlon.cloud.auth.event.SysMenuRoleUpdateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by zhurudong on 2017/10/22.
 */
@Component
@Slf4j
public class StockPaymentStatusUpdateListener implements ApplicationListener<SysMenuRoleUpdateEvent> {

    @Override
    public void onApplicationEvent(SysMenuRoleUpdateEvent event) {
        log.info("库存服务, 收到支付状态更新的事件" + event);
    }
}