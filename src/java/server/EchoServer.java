package server;

import java.io.IOException;
 
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session; //The session class allows us to sens messages to the client
import javax.websocket.server.ServerEndpoint;
/**
 *
 * @author joabemendes
 */
@ServerEndpoint("/echo")
public class EchoServer {
    /*
        @OnOpen allows us to intercept the creation of a new session.
    */
    @OnOpen
    public void onOpen(Session session){
        System.out.println(session.getId() + "started a connection");
        try{
            session.getBasicRemote().sendText("Connection stablished");
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    /* 
        @OnMessage can receive the message from the client and answer it.
    */
    @OnMessage
    public void onMessage(String message, Session session){
        System.out.println("Message from " + session.getId() + ": "+message);
        try{
            //Send something to the client
            //Here i could treat a request or answer a query
            session.getBasicRemote().sendText("We got the message: "+message);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    /*
        @OnClose is fired when the session is closed by the client
    */
    @OnClose
    public void onClose(Session session){
        System.out.println("Connection ended");
    }
    
}
