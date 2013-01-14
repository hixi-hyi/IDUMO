package jp.idumo.common.parts.provider;

import jp.idumo.common.annotation.IDUMOCommon;
import jp.idumo.common.component.ZipNumber2Address;
import jp.idumo.common.data.AddressModel;
import jp.idumo.core.annotation.IDUMOConstructor;
import jp.idumo.core.annotation.IDUMOInfo;
import jp.idumo.core.annotation.IDUMOProvider;
import jp.idumo.core.data.FlowingData;
import jp.idumo.core.data.connect.ConnectDataType;
import jp.idumo.core.data.connect.SingleConnectDataType;
import jp.idumo.core.exception.IDUMORuntimeException;
import jp.idumo.core.parts.Sendable;

@IDUMOCommon
@IDUMOProvider(send = AddressModel.class)
@IDUMOInfo(author = "Yusei SHIGENOBU", display = "住所を取得", summary = "指定した郵便番号から住所を取得する")
public class ZipNumber2AddressProvider implements Sendable {
	private ZipNumber2Address	zip;
	
	@IDUMOConstructor({ "郵便番号" })
	public ZipNumber2AddressProvider(int zipNumber) {
		try {
			zip = new ZipNumber2Address(zipNumber);
		} catch (Exception e) {
			throw new IDUMORuntimeException(e);
		}
	}
	
	@Override
	public boolean isReady() {
		try {
			return zip.isReady();
		} catch (Exception e) {
			throw new IDUMORuntimeException(e);
		}
	}
	
	@Override
	public FlowingData onCall() {
		FlowingData p = new FlowingData();
		p.add(zip.getModel());
		return p;
	}
	
	@Override
	public ConnectDataType sendableType() {
		return new SingleConnectDataType(AddressModel.class);
	}
}
