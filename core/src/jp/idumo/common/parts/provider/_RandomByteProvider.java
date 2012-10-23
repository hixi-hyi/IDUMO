package jp.idumo.common.parts.provider;

import java.util.Random;

import jp.idumo.core.data.FlowingData;
import jp.idumo.core.data.connect.ConnectDataType;
import jp.idumo.core.data.connect.SingleConnectDataType;
import jp.idumo.core.data.primitive.NumberPrimitiveElement;
import jp.idumo.core.parts.Sendable;


/**
 * ランダムなバイト情報を送るためのProvider(DebugClass)
 *
 * @author Hiroyoshi HOUCHI
 *
 */
public class _RandomByteProvider implements Sendable {

	private Random ramdom;

	public _RandomByteProvider() {
		ramdom = new Random();
	}

	@Override
	public boolean isReady() {
		return true;
	}

	@Override
	public FlowingData onCall() {
		// LogUtil.d();
		FlowingData pipes = new FlowingData();
		byte buf[] = new byte[1];
		ramdom.nextBytes(buf);
		pipes.add(new NumberPrimitiveElement.NumberPrimitiveData(buf[0]));
		return pipes;
	}

	@Override
	public ConnectDataType sendableType() {
		return new SingleConnectDataType(NumberPrimitiveElement.class);
	}

}
