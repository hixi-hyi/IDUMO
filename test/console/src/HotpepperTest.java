import com.hixi_hyi.idumo.android.converter.Number2GPSConverter;
import com.hixi_hyi.idumo.common.component.Hotpepper;
import com.hixi_hyi.idumo.common.handler.raw.NumberGetValueHandler;
import com.hixi_hyi.idumo.common.handler.raw.NumberMoreThanHandler;
import com.hixi_hyi.idumo.common.provider.HotpepperHandler;
import com.hixi_hyi.idumo.common.provider.LivedoorWeatherProvider;
import com.hixi_hyi.idumo.common.provider.NumberProvider;
import com.hixi_hyi.idumo.console.core.exec.IDUMOConsoleWrapper;
import com.hixi_hyi.idumo.console.core.util.IDUMOConsoleLogger;
import com.hixi_hyi.idumo.console.receiptor.ConsoleViewReceiptor;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.exec.IDUMOComponent;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;

public class HotpepperTest extends IDUMOConsoleWrapper {
	@Override
	public void init() {
		setExecutionWithComponent(new IDUMOComponent() {
			@Override
			public void onIdumoMakeFlowChart() throws IDUMOException {

				NumberProvider idumo0 = new NumberProvider(34.67);
				add(idumo0);
				NumberProvider idumo1 = new NumberProvider(135.52);
				add(idumo1);
				Number2GPSConverter idumo2 = new Number2GPSConverter();
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

	public static void main(String[] args) {
		IDUMOLogManager.DEBUG = true;
		IDUMOLogManager.LOGGER = new IDUMOConsoleLogger();
		HotpepperTest main = new HotpepperTest();
		main.exec();
	}
}