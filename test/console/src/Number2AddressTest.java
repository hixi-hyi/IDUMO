import com.hixi_hyi.idumo.common.converter.Number2GPSConverter;
import com.hixi_hyi.idumo.common.handler.ReversedGeocordingHandler;
import com.hixi_hyi.idumo.common.provider.NumberProvider;
import com.hixi_hyi.idumo.console.core.exec.ConsoleComponent;
import com.hixi_hyi.idumo.console.core.exec.ConsoleWrapper;
import com.hixi_hyi.idumo.console.core.util.ConsoleLogger;
import com.hixi_hyi.idumo.console.receiptor.ConsoleViewReceiptor;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.util.LogManager;

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
				Number2GPSConverter idumo2 = new Number2GPSConverter();
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
