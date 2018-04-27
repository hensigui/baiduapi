package com.java1234.collectingData;


import java.util.List;


import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 * ���������
 * @author user
 *
 */
public class LocalDataCollectorController {

  public static void main(String[] args) throws Exception {
   

    String rootFolder = "c:/crawl"; // �����������ݴ洢λ��
    int numberOfCrawlers = 2; // ����7�����棬Ҳ����7���߳�

    CrawlConfig config = new CrawlConfig(); // ������������
    config.setCrawlStorageFolder(rootFolder); // ���������ļ��洢λ��
    config.setMaxPagesToFetch(10);  // �������ҳ���ȡ��
    config.setPolitenessDelay(1000); // ������ȡ���� 1����һ��

    // ʵ�������������
    PageFetcher pageFetcher = new PageFetcher(config); // ʵ����ҳ���ȡ��
    RobotstxtConfig robotstxtConfig = new RobotstxtConfig(); // ʵ����������������� ����������� user-agent
    
    // ʵ������������˶�Ŀ������������ã�ÿ����վ����һ��robots.txt�ļ� �涨�˸���վ��Щҳ�����������Щҳ���ֹ���������Ƕ�robots.txt�淶��ʵ��
    RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
    
    // ʵ�������������
    CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

    controller.addSeed("http://www.zuidaima.com"); // �����������
    
    // �������棬����Ӵ˿̿�ʼִ���������񣬸�����������
    controller.start(LocalDataCollectorCrawler.class, numberOfCrawlers);

    List<Object> crawlersLocalData = controller.getCrawlersLocalData(); // ������߳������������ʱ����ȡ���汾������
    long totalLinks = 0;
    long totalTextSize = 0;
    int totalProcessedPages = 0;
    for (Object localData : crawlersLocalData) {
      CrawlStat stat = (CrawlStat) localData;
      totalLinks += stat.getTotalLinks();
      totalTextSize += stat.getTotalTextSize();
      totalProcessedPages += stat.getTotalProcessedPages();
    }

    // ��ӡ����
    System.out.println("ͳ�����ݣ�");
    System.out.println("�ܴ���ҳ�棺"+totalProcessedPages);
    System.out.println("�����ӳ��ȣ�"+totalLinks);
    System.out.println("���ı����ȣ�"+totalTextSize);
  }
}