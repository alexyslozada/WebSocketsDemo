package org.alexys.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Alexys
 */
@ServerEndpoint("/mensajeATodos")
@Singleton
public class WSDTodos {

    private static final Logger LOGGER = Logger.getLogger(WSDTodos.class.getName());
    
    //Lista de sesiones conectados
    private static final List<Session> conexiones = new ArrayList<>();
    
    /**
     * Evento que se ejecuta cuando un cliente se conecta
     *
     * @param session La sesion del cliente
     */
    @OnOpen
    public void iniciaSesion(Session session) {
        LOGGER.log(Level.INFO, "Iniciando la conexion de {0}", session.getId());
        conexiones.add(session); //Simplemente, lo agregamos a la lista
    }
    
    /**
     * Evento que se ejecuta cuando se pierde una conexion.
     *
     * @param session La sesion del cliente
     */
    @OnClose
    public void finConexion(Session session) {
        LOGGER.info("Terminando la conexion");
        if (conexiones.contains(session)) { // se averigua si está en la colección
            try {
                LOGGER.log(Level.INFO, "Terminando la conexion de {0}", session.getId());
                session.close(); //se cierra la conexión
                conexiones.remove(session); // se retira de la lista
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Enviaremos un mensaje a todos los conectados
     */
    @Schedule(second = "*/10", minute = "*", hour = "*", persistent = false)
    public void notificar() {
        LOGGER.log(Level.INFO, "Enviando notificacion a {0} conectados", conexiones.size());
        String mensaje = "Son las " + (new java.util.Date()) + " y hay " + conexiones.size() + " conectados ";  // el mensaje a enviar
        for (Session sesion : conexiones) { //recorro toda la lista de conectados
            RemoteEndpoint.Basic remote = sesion.getBasicRemote(); //tomo la conexion remota con el cliente...
            try {
                remote.sendText(mensaje); //... y envío el mensajue
            } catch (IOException ex) {
                LOGGER.log(Level.WARNING, null, ex);
            }
        }
    }
    
    /**
     * Solo es un metodo que atiende las peticiones
     *
     * @param mensaje
     */
    @OnMessage
    public void onMessage(String mensaje) {
        for(Session sesionAbierta:conexiones){
            try {
                sesionAbierta.getBasicRemote().sendText("Hola a todos con este mensaje:" + mensaje);
            } catch (IOException ex) {
                LOGGER.log(Level.WARNING, null, ex);
            }
        }
    }
}
