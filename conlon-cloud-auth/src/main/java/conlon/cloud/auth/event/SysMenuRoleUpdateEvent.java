package conlon.cloud.auth.event;

import conlon.cloud.auth.entity.SysMenuRole;
import org.springframework.context.ApplicationEvent;

/**
 * 支付状态更新事件
 */
public class SysMenuRoleUpdateEvent extends ApplicationEvent {

    private static final long serialVersionUID = -1959576334565638206L;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public SysMenuRoleUpdateEvent(SysMenuRole source) {
        super(source);
    }
}