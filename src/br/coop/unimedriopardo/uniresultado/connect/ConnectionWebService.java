package br.coop.unimedriopardo.uniresultado.connect;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;
import br.coop.unimedriopardo.uniresultado.models.Resultado;
import br.coop.unimedriopardo.uniresultado.models.Usuario;

public class ConnectionWebService {

	
	public void connectar(Usuario usuario) throws IOException {
		URL url  = new URL(usuario.getPrestador().getEndpoint());
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		InputStream content = connection.getInputStream();
	}
	
	public JSONObject montarJson(Resultado resultado) throws JSONException {
		JSONObject json = new JSONObject();
		JSONObject message = new JSONObject();
		
		json.put("message", message);

		JSONObject header = new JSONObject();
					header.put("operadoraOrigem", "354619");
					header.put("prestadorOrigem",resultado.getPrestador().getPrestadorOrigem() );
					header.put("sistemaOrigem", resultado.getPrestador().getSistemaPrestador());
					header.put("dataHora", new Date());
					
		message.put("header", header);
					
		JSONObject body = new JSONObject();
		
		message.put("body", body);
		
		JSONObject registros = new JSONObject();
			body.put("registros", registros);

		
			registros.put("tipoOperacao", resultado.getTipoOperacao());
			registros.put("nrCartaoBeneficiario", resultado.getNrCartaoBeneficiario());
			registros.put("nrExecucaoOperadora", resultado.getNrExecucaoOperadora());
			registros.put("anexo", "");
			registros.put("formatoArquivo", resultado.getFormatoArquivo());
		
				
				
		/**		
		JSONObject exames = new JSONObject();
		
			for (Exame exame : resultado.getExames()) {
				exames.put("codigoTabela", exame.getCodigoTabela());
				exames.put("codigoExame", exame.getCodigoExame());
				exames.put("qtde", exame.getQtde());
				

			}
			
			registros.append("exames", exames);
			**/

			System.out.println(json.toString());

		return json;
	}
}
