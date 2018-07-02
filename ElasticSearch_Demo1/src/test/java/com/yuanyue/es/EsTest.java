package com.yuanyue.es;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;
import org.elasticsearch.xpack.core.XPackClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class EsTest {
	
    private static String host="192.168.2.187"; // ��������ַ
    
    private static int port=9300; // �˿�
     
    private TransportClient client=null;
    
    private static List<String> hostList = new ArrayList<String>();
    
    static {
    	hostList.add("192.168.2.187");
    	hostList.add("192.168.2.137");
    }
  
     
    /**
     * ��ȡ����
     * @return
     */
    @SuppressWarnings({ "unchecked", "resource" })
    @Before
    public void getCient()throws Exception{
    	TransportAddress[] transportAddresses = new TransportAddress[hostList.size()];
    	for(int i=0;i<transportAddresses.length;i++) {
    		transportAddresses[i]=new TransportAddress(InetAddress.getByName(EsTest.hostList.get(i)), EsTest.port);
    	}
    	// �趨��Ⱥ����
       client = new PreBuiltXPackTransportClient(Settings.builder().put("cluster.name", "my-elasticsearch")
    		   .put("xpack.security.transport.ssl.enabled", false)
               .put("xpack.security.user", "elastic:qx1234")
               .put("client.transport.sniff", true)
    		   .build())
    		   .addTransportAddresses(transportAddresses);
               //.addTransportAddress(new TransportAddress(InetAddress.getByName(EsTest.host), EsTest.port));
    }
     
    /**
     * �ر�����
     * @param client
     */
    @After
    public void close(){
        if(client!=null){
            client.close();
        }
    }
     
    /**
     * �Դ������������
     */
    @Test
    public void testIndex()throws Exception{
        IndexResponse response =client.prepareIndex("twitter", "tweet", "1")
            .setSource(XContentFactory.jsonBuilder()
                    .startObject()
                    .field("user", "kimchy")
                    .field("postDate", new Date())
                    .field("message", "trying out Elasticsearch")
                .endObject()
                    )
            .get();
        System.out.println("�������ƣ�"+response.getIndex());
        System.out.println("���ͣ�"+response.getType());
        System.out.println("�ĵ�ID��"+response.getId()); // ��һ��ʹ����1
        System.out.println("��ǰʵ��״̬��"+response.status());
    }
     
    /**
     * ֱ��Json�������
     */
    @Test
    public void testIndex2()throws Exception{
        String json = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
            "}";
         
        IndexResponse response =client.prepareIndex("weibo", "tweet")
            .setSource(json,XContentType.JSON)
            .get();
        System.out.println("�������ƣ�"+response.getIndex());
        System.out.println("���ͣ�"+response.getType());
        System.out.println("�ĵ�ID��"+response.getId()); // ��һ��ʹ����1
        System.out.println("��ǰʵ��״̬��"+response.status());
    }
     
    /**
     * ͨ��Map�������
     */
    @Test
    public void testIndex3()throws Exception{
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("user","kimchy");
        json.put("postDate",new Date());
        json.put("message","trying out Elasticsearch");
         
        IndexResponse response =client.prepareIndex("qq", "tweet")
            .setSource(json)
            .get();
        System.out.println("�������ƣ�"+response.getIndex());
        System.out.println("���ͣ�"+response.getType());
        System.out.println("�ĵ�ID��"+response.getId()); // ��һ��ʹ����1
        System.out.println("��ǰʵ��״̬��"+response.status());
    }
     
//    /**
//     * ͨ��Json�����������
//     */
//    @Test
//    public void testIndex4()throws Exception{
//        JsonObject jsonObject=new JsonObject();
//        jsonObject.addProperty("user", "kimchy");
//        jsonObject.addProperty("postDate", "1989-11-11");
//        jsonObject.addProperty("message", "trying out Elasticsearch");
//         
//        IndexResponse response =client.prepareIndex("qq", "tweet")
//            .setSource(jsonObject.toString(),XContentType.JSON)
//            .get();
//        System.out.println("�������ƣ�"+response.getIndex());
//        System.out.println("���ͣ�"+response.getType());
//        System.out.println("�ĵ�ID��"+response.getId()); // ��һ��ʹ����1
//        System.out.println("��ǰʵ��״̬��"+response.status());
//    }
    
    /**
     * �����������ƣ�����ĵ�ID����ȡ����
     */
    @Test
    public void testGet(){
        GetResponse getResponse=client.prepareGet("twitter", "tweet", "1").get();
        System.out.println(getResponse.getSourceAsString());
    }
    /**
     * �����������ƣ�����ĵ�ID����������
     */
    @Test
    public void testUpdate(){
    	Map<String,String> jsonObject = new HashMap<String, String>();
        jsonObject.put("user", "���");
        jsonObject.put("postDate", "1989-11-11");
        jsonObject.put("message", "ѧϰElasticsearch");
         
        UpdateResponse response = client.prepareUpdate("twitter", "tweet", "1").setDoc(jsonObject).get();
        System.out.println("�������ƣ�"+response.getIndex());
        System.out.println("���ͣ�"+response.getType());
        System.out.println("�ĵ�ID��"+response.getId()); // ��һ��ʹ����1
        System.out.println("��ǰʵ��״̬��"+response.status());
    }
    /**
     * ɾ������
     */
    @Test
    public void testDelete(){
        DeleteResponse response=client.prepareDelete("twitter", "tweet", "1").get();
        System.out.println("�������ƣ�"+response.getIndex());
        System.out.println("���ͣ�"+response.getType());
        System.out.println("�ĵ�ID��"+response.getId()); // ��һ��ʹ����1
        System.out.println("��ǰʵ��״̬��"+response.status());
    }
    
    
    /**
     * �������� ����ĵ�
     * @throws Exception
     */
    @Test
    public void testIndexDoc(){
        JSONArray jsonArray=new JSONArray();
         
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("title", "ǰ��3���ټ�ǰ��");
        jsonObject.put("publishDate", "2017-12-29");
        jsonObject.put("content", "һ�Ժû������ƣ����� �Σ�����ɣ�֣�� �Σ���Ů�Ѷ���Ϊһ��С��������֣����ҡ��ܾ���أ������ϴ���������ҹ�ꡢ�ɶ��뽻������Ϸŷ������ڶ�����������ף���ƽ����ڡ����Ӷ�������һϵ�к�Ц�Ĺ��¡�������Ů��ͬ�ʹ���ȴ���ӡ�����֮�����������Ů�����������మ��ɱ�޾��ڡ�Ȼ����ʵ�ġ�������ȴ����⧲�������һ������������������һ�Ծ������ȫ�������������˶���������յ�ѡ�����ٴ������������Ҳ������");
        jsonObject.put("director", "������");
        jsonObject.put("price", "35");
        jsonArray.add(jsonObject);
         
         
        JSONObject jsonObject2=new JSONObject();
        jsonObject2.put("title", "����֮Ѫ");
        jsonObject2.put("publishDate", "2017-12-29");
        jsonObject2.put("content", "2007�꣬Dr.James�ڰ뵺�����̵�֧�����о������ˡ��о������У������˰����ҷ�������ͻ���ɱ�䣬���뵺������ɱ��������������֯���ӹ������˵��о���Dr.James����������ֻ��Ѱ�󾯷��ı������ع��ֶ������� �Σ��������뿪������Σ��СŮ������������֤�˱�������...ʮ�����һ���ƻ�С˵������֮Ѫ���ĳ��������˺�����������֯�����غ�����ɭ����־�� �Σ�����ɱ���İ뵺�����̵Ķ��ӣ����Լ������������ֶ���������������ʼ�ӽ�һ������ͨ��Ů��Nancy��ŷ������ �Σ��������Ҫ�õ������ϵ����ܡ���������Ļ���������ض���İ�����Ҳ�ٴγ��֣��ڶ�β���֮������ץ��Nancy���ֶ�����ɭ�����ò�������һͬǰȥ��ȣ��ؼ�ʱ��ȴ������ɭ��Ȼ�Ǳ�ɱ���İ뵺�����̵Ķ��ӣ������˵�ʵ���¼Ҳ��������ɭ֮��......");
        jsonObject2.put("director", "������");
        jsonObject2.put("price", "45");
        jsonArray.add(jsonObject2);
         
        JSONObject jsonObject3=new JSONObject();
        jsonObject3.put("title", "�����ս8�����ľ�����ʿ");
        jsonObject3.put("publishDate", "2018-01-05");
        jsonObject3.put("content", "�������ս�����ľ�����ʿ���н�ǰ���������ս��ԭ�����ѡ��ľ��飬������һ����ȫ����Ϯ֮�£��������������׵��� Daisy Ridley �Σ����Ҷ���Լ������Ү�� John Boyega �Σ���������Ĭ������˹���������� Oscar Isaac �Σ���λ�������Ǹ��Եľ� ���ð�չ��¡�ǰ���о���ǿ��ԭ������������Ѱ�����ӵľ��ش�ʦ¬�ˡ������ߣ���ˡ����׶� Mark Hamill �Σ����ں��ߵ�ָ���½���ԭ��ѵ�����Ҷ�������һ�����������ɵ�����Ϊ�������ò��´���Ӫ������Լ��Ĺ�ȥ��������Ĭ����Ҫ��Ӧ��սʿ������Ľ�ɫת������һ��������Ҳ������һЩѪ�Ľ�ѵ��");
        jsonObject3.put("director", "������Լ��ѷ");
        jsonObject3.put("price", "55");
        jsonArray.add(jsonObject3);
         
        JSONObject jsonObject4=new JSONObject();
        jsonObject4.put("title", "���ߵ���ȭ");
        jsonObject4.put("publishDate", "2017-12-29");
        jsonObject4.put("content", "�����ȭ�����ӵİ����������� �Σ��������������ʮ�������������С������ �Σ���һ��ԩ�ң�û�뵽��Ϊһ������ĵ������Ů���廥�����Ա���Һ����˻��ӻ�����������ȭ̳�Ĵ����Ҳ�ҿ��˼�ȭ������ܣ�����һ���鷳�����������ڡ������š������������ǣ����� �Σ���ָ���£�����������������ߵ���ȭ��");
        jsonObject4.put("director", "���� / �ų���");
        jsonObject4.put("price", "35");
        jsonArray.add(jsonObject4);
         
        JSONObject jsonObject5=new JSONObject();
        jsonObject5.put("title", "ս��2");
        jsonObject5.put("publishDate", "2017-07-27");
        jsonObject5.put("content", "���·����ڷ��޸����Ĵ��ϣ����˹���棨�⾩ �Σ�������������¬����������������������Ư��һ��������������������ô����ʱ��һ��ͻ��������������������ļƻ���ͻȻ��������һ�����޹������ң������԰�ȫ���룬ȴ���޷���������Ϊ���˵�ʹ���������ճ��������������������ɱ�е�ͬ��������չ���������������Ŷ����ĳ��������ڵ������𽥸��գ����չ�����ս������Ϊͬ����ս����");
        jsonObject5.put("director", "�⾩");
        jsonObject5.put("price", "38");
        jsonArray.add(jsonObject5);
         
        for(int i=0;i<jsonArray.size();i++){
        	JSONObject jo=jsonArray.getJSONObject(i);
            IndexResponse response=client.prepareIndex("film2", "dongzuo")
                    .setSource(jo.toJSONString(), XContentType.JSON).get();
            System.out.println("�������ƣ�"+response.getIndex());
            System.out.println("���ͣ�"+response.getType());
            System.out.println("�ĵ�ID��"+response.getId());
            System.out.println("��ǰʵ��״̬��"+response.status());
        }
    }
    
    
    /**
     * ��ѯ����
     * @throws Exception
     */
    @Test
    public void searchAll()throws Exception{
        SearchRequestBuilder srb=client.prepareSearch("film").setTypes("dongzuo");
        SearchResponse sr=srb.setQuery(QueryBuilders.matchAllQuery()).execute().actionGet(); // ��ѯ����
        SearchHits hits=sr.getHits();
        for(SearchHit hit:hits){
            System.out.println(hit.getSourceAsString());
        }
    }

    /**
     * ��ҳ��ѯ
     * @throws Exception
     */
    @Test
    public void searchPaging()throws Exception{
        SearchRequestBuilder srb=client.prepareSearch("film").setTypes("dongzuo");
        SearchResponse sr=srb.setQuery(QueryBuilders.matchAllQuery())
        		.setFrom(0).setSize(2)
        		.execute()
        		.actionGet(); // ��ѯ����
        SearchHits hits=sr.getHits();
        for(SearchHit hit:hits){
            System.out.println(hit.getSourceAsString());
        }
    }
    
    /**
     * �����ѯ
     * @throws Exception
     */
    @Test
    public void searchOrderBy()throws Exception{
        SearchRequestBuilder srb=client.prepareSearch("film").setTypes("dongzuo");
        SearchResponse sr=srb.setQuery(QueryBuilders.matchAllQuery())
                .addSort("publishDate", SortOrder.DESC)
                .execute()
                .actionGet(); // ��ҳ��������
        SearchHits hits=sr.getHits();
        for(SearchHit hit:hits){
            System.out.println(hit.getSourceAsString());
        }
    }
    
    /**
     * �����й���
     * @throws Exception
     */
    @Test
    public void searchInclude()throws Exception{
        SearchRequestBuilder srb=client.prepareSearch("film").setTypes("dongzuo");
        SearchResponse sr=srb.setQuery(QueryBuilders.matchAllQuery())
                .setFetchSource(new String[]{"title","price"}, null)
                .execute()
                .actionGet(); // ��ҳ��������
        SearchHits hits=sr.getHits();
        for(SearchHit hit:hits){
            System.out.println(hit.getSourceAsString());
        }
    }
    
    /**
     * ������ѯ
     * @throws Exception
     */
    @Test
    public void searchByCondition()throws Exception{
        SearchRequestBuilder srb=client.prepareSearch("film").setTypes("dongzuo");
        SearchResponse sr=srb.setQuery(QueryBuilders.matchQuery("title", "ս"))
                .setFetchSource(new String[]{"title","price"}, null)
                .execute()
                .actionGet(); // ��ҳ��������
        SearchHits hits=sr.getHits();
        for(SearchHit hit:hits){
            System.out.println(hit.getSourceAsString());
        }
    }
    
    /**
     * ������ѯ����ʵ��
     * @throws Exception
     */
    @Test
    public void searchHighlight()throws Exception{
        SearchRequestBuilder srb=client.prepareSearch("film").setTypes("dongzuo");
        HighlightBuilder highlightBuilder=new HighlightBuilder();
        highlightBuilder.preTags("<h2>");
        highlightBuilder.postTags("</h2>");
        highlightBuilder.field("title");
        SearchResponse sr=srb.setQuery(QueryBuilders.matchQuery("title", "ս"))
                .highlighter(highlightBuilder)
                .setFetchSource(new String[]{"title","price"}, null)
                .execute()
                .actionGet(); // ��ҳ��������
        SearchHits hits=sr.getHits();
        for(SearchHit hit:hits){
            System.out.println(hit.getSourceAsString());
            System.out.println(hit.getHighlightFields());
        }
    }
    
    /**
     * ��������ѯ
     * @throws Exception
     */
    @Test
    public void searchMutil()throws Exception{
        SearchRequestBuilder srb=client.prepareSearch("film").setTypes("dongzuo");
        QueryBuilder queryBuilder=QueryBuilders.matchPhraseQuery("title", "ս");
        QueryBuilder queryBuilder2=QueryBuilders.matchPhraseQuery("content", "����");
        QueryBuilder queryBuilder3=QueryBuilders.matchPhraseQuery("title", "��");
        SearchResponse sr=srb.setQuery(QueryBuilders.boolQuery()
                .must(queryBuilder)
                .must(queryBuilder2)
                .mustNot(queryBuilder3))
            .execute()
            .actionGet(); 
        SearchHits hits=sr.getHits();
        for(SearchHit hit:hits){
            System.out.println(hit.getSourceAsString());
        }
    }
    
    /**
     * ��������ѯshould����
     * @throws Exception
     */
    @Test
    public void searchMutil3()throws Exception{
        SearchRequestBuilder srb=client.prepareSearch("film").setTypes("dongzuo");
        QueryBuilder queryBuilder=QueryBuilders.matchPhraseQuery("title", "ս");
        QueryBuilder queryBuilder2=QueryBuilders.matchPhraseQuery("content", "����");
        QueryBuilder queryBuilder3=QueryBuilders.rangeQuery("publishDate").gt("2018-01-01");
        SearchResponse sr=srb.setQuery(QueryBuilders.boolQuery()
                .must(queryBuilder)
                .should(queryBuilder2)
                .should(queryBuilder3))
            .execute()
            .actionGet(); 
        SearchHits hits=sr.getHits();
        for(SearchHit hit:hits){
            System.out.println(hit.getScore()+":"+hit.getSourceAsString());
        }
    }
    
    /**
     * ��������ѯrange����
     * @throws Exception
     */
    @Test
    public void searchMutil4()throws Exception{
        SearchRequestBuilder srb=client.prepareSearch("film").setTypes("dongzuo");
        QueryBuilder queryBuilder=QueryBuilders.matchPhraseQuery("title", "ս");
        QueryBuilder queryBuilder2=QueryBuilders.rangeQuery("price").lte(40);
        SearchResponse sr=srb.setQuery(QueryBuilders.boolQuery()
                .must(queryBuilder)
                .filter(queryBuilder2))
            .execute()
            .actionGet(); 
        SearchHits hits=sr.getHits();
        for(SearchHit hit:hits){
            System.out.println(hit.getSourceAsString());
        }
    }
    
    /**
     * �����ִʲ�ѯ
     * @throws Exception
     */
    @Test
    public void search()throws Exception{
        SearchRequestBuilder srb=client.prepareSearch("film2").setTypes("dongzuo");
        SearchResponse sr=srb.setQuery(QueryBuilders.matchQuery("title", "������").analyzer("smartcn"))
            .setFetchSource(new String[]{"title","price"}, null)
            .execute()
            .actionGet(); 
        SearchHits hits=sr.getHits();
        for(SearchHit hit:hits){
            System.out.println(hit.getSourceAsString());
        }
    }
    
    /**
     * ���ֶ������ִʲ�ѯ
     * @throws Exception
     */
    @Test
    public void search2()throws Exception{
        SearchRequestBuilder srb=client.prepareSearch("film2").setTypes("dongzuo");
        SearchResponse sr=srb.setQuery(QueryBuilders.multiMatchQuery("ԭ������", "title","content").analyzer("smartcn"))
            .setFetchSource(new String[]{"title","price"}, null)
            .execute()
            .actionGet(); 
        SearchHits hits=sr.getHits();
        for(SearchHit hit:hits){
            System.out.println(hit.getSourceAsString());
        }
    }
    

    
    /**
     * ��ȷƥ��ĳ��ֵ
     * @throws Exception
     */
    @Test
    public void search3()throws Exception{
    	long before = System.currentTimeMillis();
        SearchRequestBuilder srb=client.prepareSearch("movies").setTypes("movie");
        SearchResponse sr = srb.setQuery(QueryBuilders.matchPhraseQuery("title", "Godfather"))
            .execute()
            .actionGet(); 
        SearchHits hits=sr.getHits();
        for(SearchHit hit:hits){
        	System.out.println(hit.getSourceAsMap());
            //System.out.println(hit.getSourceAsString());
        }
        long after = System.currentTimeMillis();
        System.out.println("ʱ�䣺"+(after-before));
    }

}
