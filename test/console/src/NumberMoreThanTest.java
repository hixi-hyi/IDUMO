import com.hixi_hyi.idumo.common.handler.raw.NumberGetValueHandler;
import com.hixi_hyi.idumo.common.handler.raw.NumberMoreThanHandler;
import com.hixi_hyi.idumo.common.provider.LivedoorWeatherProvider;
import com.hixi_hyi.idumo.console.core.exec.ConsoleComponent;
import com.hixi_hyi.idumo.console.core.exec.ConsoleWrapper;
import com.hixi_hyi.idumo.console.core.util.ConsoleLogger;
import com.hixi_hyi.idumo.console.receiptor.ConsoleViewReceiptor;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.util.LogManager;

public class NumberMoreThanTest extends ConsoleWrapper {
	public static void main(String[] args) {
		LogManager.DEBUG = true;
		LogManager.LOGGER = new ConsoleLogger();
		NumberMoreThanTest main = new NumberMoreThanTest();
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
				NumberMoreThanHandler idumo2 = new NumberMoreThanHandler(10);
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
