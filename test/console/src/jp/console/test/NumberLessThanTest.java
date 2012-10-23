package jp.console.test;

import jp.idumo.common.parts.handler.raw.NumberGetValueHandler;
import jp.idumo.common.parts.handler.raw.NumberLessThanHandler;
import jp.idumo.common.parts.provider.LivedoorWeatherProvider;
import jp.idumo.console.core.exec.ConsoleComponent;
import jp.idumo.console.core.exec.ConsoleWrapper;
import jp.idumo.console.core.util.ConsoleLogger;
import jp.idumo.console.receiptor.ConsoleViewReceiptor;
import jp.idumo.core.exception.IDUMOException;
import jp.idumo.core.util.LogManager;

public class NumberLessThanTest extends ConsoleWrapper {
	public static void main(String[] args) {
		LogManager.DEBUG = true;
		LogManager.LOGGER = new ConsoleLogger();
		NumberLessThanTest main = new NumberLessThanTest();
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
				NumberLessThanHandler idumo2 = new NumberLessThanHandler(10);
				add(idumo2);
				ConsoleViewReceiptor idumoR = new ConsoleViewReceiptor();
				add(idumoR);
				
				connect(idumo0, idumo1);
				connect(idumo1, idumo2);
				connect(idumo2, idumoR);
				
			}
			
			@Override
			public void onIdumoPrepare() {
				setLoopCount(1);
				setSleepTime(1000);
			}
		});
	}
}
