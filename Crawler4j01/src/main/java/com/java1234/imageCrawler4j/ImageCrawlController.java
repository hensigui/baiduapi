package com.java1234.imageCrawler4j;


import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 * ͼƬ���������
 * @author user
 *
 */
public class ImageCrawlController {

	  public static void main(String[] args) throws Exception {
	

	    String rootFolder =  "c:/crawl"; // �����������ݴ洢λ��
	    int numberOfCrawlers = 7; // ����7�����棬Ҳ����7���߳�
	    String storageFolder = "c:/crawl/data"; // ������ȡ��ͼƬ���ش洢λ��

	    CrawlConfig config = new CrawlConfig(); // ʵ������������

	    config.setCrawlStorageFolder(rootFolder); // ���������ļ��洢λ��

	    /*
	     * ����������ȡ�������ļ� 
	     * ��ΪͼƬ���ڶ������ļ�
	     */
	    config.setIncludeBinaryContentInCrawling(true);

	    String[] crawlDomains = {"http://www.java1234.com/"};

	    /*
	     * ʵ�������������
	     */
	    PageFetcher pageFetcher = new PageFetcher(config); // ʵ����ҳ���ȡ��
	    RobotstxtConfig robotstxtConfig = new RobotstxtConfig(); // ʵ����������������� ����������� user-agent
	    
	    // ʵ������������˶�Ŀ������������ã�ÿ����վ����һ��robots.txt�ļ� �涨�˸���վ��Щҳ�����������Щҳ���ֹ���������Ƕ�robots.txt�淶��ʵ��
	    RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
	    // ʵ�������������
	    CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
	    
	    /*
         * ������������ҳ�棬���ǹ涨�Ĵ����￪ʼ�����������ö������ҳ��
         */
	    for (String domain : crawlDomains) {
	      controller.addSeed(domain);
	    }

	    ImageCrawler.configure(crawlDomains, storageFolder); // ���������������Լ����ش洢λ��

	    /*
	     * �������棬����Ӵ˿̿�ʼִ���������񣬸�����������
	     */
	    controller.start(ImageCrawler.class, numberOfCrawlers);
	  }
	}