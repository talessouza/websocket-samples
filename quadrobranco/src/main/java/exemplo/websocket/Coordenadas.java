package exemplo.websocket;

/**
 * Adaptado de:
 * https://netbeans.org/kb/docs/javaee/maven-websocketapi_pt_BR.html
 *
 * @author TWS <tales (dot) wallace (at) gmail (dot) com>
 */
public class Coordenadas {

    private float x;
    private float y;

    public Coordenadas() {
    }

    public Coordenadas(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
