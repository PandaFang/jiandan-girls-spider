package com.github.pandafang.jiandan_girls_spider;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.github.pandafang.tool.FileTool;

/**
 * 爬虫，解析煎蛋网妹子图的url
 * @author panda fang
 * @date 2017-08-26
 * @version 1.0
 */
public class Spider {

	
	private String saveDir;
	
	/**
	 * 
	 * @param saveDir 保存图片的目录
	 */
	public Spider(String saveDir) {
		this.saveDir = saveDir;
	}
	
	public void run(int page) {
        String url = "http://jandan.net/ooxx/page-" + page + "#comments"; 
		try {
			Document document = Jsoup.connect(url)
					.userAgent("Mozilla/5.0 (Windows NT 6.1; rv:30.0) Gecko/20100101 Firefox/30.0") 
					.get();
			Elements elements = document.select(".commentlist");
			elements  = elements.first().getElementsByTag("li");
			int totalNum = 0;
			for (Element el : elements) {
				
				String id = el.id();
				
				Elements viewLinks = el.select(".view_img_link");
				int size = viewLinks.size();
				totalNum += size;
				for (int i = 0; i < size; i++) {
					Element viewLink = viewLinks.get(i);
					String link = viewLink.absUrl("href");
					System.out.println( link);
					String fileName = id + "-" + (i+1) + link.substring(link.lastIndexOf('.'));
					FileTool.downloadByNIO2(link, this.saveDir, fileName);
				}
				
				
			}
			System.out.println(totalNum + "个妹子下载完成");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}
