package jp.idumo.common.parts.receiptor;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import jp.idumo.common.annotation.IDUMOCommon;
import jp.idumo.common.data.element.TextElement;
import jp.idumo.core.annotation.IDUMOReceiptor;
import jp.idumo.core.data.FlowingData;
import jp.idumo.core.data.connect.ConnectDataType;
import jp.idumo.core.data.primitive.StringPrimitiveElement;
import jp.idumo.core.exception.IDUMOException;
import jp.idumo.core.exec.CoreController;
import jp.idumo.core.parts.Executable;
import jp.idumo.core.parts.Receivable;
import jp.idumo.core.parts.Sendable;
import jp.idumo.core.util.LogManager;
import jp.idumo.core.validator.ReceiveValidator;
import jp.idumo.core.validator.ReceiveValidatorSize;
import jp.idumo.core.validator.ReceiveValidatorType;

/**
 * バイト情報をTCP通信を用いて送ることが出来るReceiptor
 * 
 * @author Hiroyoshi HOUCHI
 */
@IDUMOCommon
@IDUMOReceiptor(author = "Hiroyoshi HOUCHI", name = "指定IPにTCP送信", receive = TextElement.class)
public class SendTCPReceiptor implements Receivable, CoreController, Executable {
	private String				ip;
	private int					port;
	private Socket				socket;
	private PrintWriter			pw;
	private OutputStream		outstream;
	private Sendable			sender;
	private ReceiveValidator	vSize	= new ReceiveValidatorSize(1);
	private ReceiveValidator	vType	= new ReceiveValidatorType(1, StringPrimitiveElement.class);
	
	public SendTCPReceiptor(String ip, int port) {
		LogManager.log();
		this.ip = ip;
		this.port = port;
		socket = new Socket();
	}
	
	@Override
	public boolean isReady() {
		LogManager.log();
		return sender.isReady() && socket.isConnected();
	}
	
	@Override
	public void onIdumoStart() {
		LogManager.log();
		try {
			socket.connect(new InetSocketAddress(ip, port));
			outstream = socket.getOutputStream();
			pw = new PrintWriter(new OutputStreamWriter(outstream));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onIdumoStop() {
		try {
			socket.close();
			outstream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public ConnectDataType receivableType() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
	@Override
	public void run() {
		LogManager.log();
		if (!sender.isReady()) {
			return;
		}
		FlowingData data = sender.onCall();
		if (data == null) {
			return;
		}
		for (Object o : data) {
			LogManager.debug(o.toString());
			pw.println(o.toString());
			pw.flush();
		}
	}
	
	@Override
	public void setSender(Sendable... senders) throws IDUMOException {
		vSize.validate(senders);
		vType.validate(senders);
		sender = senders[0];
	}
	
}
