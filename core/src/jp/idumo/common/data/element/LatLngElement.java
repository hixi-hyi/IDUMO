package jp.idumo.common.data.element;

import jp.idumo.core.data.DataElement;
import jp.idumo.core.data.raw.NumberRawDataType;

public interface LatLngElement extends DataElement {
	public double getLatitude();
	
	public double getLongitude();
	
	public class LatLngData extends AbstractData implements LatLngElement {
		
		private static final String LAT = "lat";
		private static final String LNG = "lng";
		
		public LatLngData(double lat, double lng) {
			add(new NumberRawDataType(LAT, lat, "latitude"));
			add(new NumberRawDataType(LNG, lng, "longitude"));
		}
		
		@Override
		public double getLatitude() {
			return (Double) getValue(LAT);
		}
		
		@Override
		public double getLongitude() {
			return (Double) getValue(LNG);
		}
		
	}
}
