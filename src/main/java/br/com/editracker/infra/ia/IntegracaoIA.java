package br.com.editracker.infra.ia;

import java.io.IOException;

import br.com.meditracker.dominio.RepositorioPerguntas;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class IntegracaoIA implements RepositorioPerguntas{
	
	public String responderPerguntas(String pergunta) {
	
	//String apiKey = "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";
    String url = "https://api.openai.com/v1/chat/completions";
    String modelo = "gpt-3.5-turbo-1106";
   
       OkHttpClient client = new OkHttpClient();

       MediaType mediaType = MediaType.parse("application/json");
       String body = "{\"model\": \"" + modelo + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + pergunta + "\"}]}";
       RequestBody requestBody = RequestBody.create(mediaType, body);

       Request request = new Request.Builder()
               .url(url)
               .post(requestBody)
               .addHeader("Authorization", "Bearer " + "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")
               .addHeader("Content-Type", "application/json")
               .build();
       
       try (Response response = client.newCall(request).execute()) {

    	   String responseBody = response.body().string();
           return responseBody;
           
              
       } catch (IOException e) {
    	   
    	   throw new RuntimeException(e);
    	   
       }
}
}
