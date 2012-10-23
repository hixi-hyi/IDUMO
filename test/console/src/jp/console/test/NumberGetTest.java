package jp.console.test;

import jp.idumo.common.parts.handler.raw.NumberGetValueHandler;
import jp.idumo.common.parts.provider.LivedoorWeatherProvider;
import jp.idumo.console.core.exec.ConsoleComponent;
import jp.idumo.console.core.exec.ConsoleWrapper;
import jp.idumo.console.core.util.ConsoleLogger;
import jp.idumo.console.receiptor.ConsoleViewReceiptor;
import jp.idumo.core.exception.IDUMOException;
import jp.idumo.core.util.LogManager;

public class NumberGetTest extends ConsoleWrapper {
	public static void main(String[] args) {
		LogManager.DEBUG = true;
		LogManager.LOGGER = new ConsoleLogger();
		NumberGetTest main = new NumberGetTest();
		main.exec();
	}
	
	@Override
	public void init() {
		setExecutionWithComponent(new ConsoleComponent() {
			@Override
			public void onIdumoMakeFlowChart() throws IDUMOException {
				LivedoorWeatherProvider idumo0 = new LivedoorWeatherProvider(63);
				add(idumo0);
				NumberGetValueHandler idumo1 = new NumberGetValueHandler("max_temp");
				add(idumo1);
				ConsoleViewReceiptor idumoR = new ConsoleViewReceiptor();
				add(idumoR);
				
				connect(idumo0, idumo1);
				connect(idumo1, idumoR);
				
			}
			
			@Override
			public void onIdumoPrepare() {
				setLoopCount(1);
				setSleepTime(1000);
			}
		});
	}
}
