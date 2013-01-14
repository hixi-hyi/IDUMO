package jp.idumo.common.component;

import java.io.IOException;

import jp.idumo.common.data.AddressModel;
import jp.idumo.common.util.URL2XMLParser;

import org.jdom.Element;
import org.jdom.JDOMException;

public class ZipNumber2Address {
	
	private static final String	REQUEST_URL_SEED	= "http://api.aoikujira.com/zip/xml/get.php?zn=%d";
	
	private String				requestURL;
	private AddressModel		model;
	private boolean				isReady;
	private URL2XMLParser		parser;
	
	public ZipNumber2Address(int zipNumber) throws IOException, JDOMException {
		requestURL = String.format(REQUEST_URL_SEED, zipNumber);
		init();
	}
	
	public AddressModel getModel() {
		return model;
	}
	
	public void init() throws IOException, JDOMException {
		parser = new URL2XMLParser(requestURL);
		Element root = parser.getRoot();
		// parser.output();
		Element value = root.getChild("value");
		String zip = value.getChildText("zip");
		String ken = value.getChildText("ken");
		String shi = value.getChildText("shi");
		String cho = value.getChildText("cho");
		model = new AddressModel(zip, ken, shi, cho);
		isReady = true;
	}
	
	public boolean isReady() throws IOException, JDOMException {
		if (isReady) {
			return true;
		}
		init();
		return isReady;
	}
}
