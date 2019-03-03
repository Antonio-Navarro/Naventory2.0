package com.antoniojnavarro.naventory.app.jsf.websocket;

import java.util.ArrayList;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/push/")
public class PushSocket {

	private ArrayList<Session> sessions;

	@OnMessage
	public void messageReceiver(String message) {
		System.out.println("Received message:" + message);
	}

	@OnOpen
	public void onOpen(Session session) {
		System.out.println("onOpen: " + session.getId());
		sessions.add(session);
		System.out.println("onOpen: Notification list size: " + sessions.size());
	}

}
