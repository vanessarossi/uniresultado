package br.coop.unimedriopardo.uniresultado.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConversorDeData {
	
		public String formatar(Date data) {
			SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-dd HH:MM:SS");
			String dataFormatada = formato.format(data);
			return dataFormatada;
		}

}
