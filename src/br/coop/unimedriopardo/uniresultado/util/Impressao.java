package br.coop.unimedriopardo.uniresultado.util;

import java.io.File;

public class Impressao {

	File folder;

	public File criarPasta() {
		// catalina.base
		String folderPath = System.getProperty("catalina.base") + "/webapps/impressaoExame";
		folder = new File(folderPath);
		if (!folder.exists()) {
			folder.mkdir();
		}
		return folder;
	}
	
	public String caminho() {
		return System.getProperty("catalina.base") + "/webapps/impressaoExame";
	}

}
