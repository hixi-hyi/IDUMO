
import com.hixi_hyi.idumo.common.handler.StringConcatHandler_Prefix;
import com.hixi_hyi.idumo.common.handler.StringConcatHandler_Suffix;
import com.hixi_hyi.idumo.common.provider.StringProvider;
import com.hixi_hyi.idumo.console.core.exec.IDUMOConsoleWrapper;
import com.hixi_hyi.idumo.console.core.util.IDUMOConsoleLogger;
import com.hixi_hyi.idumo.console.receiptor.ConsoleViewReceiptor;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.exec.IDUMOComponent;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;

public class StringConcatTest extends IDUMOConsoleWrapper {
	@Override
	public void init() {
		setExecutionWithComponent(new IDUMOComponent() {
			@Override
			public void onIdumoMakeFlowChart() throws IDUMOException {
				StringProvider idumo0 = new StringProvider("'str_test'");
				add(idumo0);
				StringConcatHandler_Prefix idumo1 = new StringConcatHandler_Prefix(
						"pre-");
				add(idumo1);
				StringConcatHandler_Suffix idumo2 = new StringConcatHandler_Suffix(
						"-suf");
				add(idumo2);
				ConsoleViewReceiptor idumop = new ConsoleViewReceiptor();
				add(idumop);

				connect(idumo0, idumo1);
				connect(idumo1, idumo2);
				connect(idumo2, idumop);

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
		StringConcatTest main = new StringConcatTest();
		main.exec();
	}
}
