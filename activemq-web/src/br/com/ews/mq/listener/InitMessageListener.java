/* ========================================================================== *
 * Copyright (c) 2013, Wallsistem Consultoria. All rights reserved.           *
 * Wallsistem Consultoria de Informatica Ltda.  PROPRIETARY/CONFIDENTIAL.     *
 * ========================================================================== */
package br.com.ews.mq.listener;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class ServletContextListener
 * <p>
 * Inicializa os MessageListener's
 * 
 * @author Eduardo Wallace
 * @since 11/12/2013
 */
@WebListener
public class InitMessageListener implements ServletContextListener {
	
	@Resource(name = "jms/ConnectionFactory") 
	private ConnectionFactory connectionFactory;
     
	@Resource(name = "jms/chat") 
	private Topic topic;
	
	@Resource(name = "jms/transacao") 
	private Queue queue;
	
	/** Identificador do Topico Duravel **/
	private static final String ID_CLIENT = "EWS660992";
	
	/** Assinatura do Topico Duravel **/
	 private static final String ASSINATURA_TOPICO = "chatSubscriber";
	
    /**
     * Default constructor. 
     */
    public InitMessageListener() {
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
       
    	try {
    		System.out.println("Inicializando os MessageListener's");
			Connection connection = connectionFactory.createConnection();
			connection.setClientID(ID_CLIENT);
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			//cria topico duravel
			MessageConsumer c1 = session.createDurableSubscriber(topic, ASSINATURA_TOPICO);
			c1.setMessageListener(new ListenerTopic1());
			
			//listener Topic
			MessageConsumer c2 = session.createConsumer(topic);
			c2.setMessageListener(new ListenerTopic2());
			
			//listener topic
			MessageConsumer c3 = session.createConsumer(topic);
			c3.setMessageListener(new ListenerTopic3());
			
			//listener fila
			MessageConsumer c4 = session.createConsumer(queue);
			c4.setMessageListener(new ListenerFila());
			
			connection.start();
			
			System.out.println("MessageListener's Iniciados");
			
    	} catch (JMSException e) {
			e.printStackTrace();
		}
    	
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
    	//--
    }
	
}