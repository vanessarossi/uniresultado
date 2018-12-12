package br.coop.unimedriopardo.uniresultado.connect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import br.coop.unimedriopardo.uniresultado.models.Exame;
import br.coop.unimedriopardo.uniresultado.models.Resultado;
import br.coop.unimedriopardo.uniresultado.models.Usuario;
import br.coop.unimedriopardo.uniresultado.util.ConversorDeData;

public class ConnectionWebService {

	public Boolean enviar(Usuario usuario, Resultado resultado) throws IOException {
		URL url = new URL("http://192.168.0.242:8400/wsprestador/services/laudo_resultado_exame");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		String userCredentials = "admin:f6fdffe48c908deb0f4c3bd36c032e72";
		String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));

		connection.setRequestProperty("Authorization", basicAuth);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setUseCaches(false);
		connection.setDoInput(true);
		connection.setDoOutput(true);

		PrintStream printStream = new PrintStream(connection.getOutputStream());
		boolean statusEnvio = false;
		try {
			printStream.println(montarJson(resultado));
			connection.connect();
			statusEnvio = verificarResposta(connection);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return statusEnvio;
	}

	public String montarJson(Resultado resultado) throws JSONException {
		JSONObject header = new JSONObject();
		header.put("operadoraOrigem", "354619");
		header.put("prestadorOrigem", resultado.getPrestador().getPrestadorOrigem());
		header.put("sistemaOrigem", resultado.getPrestador().getSistemaPrestador());
		header.put("dataHora", new ConversorDeData().formatar(new Date()));

		JSONArray exames = new JSONArray();
		for (Exame exame : resultado.getExames()) {
			JSONObject exameJson = new JSONObject();
			exameJson.put("codigoTabela", exame.getCodigoTabela());
			exameJson.put("codigoExame", exame.getCodigoExame());
			exameJson.put("qtde", exame.getQtde());
			exames.put(exameJson);
		}

		JSONObject registro = new JSONObject();
		registro.put("tipoOperacao", resultado.getTipoOperacao());
		registro.put("nrCartaoBeneficiario", resultado.getNrCartaoBeneficiario());
		registro.put("nrExecucaoOperadora", resultado.getNrExecucaoOperadora());
		registro.put("exames", exames);
		registro.put("formatoArquivo", resultado.getFormatoArquivo());
		registro.put("anexo", resultado.getAnexo());

		JSONArray registros = new JSONArray();
		registros.put(registro);

		JSONObject json = new JSONObject();
		JSONObject message = new JSONObject();
		JSONObject body = new JSONObject();

		body.put("registros", registros);
		message.put("header", header);
		message.put("body", body);
		json.put("message", message);

		return json.toString();
	}

	public Boolean verificarResposta(HttpURLConnection connection) throws IOException, JSONException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((connection.getInputStream())));
		StringBuilder stringBuilder = new StringBuilder();
		String output;
		while ((output = bufferedReader.readLine()) != null) {
			stringBuilder.append(output);
		}
		JSONObject resposta;
		boolean status;
		resposta = new JSONObject(stringBuilder.toString());
		status = (boolean) resposta.get("status");
		//motivo = resposta.get("motivo").toString();
		return status;
	}

}
