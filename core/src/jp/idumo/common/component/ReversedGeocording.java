package jp.idumo.common.component;

import java.net.URL;
import java.net.URLConnection;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;

/**
 * 
 * 緯度経度の情報から現在地を推測するクラス．
 * 
 * 簡易逆ジオコーディングサービス / Finds Webサービス
 * http://www.finds.jp/wsdocs/rgeocode/index.html#APPTOU
 * 
 * @author Hiroyoshi
 * 
 */
public class ReversedGeocording {
	
	private static final String REQUEST_URL = "http://www.finds.jp/ws/rgeocode.php?lat=%f&lon=%f";
	
	private String requestURL;
	private boolean isReady;
	
	private String location;
	
	public ReversedGeocording(double lat, double lon) {
		requestURL = String.format(REQUEST_URL, lat, lon);
		init();
	}
	
	public String getLocation() {
		return location;
	}
	
	public void init() {
		try {
			URL accessURL = new URL(requestURL);
			URLConnection con = accessURL.openConnection();
			Document doc = new SAXBuilder().build(con.getInputStream());
			Element root = doc.getRootElement();
			Namespace ns = root.getNamespace();
			String pref = root.getChild("result", ns).getChild("prefecture", ns).getChildText("pname", ns);
			String mun = root.getChild("result", ns).getChild("municipality", ns).getChildText("mname", ns);
			String sec = root.getChild("result", ns).getChild("local", ns).getChildText("section", ns);
			location = pref + mun + sec;
			isReady = true;
		}
		catch (Exception e) {
			location = "住所が特定できません";
			isReady = true;
		}
	}
	
	public boolean isReady() {
		if (isReady) {
			return true;
		}
		init();
		return isReady;
	}
	
}
