package br.coop.unimedriopardo.uniresultado.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConversorData {
	
		public Date formatar(Date date, String formato) {
			SimpleDateFormat format = new SimpleDateFormat(formato);
			Date data = null;
			try {
				data = format.parse(date.toString());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return data;
		}

}
