package exemplo.websocket;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * Adaptado de:
 * https://netbeans.org/kb/docs/javaee/maven-websocketapi_pt_BR.html
 *
 * @author TWS <tales (dot) wallace (at) gmail (dot) com>
 */
public class FiguraDecoder implements Decoder.Text<Figura> {

    @Override
    public Figura decode(String stringJson) throws DecodeException {
        JsonObject jsonObject = Json.createReader(new StringReader(stringJson)).readObject();
        return new Figura(jsonObject);
    }

    @Override
    @SuppressWarnings("empty-statement")
    public boolean willDecode(String stringJson) {
        try {
            Json.createReader(new StringReader(stringJson)).readObject();
            return true;
        } catch (JsonException e) {
            System.err.println("Erro: " + e.getMessage());;
            return false;
        }
    }

    @Override
    public void init(EndpointConfig config) {
        System.out.println("init");
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }

}
