package br.coop.unimedriopardo.uniresultado.connect;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.coop.unimedriopardo.uniresultado.models.Exame;
import br.coop.unimedriopardo.uniresultado.models.Resultado;
import br.coop.unimedriopardo.uniresultado.models.Usuario;

public class ConnectionWebService {

	public void enviar(Usuario usuario, Resultado resultado) throws IOException {
		URL url = new URL("http://192.168.0.242:8400/wsprestador/services/laudo_resultado_exame");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		String userCredentials = "admin:f6fdffe48c908deb0f4c3bd36c032e72";
		String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));

		connection.setRequestProperty ("Authorization", basicAuth);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setUseCaches(false);
		connection.setDoInput(true);
		connection.setDoOutput(true);
		
		PrintStream printStream = new PrintStream(connection.getOutputStream());
		try {
			printStream.println(montarJson(resultado));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		connection.connect();
		
		String resposta = new Scanner(connection.getInputStream()).next();
		
		System.out.println(resposta);
	}

	public String montarJson(Resultado resultado) throws JSONException {
		JSONObject json = new JSONObject();
		JSONObject message = new JSONObject();
		JSONObject header = new JSONObject();
		JSONObject body = new JSONObject();
		JSONArray registros = new JSONArray();
		JSONArray exames = new JSONArray();
		
		header.put("operadoraOrigem", "354619");
		header.put("prestadorOrigem", resultado.getPrestador().getPrestadorOrigem());
		header.put("sistemaOrigem", resultado.getPrestador().getSistemaPrestador());
		header.put("dataHora", LocalDateTime.now());
		
		
		for (Exame exame : resultado.getExames()) {
			exames.put(exame.getCodigoTabela());
			exames.put(exame.getCodigoExame());
			exames.put(exame.getQtde());
		}
		
		registros 
		(resultado.getTipoOperacao());
		registros.put( resultado.getNrCartaoBeneficiario());
		registros.put( resultado.getNrExecucaoOperadora());
		registros.put(resultado.getFormatoArquivo());
		registros.put(exames);
		registros.put(resultado.getFormatoArquivo());
		registros.put(resultado.getAnexo());
		
		registros.put(exames);
		body.put("registros",registros);
		message.put("header",header);
		message.put("body",body);
		json.put("message",message);
		
		System.out.println(json.toString());
		return json.toString();
	}
}
