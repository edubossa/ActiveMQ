/* ========================================================================== *
 * Copyright (c) 2013, Wallsistem Consultoria. All rights reserved.           *
 * Wallsistem Consultoria de Informatica Ltda.  PROPRIETARY/CONFIDENTIAL.     *
 * ========================================================================== */
package com.ews.mq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Abstract Class, responsavel por disponibilizar os seguintes recursos:
 * <ul>
 * 	<li>ConnectionFactory</li>
 * 	<li>Connection</li>
 * 	<li>Session</li>
 * <ul>
 * 
 * @author Eduardo Wallace
 * @since 11/12/2013
 */
public abstract class AbstractActiveMQConnectionFactory {

	private ConnectionFactory connectionFactory;
	private Connection connection;
	private Session session;
	private Context ctx;

	public AbstractActiveMQConnectionFactory() {
		init();
	}
	
	/**
	 * Inicializa os recursos a serem injetados via JNDI
	 */
	private void init() {
		
		try {
			
			if (ctx == null) {
				ctx = new InitialContext();
			}
			
			if (connectionFactory == null) {				
				connectionFactory = (ConnectionFactory) ctx.lookup("ConnectionFactory");
			}
			
			if (connection == null) {				
				connection = connectionFactory.createConnection();
			}
			
			if (session == null) {				
				session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Fabrica de Conexoes com o provider ActiveMQ
	 * @return ConnectionFactory
	 */
	protected ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}
	
	/**
	 * Conexao com o provider ActiveMQ
	 * @return Connection
	 */
	protected Connection getConnection() {
		return connection;
	}
	
	/**
	 * Sessao com o provider ActiveMQ
	 * @return Session
	 */
	protected Session getSession() {
		return session;
	}
	
	/**
	 * Destination configurada no provider ActiveMQ
	 * @param jndi - enum previamente configurado
	 * @return Destination - Fila ou Topico Configurado
	 * @throws NamingException em caso de erro.
	 */
	protected Destination getDestination(JNDIConfig jndi)  { 
		Destination destination = null;
		try {
			destination = (Destination) ctx.lookup(jndi.jndi);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return destination;
	}
	
	/**
	 * Representacao das Fila e Topicos configurados no arquivo: <b>jndi.properties</b>
	 * @author Eduardo Wallace
	 */
	enum JNDIConfig {
		
		/** Fila Transacao **/
		TRANSACAO ("transacao"),
		
		/** Topico Chat **/
		CHAT("chat")
		;
		
		private String jndi;
		
		JNDIConfig(String jndi) {
			this.jndi = jndi;
		}
		
	}

}