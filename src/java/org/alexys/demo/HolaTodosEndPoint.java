/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.alexys.demo;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;
import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;

/**
 *
 * @author Alexys
 */
@ServerEndpoint(value="/holaTodos")
public class HolaTodosEndPoint {

    static final Logger LOGGER = Logger.getLogger(HolaTodosEndPoint.class.getName());
    
    @OnOpen
    public void iniciaConexion(){
        LOGGER.info("Iniciando conexion de HolaTodosEndPoint");
    }
    
    @OnMessage
    public String onMessage(String message) {
        LOGGER.log(Level.INFO, message);
        return "Hola a todos con este mensaje:" + message;
    }
    
    @OnClose
    public void finConexion(){
        LOGGER.info("Fin conexion HolaTodosEndPoint");
    }
}
