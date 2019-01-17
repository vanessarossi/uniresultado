package br.coop.unimedriopardo.uniresultado.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConversorDeData {

	public String formatar(Date data, String formatoStr) {
		SimpleDateFormat formato = new SimpleDateFormat(formatoStr);
		String dataFormatada = formato.format(data);
		return dataFormatada;
	}

	public Date formatarStringParaData(String data) {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = formato.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("Converter"+data);
		System.out.println("Convertido"+date);
		return date;
	}
}
