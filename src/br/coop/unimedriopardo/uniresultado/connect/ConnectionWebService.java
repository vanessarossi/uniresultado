package br.coop.unimedriopardo.uniresultado.connect;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Date;

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
		
		OutputStream os = connection.getOutputStream();
		try {
			os.write(montarJson(resultado).getBytes());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		os.close();
		//resposta
		InputStream content = new BufferedInputStream(connection.getInputStream());
		String r = IOUtils.toString(content);
				
	}

	public String montarJson(Resultado resultado) throws JSONException {
		JSONObject json = new JSONObject();
		JSONObject message = new JSONObject();
		JSONObject header = new JSONObject();
		JSONObject body = new JSONObject();
		JSONObject registros = new JSONObject();
		JSONArray exames = new JSONArray();
		
		header.put("operadoraOrigem", "354619");
		header.put("prestadorOrigem", resultado.getPrestador().getPrestadorOrigem());
		header.put("sistemaOrigem", resultado.getPrestador().getSistemaPrestador());
		header.put("dataHora", new Date());

		registros.put("tipoOperacao", resultado.getTipoOperacao());
		registros.put("nrCartaoBeneficiario", resultado.getNrCartaoBeneficiario());
		registros.put("nrExecucaoOperadora", resultado.getNrExecucaoOperadora());	
		

		for (Exame exame : resultado.getExames()) {
			JSONObject exameJson = new JSONObject();
			exameJson.put("codigoTabela", exame.getCodigoTabela());
			exameJson.put("codigoExame", exame.getCodigoExame());
			exameJson.put("qtde",exame.getQtde());
			exames.put(exameJson);
		}
		
		registros.put("exames",exames);
		registros.put("anexo", resultado.getAnexo());
		registros.put("formatoArquivo", resultado.getFormatoArquivo());
		body.put("registros", registros);
		message.put("header", header);
		message.put("body", body);
		json.put("message",message);
		
		return json.toString();
	}
}
