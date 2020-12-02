package colon.cloud.search.service;

import io.searchbox.core.SearchResult;
import java.util.List;

/**
 * Created by conlon on 2019-05-17
 */
public interface JestService {

    public boolean createIndex(String indexName) throws Exception;

    public boolean createIndexMapping(String source , String indexName , String typeName) throws Exception;

    public String getIndexMapping(String indexName , String typeName) throws Exception;

    public boolean index(List<Object> objs , String indexName , String typeName) throws Exception;

    public String search(String query , String indexName , String typeName) throws Exception;

    SearchResult searchResult(String query, String indexName, String typeName) throws Exception;

    public Double count(String query , String indexName , String typeName) throws Exception;

    public String get(String id , String indexName , String typeName) throws Exception;

    public boolean deleteIndex(String indexName) throws Exception;

    public boolean delete(String id , String indexName , String typeName) throws Exception;

    public void closeJestClient() throws Exception;

    public <T> Boolean elasticInsert(T t , String indexName , String typeName , String uniqueId) throws Exception;

}