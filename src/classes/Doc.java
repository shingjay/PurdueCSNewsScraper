package classes;

import java.util.ArrayList;

public class Doc {
	private String title;
	private String content;
	private String link;
	private ArrayList<String> imageURLs;
	
	public Doc() { }
	
	public void setTitle(String title) { this.title = title; }
	public void setContent(String content) { this.content = content; }
	public void setLink(String link) { this.link = link; }
	public void addImageURL(String url) { imageURLs.add(url); }
	
	public String getTitle() { return title; } 
	public String getContent() { return content; } 
	public String getLink() { return link; }
	public ArrayList<String> getImageURLs() { return imageURLs; }
}
