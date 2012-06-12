import com.hixi_hyi.idumo.common.handler.raw.StringGetValueHandler;
import com.hixi_hyi.idumo.common.provider.LivedoorWeatherProvider;
import com.hixi_hyi.idumo.console.core.exec.ConsoleComponent;
import com.hixi_hyi.idumo.console.core.exec.ConsoleWrapper;
import com.hixi_hyi.idumo.console.core.util.ConsoleLogger;
import com.hixi_hyi.idumo.console.receiptor.ConsoleViewReceiptor;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.util.LogManager;

public class LivedoorGetStringConsoleTest extends ConsoleWrapper {
	public static void main(String[] args) {
		LogManager.DEBUG = true;
		LogManager.LOGGER = new ConsoleLogger();
		LivedoorGetStringConsoleTest main = new LivedoorGetStringConsoleTest();
		main.exec();
	}
	
	@Override
	public void init() {
		setExecutionWithComponent(new LivedoorWeatherConsoleComponent());
	}
}

class LivedoorWeatherConsoleComponent extends ConsoleComponent {
	@Override
	public void onIdumoMakeFlowChart() throws IDUMOException {
		LivedoorWeatherProvider idumo0 = new LivedoorWeatherProvider(63);
		add(idumo0);
		StringGetValueHandler idumo1 = new StringGetValueHandler("min_temp");
		add(idumo1);
		ConsoleViewReceiptor idumo2 = new ConsoleViewReceiptor();
		add(idumo2);
		
		connect(idumo0, idumo1);
		connect(idumo1, idumo2);
		
	}
	
	@Override
	public void onIdumoPrepare() {
		setLoopCount(1);
		setSleepTime(1000);
	}
}
