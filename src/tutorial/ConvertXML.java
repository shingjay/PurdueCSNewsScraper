package tutorial;

import classes.*;
import org.w3c.dom.*;
import java.util.*;

import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class ConvertXML {
	
	public ConvertXML() {}
	
	// parses to the arraylist into an XML file
	public boolean convert(ArrayList<Doc> docs) 
	{
		try 
		{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document document = docBuilder.newDocument();
			
			// article will be the root
			Element rootElement = document.createElement("PurdueCS");
			document.appendChild(rootElement);
			
			int i = 0;
			// iterate through each article and add them into the article
			for(Doc d : docs)
			{
				Element article = document.createElement("Article");
				rootElement.appendChild(article);				
				article.setAttribute("id", "" + i++);
				
				Element title = document.createElement("Title");
				title.appendChild(document.createTextNode(d.getTitle()));
				article.appendChild(title);
				
				Element url = document.createElement("URL");
				url.appendChild(document.createTextNode(d.getLink()));
				article.appendChild(url);
				
				Element content = document.createElement("Content");
				content.appendChild(document.createTextNode(d.getContent()));
				article.appendChild(content);
			}
			
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File("test.xml"));
			
			t.transform(source, result);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
}
