import conlon.cloud.mq.MqApplication;
import conlon.cloud.mq.service.MqService;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MqApplication.class)
@Slf4j
public class MqTest {

    @Resource(name = "mqCommonService")
    private MqService mqCommonService;

    @Resource(name = "mqOrderService")
    private MqService mqOrderService;

    @Test
    public void test01 (){
        mqCommonService.sendMsg("my_name_is_common");
    }

}