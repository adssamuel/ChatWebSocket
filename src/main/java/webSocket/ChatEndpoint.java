package webSocket;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import DAO.MensagensDAO;
import entidade.Mensagens;

@ServerEndpoint("/chat")
public class ChatEndpoint {
	
	@Inject
	private MensagensDAO dao;

    // Sessão → Nome de usuário
    private static Map<Session, String> usuarios = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session) {
        String query = session.getQueryString(); // exemplo: nome=Samuel
        
        String nome = "Anônimo";
        if (query != null && query.startsWith("nome=")) {
            try {
                nome = URLDecoder.decode(query.substring(5), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        usuarios.put(session, nome);
        broadcast("📢 " + nome + " entrou no chat!");
    }

    @OnMessage
    public void onMessage(String msg, Session session) {

        String nomeRemetente = usuarios.get(session);

        Mensagens mensagem = new Mensagens();
        mensagem.setRemetente(nomeRemetente);
        mensagem.setTexto(msg);
        mensagem.setDataHora(LocalDateTime.now());

        dao.salvar(mensagem);
        
        System.out.println("aki");

        // Aqui você reenvia para os clientes etc
    	
        broadcast("💬 " + nomeRemetente + ": " + msg);
    }

    @OnClose
    public void onClose(Session session) {
        String nome = usuarios.get(session);
        usuarios.remove(session);
        broadcast("🚪 " + nome + " saiu do chat.");
    }

    @OnError
    public void onError(Session session, Throwable t) {
        System.err.println("Erro com usuário " + usuarios.get(session) + ": " + t.getMessage());
    }

    private void broadcast(String mensagem) {
        for (Session s : usuarios.keySet()) {
            if (s.isOpen()) {
                s.getAsyncRemote().sendText(mensagem);
            }
        }
    }
}