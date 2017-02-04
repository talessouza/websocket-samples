package exemplo.websocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Adaptado de:
 * https://netbeans.org/kb/docs/javaee/maven-websocketapi_pt_BR.html
 *
 * @author TWS <tales (dot) wallace (at) gmail (dot) com>
 */
@ServerEndpoint(value = "/quadrobrancoendpoint", encoders = {FiguraEncoder.class}, decoders = {FiguraDecoder.class})
public class QuadroBranco {

    private static final Set<Session> conectados = Collections.synchronizedSet(new HashSet<Session>());

    @OnMessage
    public void transmissaoFigura(Figura figuraRecebida, Session conexaoDeRecebimento) throws IOException, EncodeException {
        System.out.println("transmissaoFigura: " + figuraRecebida);
        for (Session conexao : conectados) {
            if (!conexao.equals(conexaoDeRecebimento)) {
                conexao.getBasicRemote().sendObject(figuraRecebida);
            }
        }
    }

    @OnMessage
    public void broadcastSnapshot(ByteBuffer data, Session session) throws IOException {
        System.out.println("broadcastBinary: " + data);
        for (Session peer : conectados) {
            if (!peer.equals(session)) {
                peer.getBasicRemote().sendBinary(data);
            }
        }
    }

    @OnOpen
    public void onOpen(Session conexao) {
        conectados.add(conexao);
    }

    @OnClose
    public void onClose(Session conexao) {
        conectados.remove(conexao);
    }

    @OnError
    public void onError(Throwable t) {
        System.err.println("Erro: " + t.getMessage());
    }
}
