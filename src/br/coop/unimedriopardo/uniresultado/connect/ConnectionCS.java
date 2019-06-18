package br.coop.unimedriopardo.uniresultado.connect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import br.coop.unimedriopardo.uniresultado.model.Exame;
import br.coop.unimedriopardo.uniresultado.model.Resultado;

public class ConnectionCS {

	public Resultado pesquisarInformacoesLaudo(String accessNumber) throws IOException {
		URL url = new URL("http://177.87.151.13:8081/api/procedimento/"+accessNumber);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		//add request header
		connection.setRequestProperty("Authorization", "Bearer "+"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJVc2VySWQiOjY4LCJOYW1lIjoiU2VydmnDp28gZGUgSW50ZWdyYcOnw6NvIiwiQXV0aGVudGljYXRpb25UeXBlIjoiSldUIiwiSXNBdXRoZW50aWNhdGVkIjp0cnVlfQ.EpWtvo-eimndUqf3_AeLn9Vp4_4rjJzg4i3m07T0vtI");
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setUseCaches(false);
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.connect();
		
		try {
			if (connection.getResponseCode() == 200) {
				StringBuilder stringBuilderResposta = verificarResposta(connection);
				Resultado resultado = montarResultado(stringBuilderResposta);
				return resultado;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public StringBuilder verificarResposta(HttpURLConnection connection) throws IOException, JSONException {		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((connection.getInputStream())));
		StringBuilder stringBuilder = new StringBuilder();
		String output;
		while ((output = bufferedReader.readLine()) != null) {
			stringBuilder.append(output);
		}
		return stringBuilder;
	}
	
	public Resultado montarResultado(StringBuilder stringBuilder) throws JSONException {
		JSONObject resposta;
		resposta = new JSONObject(stringBuilder.toString());
		Resultado resultado = new Resultado();
		resultado.setNrCartaoBeneficiario(resposta.get("matriculaPaciente").toString());
		resultado.setNrExecucaoOperadora(resposta.get("senhaGuiaOperadora").toString());	
		
		List<Exame> exames = new ArrayList<Exame>();
		Exame exame = new Exame();
		exame.setCodigoExame(resposta.get("codigoAmbCbhpmProcedimento").toString());
		exames.add(exame);
		
		resultado.setExames(exames);
		return resultado;
	}
	
	
}
