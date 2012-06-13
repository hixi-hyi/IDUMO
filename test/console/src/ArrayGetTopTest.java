import com.hixi_hyi.idumo.common.adapter.Number2GPSAdapter;
import com.hixi_hyi.idumo.common.handler.manifact.ArrayGetTopHandler;
import com.hixi_hyi.idumo.common.handler.raw.SortHandler;
import com.hixi_hyi.idumo.common.provider.HotpepperHandler;
import com.hixi_hyi.idumo.common.provider.NumberProvider;
import com.hixi_hyi.idumo.console.core.exec.ConsoleComponent;
import com.hixi_hyi.idumo.console.core.exec.ConsoleWrapper;
import com.hixi_hyi.idumo.console.core.util.ConsoleLogger;
import com.hixi_hyi.idumo.console.receiptor.ConsoleViewReceiptor;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.util.LogManager;

public class ArrayGetTopTest extends ConsoleWrapper {
	public static void main(String[] args) {
		LogManager.DEBUG = true;
		LogManager.LOGGER = new ConsoleLogger();
		ArrayGetTopTest main = new ArrayGetTopTest();
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
				SortHandler idumo4 = new SortHandler("average");
				add(idumo4);
				ArrayGetTopHandler idumo5 = new ArrayGetTopHandler();
				add(idumo5);
				ConsoleViewReceiptor idumoR = new ConsoleViewReceiptor();
				add(idumoR);
				
				connect(idumo0, idumo2);
				connect(idumo1, idumo2);
				connect(idumo2, idumo3);
				connect(idumo3, idumo4);
				connect(idumo4, idumo5);
				connect(idumo5, idumoR);
				
			}
			
			@Override
			public void onIdumoPrepare() {
				setLoopCount(1);
				setSleepTime(1000);
			}
		});
	}
}
