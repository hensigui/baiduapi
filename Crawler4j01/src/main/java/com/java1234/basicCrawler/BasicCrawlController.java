package com.java1234.basicCrawler;


import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class BasicCrawlController {

	  public static void main(String[] args) throws Exception {

	    String crawlStorageFolder = "c:/crawl"; // �����������ݴ洢λ��
	    int numberOfCrawlers = 7; // ����7�����棬Ҳ����7���߳�

	    CrawlConfig config = new CrawlConfig();  // ʵ�������������ļ�

	    config.setCrawlStorageFolder(crawlStorageFolder); // ���������ļ��洢λ��

	    /*
	     * ���������Ƶ��
	     * ÿ1000���룬Ҳ������������ļ��������1��
	     */
	    config.setPolitenessDelay(1000);

	    /*
	     * �����������ҳ����ȣ�����ר�Żὲ��  ����2 Ϊ����
	     * Ĭ��ֵ-1 �������
	     */
	    config.setMaxDepthOfCrawling(2);

	    /*
	     * ������ȡ�������ҳ�� ��������1000  �����ȡ1000��
	     * Ĭ��ֵ��-1����ʾ������
	     */
	    config.setMaxPagesToFetch(1000);

	    /**
	     * �Ƿ���ȡ�������ļ�������ͼƬ��PDF�ĵ�����Ƶ֮��Ķ��� ��������false ����ȡ
	     * Ĭ��ֵtrue����ȡ
	     */
	    config.setIncludeBinaryContentInCrawling(false);

	    /*
	     * ����������ô���
	     * config.setProxyHost("proxyserver.example.com");  // �����ַ
	     * config.setProxyPort(8080); // ����˿�
	     *
	     * ���ʹ�ô���Ҳ�������������֤  �û���������
	     * config.setProxyUsername(username); config.getProxyPassword(password);
	     */

	    /*
	     * ������ü������ó�true����һ������ͻȻ��ֹ���߱��������ǿ��Իָ���
	     * Ĭ��������false���Ƽ���Ĭ�����ã��������ó�true�����ܻ����ۿۣ�
	     */
	    config.setResumableCrawling(false);

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
        controller.addSeed("http://www.java1234.com/");
        controller.addSeed("http://www.java1234.com/a/kaiyuan/");
        controller.addSeed("http://www.java1234.com/a/bysj/");

        /*
         * �������棬����Ӵ˿̿�ʼִ���������񣬸�����������
         */
	    controller.start(BasicCrawler.class, numberOfCrawlers);
	  }
	}