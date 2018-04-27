package com.java1234.shutdownCrawler;


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
public class ControllerWithShutdown {

  public static void main(String[] args) throws Exception {
  
    String crawlStorageFolder = "c:/crawl"; // �����������ݴ洢λ��

    int numberOfCrawlers =2; // ����2�����棬Ҳ����2���߳�

    CrawlConfig config = new CrawlConfig(); // ʵ������������

    config.setCrawlStorageFolder(crawlStorageFolder); // ���������ļ��洢λ��

    config.setPolitenessDelay(1000); // 1����һ��

    config.setMaxPagesToFetch(-1); // ������ȡ

    // ʵ�������������
    PageFetcher pageFetcher = new PageFetcher(config); // ʵ����ҳ���ȡ��
    RobotstxtConfig robotstxtConfig = new RobotstxtConfig(); // ʵ����������������� ����������� user-agent
    
    // ʵ������������˶�Ŀ������������ã�ÿ����վ����һ��robots.txt�ļ� �涨�˸���վ��Щҳ�����������Щҳ���ֹ���������Ƕ�robots.txt�淶��ʵ��
    RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
    // ʵ�������������
    CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

    // ������������ҳ�棬���ǹ涨�Ĵ����￪ʼ�����������ö������ҳ��
    controller.addSeed("http://www.java1234.com/");
    controller.addSeed("http://www.java1234.com/a/javaziliao/bishi/");

    // �������棬����Ӵ˿̿�ʼִ���������񣬸����������� ������
    controller.startNonBlocking(BasicCrawler.class, numberOfCrawlers);

    // ��Ϣ5��
    Thread.sleep(10 * 1000);
    
    System.out.println("��Ϣ10��");

    // ֹͣ��ȡ
    controller.shutdown();
    System.out.println("ֹͣ��ȡ");
    
    // �ȴ���������
    controller.waitUntilFinish();
  }
}