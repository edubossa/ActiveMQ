/* ========================================================================== *
 * Copyright (c) 2013, Wallsistem Consultoria. All rights reserved.           *
 * Wallsistem Consultoria de Informatica Ltda.  PROPRIETARY/CONFIDENTIAL.     *
 * ========================================================================== */
package com.ews.mq;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;
import javax.naming.NamingException;

/**
 * Producer para envio de mensagens
 * 
 * @author Eduardo Wallace
 * @since 11/12/2013
 * @see AbstractActiveMQConnectionFactory
 */
public class Producer extends AbstractActiveMQConnectionFactory {
	
	/**
	 * Envia Mensagem para a Fila
	 * @param mensagem a ser enviada
	 * @throws NamingException 
	 * @throws JMSException 
	 */
	public void sendMessage(String mensagem) throws JMSException { 

		Destination destination = getDestination(JNDIConfig.TRANSACAO);
		MessageProducer producer = getSession().createProducer(destination);
		TextMessage message = getSession().createTextMessage(mensagem);
		message.setJMSMessageID("EWS-ID99898");
		
		//producer.setTimeToLive(60000);
		producer.send(message);
		
		System.out.println(mensagem);
		System.out.println("Mensagem Enviada Com Sucesso!!!");
		getConnection().close(); //TODO Verificar close connection

	}

}