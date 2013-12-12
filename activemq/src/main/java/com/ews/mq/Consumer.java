/* ========================================================================== *
 * Copyright (c) 2013, Wallsistem Consultoria. All rights reserved.           *
 * Wallsistem Consultoria de Informatica Ltda.  PROPRIETARY/CONFIDENTIAL.     *
 * ========================================================================== */
package com.ews.mq;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Listener assincrono para consumo das mensagens.
 * 
 * @author Eduardo Wallace
 * @since 11/12/2013
 * @see AbstractActiveMQConnectionFactory
 */
public class Consumer extends AbstractActiveMQConnectionFactory implements MessageListener {
	
	public void initListener() throws JMSException { 
		
		Destination destination = getDestination(JNDIConfig.TRANSACAO);
		MessageConsumer consumer = getSession().createConsumer(destination);
		consumer.setMessageListener(new Consumer());
		getConnection().start();
		System.out.println("Listener Consumer - queue://fila.transacao - [Inicializado]");	
	}
	 
	public static void main(String[] args) {	
		try {
			new Consumer().initListener();
		} catch (JMSException e) {
			e.printStackTrace();
		}
			
	}

	public void onMessage(Message message) {		
		
		try {
			TextMessage msg = (TextMessage) message;
			System.out.println("JMSCorrelationID: " + msg.getJMSCorrelationID());
			System.out.println("JMSMessageID: " + msg.getJMSMessageID());
			System.out.println("JMSTimestamp: " +  msg.getJMSTimestamp());
			System.out.println("MSG recebida: " + msg.getText());
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}

}