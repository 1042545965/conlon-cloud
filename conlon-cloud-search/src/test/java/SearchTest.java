import colon.cloud.search.SearchApplication;
import colon.cloud.search.entity.LogPilotModel;
import colon.cloud.search.service.JestService;
import com.alibaba.fastjson.JSON;
import io.searchbox.core.SearchResult;
import io.searchbox.core.SearchResult.Hit;
import io.searchbox.core.search.aggregation.MetricAggregation;
import java.util.List;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SearchApplication.class)
@Slf4j
public class SearchTest {

    @Autowired
    private JestService jestService;

    private String index_01 = "catalina-2020.03.21";
    private String type_01 = "doc";

    @Test
    public void test01() {
        try {
            String indexMapping = jestService.getIndexMapping(index_01, type_01);
            log.info("indexMapping=={}" + indexMapping);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test02() {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.queryStringQuery("logback").field("message"));
        log.info("searchSourceBuilder===>"+searchSourceBuilder.toString());
        String search = null;
        try {
            search = jestService.search(searchSourceBuilder.toString(), index_01, type_01);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info(search);
    }

    /**
     * 处理返回对应source 的实体信息
     * @Author:Mr conlon
     * @create 202/3/31 11:24
     */
    @Test
    public void test03() {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.queryStringQuery("logback").field("message")).size(10).from(2);
        log.info("searchSourceBuilder===>"+searchSourceBuilder.toString());

        try {
            SearchResult searchResult = jestService.searchResult(searchSourceBuilder.toString(), index_01, type_01);
            List<Hit<LogPilotModel, Void>> hits = searchResult.getHits(LogPilotModel.class);
            for (Hit<LogPilotModel, Void> hit : hits) {
                LogPilotModel pilotModel = hit.source;
                System.out.println("====>"+pilotModel.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}