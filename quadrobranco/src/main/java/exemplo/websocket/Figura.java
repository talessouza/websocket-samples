package exemplo.websocket;

import java.io.StringWriter;
import javax.json.Json;
import javax.json.JsonObject;

/**
 * Adaptado de: https://netbeans.org/kb/docs/javaee/maven-websocketapi_pt_BR.html
 * @author TWS <tales (dot) wallace (at) gmail (dot) com>
 */
public class Figura {

    public Figura(JsonObject json) {
        this.json = json;
    }

    private JsonObject json;

    public JsonObject getJson() {
        return json;
    }

    public void setJson(JsonObject json) {
        this.json = json;
    }

    @Override
    public String toString() {
        StringWriter writer = new StringWriter();
        Json.createWriter(writer).write(json);
        return writer.toString();
    }
    
}
