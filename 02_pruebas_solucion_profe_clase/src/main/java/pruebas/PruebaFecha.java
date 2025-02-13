package pruebas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PruebaFecha {

	public static void main(String[] args) throws ParseException {
		String fechaString = "2024-10-05";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date fecha = new Date();
		
		fecha = sdf.parse(fechaString);
		System.out.println(fecha);

	}

}
