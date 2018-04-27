package com.java1234.multipleCrawlers;



import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 * ���߳����������
 * @author user
 *
 */
public class MultipleCrawlerController {
 
  public static void main(String[] args) throws Exception {
  
    String crawlStorageFolder = "c:/crawl"; // �����������ݴ洢λ��

    CrawlConfig config1 = new CrawlConfig(); // ��������ʵ��1
    CrawlConfig config2 = new CrawlConfig(); // ��������ʵ��2

    // ��������ʵ���ļ��ֱ�洢�����ļ���
    config1.setCrawlStorageFolder(crawlStorageFolder + "/crawler1");
    config2.setCrawlStorageFolder(crawlStorageFolder + "/crawler2");

    config1.setPolitenessDelay(1000); // ����1����ȡһ��
    config2.setPolitenessDelay(2000); // ����2����ȡһ��

    config1.setMaxPagesToFetch(5); // ���������ȡҳ��5
    config2.setMaxPagesToFetch(6); // ���������ȡҳ��6

    // ʹ������PageFetcherʵ��
    PageFetcher pageFetcher1 = new PageFetcher(config1);
    PageFetcher pageFetcher2 = new PageFetcher(config2);

    // ʹ��ͬһ��RobotstxtServerʵ��
    RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
    RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher1);

    CrawlController controller1 = new CrawlController(config1, pageFetcher1, robotstxtServer);
    CrawlController controller2 = new CrawlController(config2, pageFetcher2, robotstxtServer);

    // �ֱ�ָ��Ŀ����������
    String[] crawler1Domains = {"http://www.zuidaima.com/"}; 
    String[] crawler2Domains = {"http://www.java1234.com/"}; 

    // �����Զ�������
    controller1.setCustomData(crawler1Domains); 
    controller2.setCustomData(crawler2Domains);

    // ������������ҳ�棬���ǹ涨�Ĵ����￪ʼ�����������ö������ҳ��
    controller1.addSeed("http://www.zuidaima.com/");
    controller1.addSeed("http://www.zuidaima.com/share/p1-s1.htm");

    controller2.addSeed("http://www.java1234.com/");
    controller2.addSeed("http://www.java1234.com/a/bysj/javaweb/");

    // �������棬����Ӵ˿̿�ʼִ���������񣬸�����������  ����Դ��  ������������������
    controller1.startNonBlocking(BasicCrawler.class, 5);
    controller2.startNonBlocking(BasicCrawler.class, 7);

    controller1.waitUntilFinish(); // ֱ�����
    System.out.println("����1�������");

    controller2.waitUntilFinish();  // ֱ�����
    System.out.println("����2�������");
  }
}