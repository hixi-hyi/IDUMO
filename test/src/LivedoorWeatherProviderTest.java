import com.hixi_hyi.idumo.common.provider.LivedoorWeatherProvider;
import com.hixi_hyi.idumo.core.parts.IDUMOSender;

public class LivedoorWeatherProviderTest {

	public static void main(String args[]) {
		IDUMOSender provider = new LivedoorWeatherProvider(63);
		System.out.println(provider.onCall().next());

	}
}