package com.hixi_hyi.idumo.common.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class URL2XMLParser {

	private URL url;
	private Document doc;

	public URL2XMLParser(String url) throws IOException, JDOMException{
		this.url = new URL(url);
		init();
	}
	public URL2XMLParser(URL url) throws IOException, JDOMException{
		this.url = url;
		init();
	}

	public void init() throws IOException, JDOMException{
		URLConnection con = url.openConnection();
		doc = new SAXBuilder().build(con.getInputStream());
	}

	public Element getRoot(){
		return doc.getRootElement();
	}

	public void output() throws IOException{
	    XMLOutputter outputter = new XMLOutputter();
	    Format format = outputter.getFormat();
	    format.setLineSeparator("\n");
	    format.setIndent("    ");
	    outputter.setFormat(format);
	    outputter.output(doc, System.out);
	}

}
