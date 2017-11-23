/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flecharoja.blog.websocketsdemo;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.websocket.Session;

/**
 *
 * @author Gerardo Arroyo
 */
@ApplicationScoped
public class ChatSessionHandler {

    private static final Logger LOG = Logger.getLogger(ChatSessionHandler.class.getName());

    private final Set<Session> sesiones = new HashSet<>();

    public void addSession(Session session) {
        sesiones.add(session);
    }

    public void removeSession(Session session) {
        sesiones.remove(session);
    }

    /**
     * Metodo que agrega un mensaje y el id de la session
     *
     * @param message
     * @param id
     */
    void addMessage(String message, String id) {
        for (Session session : sesiones) {
            sendToSession(session, message);
        }
    }

    private void sendToSession(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message + " Session [" + session.getId() + "]");
        } catch (IOException ex) {
            sesiones.remove(session);
            LOG.log(Level.SEVERE, "Error al enviar a sesion " + session.getId(), ex);
        }
    }
}
