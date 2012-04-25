import com.hixi_hyi.idumo.common.handler.raw.NumberGetValueHandler;
import com.hixi_hyi.idumo.common.provider.LivedoorWeatherProvider;
import com.hixi_hyi.idumo.console.core.exec.IDUMOConsoleWrapper;
import com.hixi_hyi.idumo.console.core.util.IDUMOConsoleLogger;
import com.hixi_hyi.idumo.console.receiptor.ConsoleViewReceiptor;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.exec.IDUMOComponent;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;

public class NumberGetTest extends IDUMOConsoleWrapper {
	@Override
	public void init() {
		setExecutionWithComponent(new IDUMOComponent() {
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

	public static void main(String[] args) {
		IDUMOLogManager.DEBUG = true;
		IDUMOLogManager.LOGGER = new IDUMOConsoleLogger();
		NumberGetTest main = new NumberGetTest();
		main.exec();
	}
}
