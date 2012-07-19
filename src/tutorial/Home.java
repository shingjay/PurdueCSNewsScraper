package tutorial;

import java.io.*;
import java.util.ArrayList;
import javax.xml.*;
import javax.xml.parsers.*;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import org.jsoup.helper.*;
import classes.Doc;

public class Home {
	
	/**
	 * @return a list of articles
	 */
	public static ArrayList<Doc> getNewsArticles() 
	{
		ArrayList<String> urls = new ArrayList<String>();
		ArrayList<Doc> docs = new ArrayList<Doc>();
		
		try {
			// gets all the news url
			String url="http://www.cs.purdue.edu";
			Document doc = Jsoup.connect(url).get();
			Element content = doc.getElementById("feature");
			Elements links = doc.getElementsByClass("title");
			for (Element link : links) 
			{
				String linkhref = link.attr("abs:href");
				Elements temp = link.getElementsByTag("a");				
				urls.add(temp.attr("href"));
			}
			// go through each news url
			for (String _url : urls) 
			{
				Doc _doc = new Doc();
				// go through each url and copy the articles
				Document temp = Jsoup.connect(_url).get();
				Elements elements = temp.getElementsByClass("style1");
					
				_doc.setLink(_url);
				
				String _content = "";
					for(Element _e : elements)
					{
					// to get title
					String _header = (_e.getElementsByTag("h1").text().toString());
					// get the content
					if (_header.length() != 0)
						_doc.setTitle(_header);
					String tempContent = _e.getElementsByTag("p").text().toString();
					if (tempContent.length() != 0)
						_content += tempContent;
				}
				_doc.setContent(_content);								
				docs.add(_doc);
			}	
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return docs;
	}
	
	public static void main(String [] args) 
	{
		ArrayList<Doc> news = getNewsArticles();
		//ConvertXML converter = new ConvertXML();
		ConvertJSON converter = new ConvertJSON();
		System.out.println(converter.convert(news));
	}
	

}
