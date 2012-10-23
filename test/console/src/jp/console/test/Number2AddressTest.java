package jp.console.test;

import jp.idumo.common.parts.adapter.Number2GPSAdapter;
import jp.idumo.common.parts.handler.ReversedGeocordingHandler;
import jp.idumo.common.parts.provider.NumberProvider;
import jp.idumo.console.core.exec.ConsoleComponent;
import jp.idumo.console.core.exec.ConsoleWrapper;
import jp.idumo.console.core.util.ConsoleLogger;
import jp.idumo.console.receiptor.ConsoleViewReceiptor;
import jp.idumo.core.exception.IDUMOException;
import jp.idumo.core.util.LogManager;

public class Number2AddressTest extends ConsoleWrapper {
	public static void main(String[] args) {
		LogManager.DEBUG = true;
		LogManager.LOGGER = new ConsoleLogger();
		Number2AddressTest main = new Number2AddressTest();
		main.exec();
	}
	
	@Override
	public void init() {
		setExecutionWithComponent(new ConsoleComponent() {
			@Override
			public void onIdumoMakeFlowChart() throws IDUMOException {
				NumberProvider idumo0 = new NumberProvider(35.4138);
				add(idumo0);
				NumberProvider idumo1 = new NumberProvider(139.4505);
				add(idumo1);
				Number2GPSAdapter idumo2 = new Number2GPSAdapter();
				add(idumo2);
				ReversedGeocordingHandler idumo3 = new ReversedGeocordingHandler();
				add(idumo3);
				
				ConsoleViewReceiptor idumor = new ConsoleViewReceiptor();
				add(idumor);
				
				connect(idumo0, idumo2);
				connect(idumo1, idumo2);
				connect(idumo2, idumo3);
				connect(idumo3, idumor);
				
			}
			
			@Override
			public void onIdumoPrepare() {
				setLoopCount(1);
				setSleepTime(1000);
			}
		});
	}
}
