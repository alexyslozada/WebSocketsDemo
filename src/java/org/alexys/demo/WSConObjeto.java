/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.alexys.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.alexys.objetos.MiObjeto;
import org.alexys.objetos.MiObjetoEncoder;
import org.alexys.objetos.MiObjetoDecoder;
/**
 *
 * @author Alexys
 */
@ServerEndpoint(value="/conObjeto", encoders = {MiObjetoEncoder.class}, decoders = {MiObjetoDecoder.class})
public class WSConObjeto {

    private static final List<Session> conexiones = new ArrayList<>();
    
    @OnMessage
    public void onMessage(MiObjeto miObjeto) {
        for(Session sesion:conexiones){
            try{
                sesion.getBasicRemote().sendObject(miObjeto);
            } catch (IOException | EncodeException ex) {
                Logger.getLogger(WSConObjeto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
        /**
     * Evento que se ejecuta cuando un cliente se conecta
     *
     * @param session La sesion del cliente
     */
    @OnOpen
    public void iniciaSesion(Session session) {
        conexiones.add(session); //Simplemente, lo agregamos a la lista
    }
    
    /**
     * Evento que se ejecuta cuando se pierde una conexion.
     *
     * @param session La sesion del cliente
     */
    @OnClose
    public void finConexion(Session session) {
        if (conexiones.contains(session)) { // se averigua si está en la colección
            try {
                session.close(); //se cierra la conexión
                conexiones.remove(session); // se retira de la lista
            } catch (IOException ex) {
            }
        }
    }

}
