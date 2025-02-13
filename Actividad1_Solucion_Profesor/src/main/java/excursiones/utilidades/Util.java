package excursiones.utilidades;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	
	public static Date unaFecha(String fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(fecha);
		} catch (Exception e) {
			return null;
		} 
		
	}

}
