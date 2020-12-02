package conlon.cloud.canal.config;

import com.alibaba.otter.canal.protocol.CanalEntry.EventType;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.AbstractMessageHandler;
import top.javatool.canal.client.handler.EntryHandler;
import top.javatool.canal.client.handler.RowDataHandler;

/**
 * 获取到map 对象后转换成sql，使用jooq执行 sql
 *
 * @author yang peng
 * @date 2019/4/1915:19
 */
@Component
@Slf4j
public class RowDataEntryHandler  extends AbstractMessageHandler {


    public RowDataEntryHandler(List<? extends EntryHandler> entryHandlers,
            RowDataHandler<RowData> rowDataHandler) {
        super(entryHandlers, rowDataHandler);
        log.info("entryHandlers , rowDataHandler " , entryHandlers , rowDataHandler);
    }


}