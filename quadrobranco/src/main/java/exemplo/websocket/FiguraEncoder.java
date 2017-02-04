package exemplo.websocket;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * Adaptado de:
 * https://netbeans.org/kb/docs/javaee/maven-websocketapi_pt_BR.html
 *
 * @author TWS <tales (dot) wallace (at) gmail (dot) com>
 */
public class FiguraEncoder implements Encoder.Text<Figura> {

    @Override
    public String encode(Figura figura) throws EncodeException {
        return figura.getJson().toString();
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
