package jp.console.test;

import jp.idumo.common.parts.adapter.Number2GPSAdapter;
import jp.idumo.common.parts.handler.HotpepperHandler;
import jp.idumo.common.parts.provider.NumberProvider;
import jp.idumo.console.core.exec.ConsoleComponent;
import jp.idumo.console.core.exec.ConsoleWrapper;
import jp.idumo.console.core.util.ConsoleLogger;
import jp.idumo.console.receiptor.ConsoleViewReceiptor;
import jp.idumo.core.exception.IDUMOException;
import jp.idumo.core.util.LogManager;

public class HotpepperTest extends ConsoleWrapper {
	public static void main(String[] args) {
		LogManager.DEBUG = true;
		LogManager.LOGGER = new ConsoleLogger();
		HotpepperTest main = new HotpepperTest();
		main.exec();
	}
	
	@Override
	public void init() {
		setExecutionWithComponent(new ConsoleComponent() {
			@Override
			public void onIdumoMakeFlowChart() throws IDUMOException {
				
				NumberProvider idumo0 = new NumberProvider(34.67);
				add(idumo0);
				NumberProvider idumo1 = new NumberProvider(135.52);
				add(idumo1);
				Number2GPSAdapter idumo2 = new Number2GPSAdapter();
				add(idumo2);
				HotpepperHandler idumo3 = new HotpepperHandler();
				add(idumo3);
				ConsoleViewReceiptor idumoR = new ConsoleViewReceiptor();
				add(idumoR);
				
				connect(idumo0, idumo2);
				connect(idumo1, idumo2);
				connect(idumo2, idumo3);
				connect(idumo3, idumoR);
				
			}
			
			@Override
			public void onIdumoPrepare() {
				setLoopCount(1);
				setSleepTime(1000);
			}
		});
	}
}
