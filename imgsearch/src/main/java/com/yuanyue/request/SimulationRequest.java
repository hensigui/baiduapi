package com.yuanyue.request;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlFileInput;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
/**
 * 发送图片给谷歌辨识
 * @author yuanyue
 * @Description: 
 * @date 2018年6月29日
 */
public class SimulationRequest {
	private static String uriAPI;
	
	private static String imageUrl = 
			"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530270882940&di=cb78e6d6485e9f06bfa4d369187467bd&imgtype=0&src=http%3A%2F%2Fi-3.497.com%2F2017%2F2%2F13%2Fb5fd63fa-4fcc-4f02-8596-8ca9753ceda0.jpg";

	private static WebClient client;
	
	public static WebClient getClient() {
		if(client==null) {
			client = new WebClient(BrowserVersion.CHROME);
			// 1 启动JS  
			client.getOptions().setJavaScriptEnabled(true);  
		    // 2 禁用Css，可避免自动二次请求CSS进行渲染  
			client.getOptions().setCssEnabled(false);  
		    // 3 启动客户端重定向  
			client.getOptions().setRedirectEnabled(true); 
			client.getOptions().setDownloadImages(false);
			return client;
		}else {
			return client;
		}
	}
	
	static {
		client = getClient();
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		uriAPI = "https://images.ggws.net/searchbyimage?image_url="+URLEncoder.encode(imageUrl,"utf-8")+"&encoded_image=&image_content=&filename=&hl=zh-CN";
		
		try {
			HtmlPage page = client.getPage(uriAPI);
			
			List<HtmlElement> divList=page.getByXPath("//a[@class=\"fKDtNb\"]/.");
			//List<HtmlElement> divList=page.getByXPath("//a/text()");
			
			
	        System.out.println(divList.toString());
	        
	        HtmlForm form = page.getFormByName("form2");
	        HtmlFileInput fileInput = form.getInputByName("file");
	        client.getOptions().setJavaScriptEnabled(true);  
	        
	        fileInput.setValueAttribute("E:\\timg.jpg");
	        //FileUtils.writeStringToFile(new File("e:\\imgresult.html"), page.asXml());
	        client.close(); 
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
