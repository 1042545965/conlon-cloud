package conlon.cloud.canal.config;

import com.alibaba.fastjson.JSON;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.context.CanalContext;
import top.javatool.canal.client.handler.EntryHandler;
import top.javatool.canal.client.model.CanalModel;
import top.javatool.canal.client.util.GenericUtil;

/**
 * 获取到map 对象后转换成sql，使用jooq执行 sql
 *
 * @author yang peng
 * @date 2019/4/1915:19
 */
@CanalTable(value = "all")
@Component
@Slf4j
public class DefaultEntryHandler implements EntryHandler<Map<String, String>> {

    @Override
    public void insert(Map<String, String> map) {
//        Class<Object> tableClass = GenericUtil.getTableClass(this.);
        CanalModel model = CanalContext.getModel();
        log.info("insert model  {}",model);
        log.info("insert commitId  {}",model.getId());
        log.info("insert table  {}",model.getTable());
        log.info("insert database  {}",model.getDatabase());
        log.info("insert message  {}",JSON.toJSONString(map));
    }

    @Override
    public void update(Map<String, String> before, Map<String, String> after) {
        log.info("update before {} ", JSON.toJSONString(before));
        log.info("update after {}", JSON.toJSONString(after));
    }

    @Override
    public void delete(Map<String, String> map) {
        log.info("delete  {}", JSON.toJSONString(map));
    }
}