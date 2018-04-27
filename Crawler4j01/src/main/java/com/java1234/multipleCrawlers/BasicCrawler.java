package com.java1234.multipleCrawlers;


import java.util.Set;
import java.util.regex.Pattern;


import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * �Զ�����������Ҫ�̳�WebCrawler�࣬������Щurl���Ա����Լ�������ȡ��ҳ����Ϣ
 * @author user
 *
 */
public class BasicCrawler extends WebCrawler {

  // ����ƥ��ָ���ĺ�׺�ļ�
  private static final Pattern FILTERS = Pattern.compile(
      ".*(\\.(css|js|bmp|gif|jpe?g" + "|png|tiff?|mid|mp2|mp3|mp4" + "|wav|avi|mov|mpeg|ram|m4v|pdf" +
      "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

  private String[] myCrawlDomains; // Ŀ����������

  /**
   * ����ʵ������ǰ���ã�һ���������ǳ�ʼ��һЩ����
   */
  @Override
  public void onStart() {
    myCrawlDomains = (String[]) myController.getCustomData(); // ��ȡ�Զ������� Ŀ����������  �����������������õ�
  }

  /**
   * ���������Ҫ�Ǿ�����Щurl������Ҫץȡ������true��ʾ��������Ҫ�ģ�����false��ʾ����������Ҫ��Url
   * ��һ������referringPage��װ�˵�ǰ��ȡ��ҳ����Ϣ
   * �ڶ�������url��װ�˵�ǰ��ȡ��ҳ��url��Ϣ
   */
  @Override
  public boolean shouldVisit(Page referringPage, WebURL url) {
    String href = url.getURL().toLowerCase(); // �õ�Сд��url
    
    // ����ƥ�䣬����һЩָ����׺���ļ�
    if (FILTERS.matcher(href).matches()) {
      return false;
    }

    // ����Ŀ������ ָ��Ŀ����������
    for (String crawlDomain : myCrawlDomains) {
      if (href.startsWith(crawlDomain)) {
        return true;
      }
    }

    return false;
  }

  /**
   * ����������������Ҫ��ҳ�棬��������ᱻ���ã����ǿ��Ծ���Ĵ������ҳ��
   * page������װ������ҳ����Ϣ
   */
  @Override
  public void visit(Page page) {
    int docid = page.getWebURL().getDocid(); // ��ȡdocid url��Ψһʶ�� ��������
    String url = page.getWebURL().getURL(); // ��ȡurl
    int parentDocid = page.getWebURL().getParentDocid(); // ��ȡ�ϼ�ҳ���docId

    System.out.println("docId:"+docid);
    System.out.println("url:"+url);
    System.out.println("�ϼ�ҳ��docId:"+parentDocid);

    if (page.getParseData() instanceof HtmlParseData) { // �ж��Ƿ���html���� 
      HtmlParseData htmlParseData = (HtmlParseData) page.getParseData(); // ǿ������ת������ȡhtml���ݶ���
      String text = htmlParseData.getText(); // ��ȡҳ�洿�ı�����html��ǩ��
      String html = htmlParseData.getHtml(); // ��ȡҳ��Html
      Set<WebURL> links = htmlParseData.getOutgoingUrls(); // ��ȡҳ���������

      System.out.println("���ı�����: " + text.length());
      System.out.println("html����: " + html.length());
      System.out.println("������Ӹ���: " + links.size());
    }

    System.out.println("======================");
  }
}