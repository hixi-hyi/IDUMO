


import com.hixi_hyi.idumo.common.handler.raw.IDUMOGetStringValueHandler;
import com.hixi_hyi.idumo.common.provider.LivedoorWeatherProvider;
import com.hixi_hyi.idumo.console.core.exec.IDUMOConsoleWrapper;
import com.hixi_hyi.idumo.console.core.util.IDUMOConsoleLogger;
import com.hixi_hyi.idumo.console.receiptor.ConsoleViewReceiptor;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.exec.IDUMOComponent;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;

public class LivedoorWeatherConsole extends IDUMOConsoleWrapper {
	@Override
	public void init() {
		setExecutionWithComponent(new LivedoorWeatherConsoleComponent());
	}

	public static void main(String[] args) {
		IDUMOLogManager.DEBUG = true;
		IDUMOLogManager.LOGGER = new IDUMOConsoleLogger();
		LivedoorWeatherConsole main = new LivedoorWeatherConsole();
		main.exec();
	}
}

class LivedoorWeatherConsoleComponent extends IDUMOComponent {
	@Override
	public void onIdumoMakeFlowChart() throws IDUMOException {
		LivedoorWeatherProvider idumo0 = new LivedoorWeatherProvider(63);
		add(idumo0);
		IDUMOGetStringValueHandler idumo1 = new IDUMOGetStringValueHandler("weather");
		add(idumo1);
		ConsoleViewReceiptor idumo2 = new ConsoleViewReceiptor();
		add(idumo2);
		
		connect(idumo0,idumo1);
		connect(idumo1, idumo2);

	}

	@Override
	public void onIdumoPrepare() {
		setLoopCount(1);
		setSleepTime(1000);
	}
}
