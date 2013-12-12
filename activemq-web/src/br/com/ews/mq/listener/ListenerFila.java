/* ========================================================================== *
 * Copyright (c) 2013, Wallsistem Consultoria. All rights reserved.           *
 * Wallsistem Consultoria de Informatica Ltda.  PROPRIETARY/CONFIDENTIAL.     *
 * ========================================================================== */
package br.com.ews.mq.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Listener Fila Transacao
 * @author Eduardo Wallace
 * @since 11/12/2013
 */
public class ListenerFila implements MessageListener {

	@Override
	public void onMessage(Message msg) {
		try {
			TextMessage message = (TextMessage) msg;
			System.out.println("<<<Listener Fila Transacao>>>");
			System.out.println(message.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}