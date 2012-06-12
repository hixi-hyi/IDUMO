import com.hixi_hyi.idumo.common.converter.Number2GPSConverter;
import com.hixi_hyi.idumo.common.provider.NumberProvider;
import com.hixi_hyi.idumo.console.core.exec.IDUMOConsoleWrapper;
import com.hixi_hyi.idumo.console.core.util.IDUMOConsoleLogger;
import com.hixi_hyi.idumo.console.receiptor.ConsoleViewReceiptor;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.exec.CoreComponent;
import com.hixi_hyi.idumo.core.util.LogManager;

public class Number2GPSTest extends IDUMOConsoleWrapper {
	@Override
	public void init() {
		setExecutionWithComponent(new CoreComponent() {
			@Override
			public void onIdumoMakeFlowChart() throws IDUMOException {
				NumberProvider idumo0 = new NumberProvider(40.0);
				add(idumo0);
				NumberProvider idumo1 = new NumberProvider(132.0);
				add(idumo1);
				Number2GPSConverter idumo2 = new Number2GPSConverter();
				add(idumo2);
				
				
				ConsoleViewReceiptor idumor = new ConsoleViewReceiptor();
				add(idumor);
				
				connect(idumo0, idumo2);
				connect(idumo1, idumo2);
				connect(idumo2, idumor);
				
			}
			
			@Override
			public void onIdumoPrepare() {
				setLoopCount(1);
				setSleepTime(1000);
			}
		});
	}
	
	public static void main(String[] args) {
		LogManager.DEBUG = true;
		LogManager.LOGGER = new IDUMOConsoleLogger();
		Number2GPSTest main = new Number2GPSTest();
		main.exec();
	}
}
