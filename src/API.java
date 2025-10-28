import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.InaccessibleObjectException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class API {
    private final String BASE_URL = "https://pokeapi.co/api/v2/pokemon/";
    private HttpClient client = null;

    public API() {
        client = HttpClient.newHttpClient();
    }

    public Pokemon getPokemon(String name){
        HttpResponse<String> response = null;
        try{
            HttpRequest request = HttpRequest.newBuilder().GET().uri(new URI(BASE_URL + name)).build();
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        }catch(URISyntaxException e){
            System.out.println("Errore nella creazione dell'URI");
            return null;
        }catch (IOException | InterruptedException e) {
            System.out.println("Errore nella richiesta HTTP");
            return null;
        }

        if(response.body().equals("Not Found")) return null;

        //deserializzazione
        Gson gson = new Gson();
        //String risultato = pokemon.id + " " + pokemon.name + " " + pokemon.types.get(0).getType().getName() + " " + pokemon.types.get(1).getType().getName();

        return gson.fromJson(response.body(), Pokemon.class);
    }
}
