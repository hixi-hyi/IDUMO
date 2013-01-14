package jp.console.test;

import jp.idumo.common.parts.provider.ZipNumber2AddressProvider;
import jp.idumo.console.core.exec.ConsoleComponent;
import jp.idumo.console.core.exec.ConsoleWrapper;
import jp.idumo.console.core.util.ConsoleLogger;
import jp.idumo.console.receiptor.ConsoleViewReceiptor;
import jp.idumo.core.exception.IDUMOException;
import jp.idumo.core.util.LogManager;

public class ZipNumber2AddressTest extends ConsoleWrapper {
	public static void main(String[] args) {
		LogManager.DEBUG = true;
		LogManager.LOGGER = new ConsoleLogger();
		ZipNumber2AddressTest main = new ZipNumber2AddressTest();
		main.exec();
	}
	
	@Override
	public void init() {
		setExecutionWithComponent(new ConsoleComponent() {
			@Override
			public void onIdumoMakeFlowChart() throws IDUMOException {
				ZipNumber2AddressProvider idumo1 = new ZipNumber2AddressProvider(9390234);
				add(idumo1);
				ConsoleViewReceiptor idumor = new ConsoleViewReceiptor();
				add(idumor);
				
				connect(idumo1, idumor);
				
			}
			
			@Override
			public void onIdumoPrepare() {
				setLoopCount(1);
				setSleepTime(1000);
			}
		});
	}
}
