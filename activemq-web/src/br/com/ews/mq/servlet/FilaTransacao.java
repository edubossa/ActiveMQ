/* ========================================================================== *
 * Copyright (c) 2013, Wallsistem Consultoria. All rights reserved.           *
 * Wallsistem Consultoria de Informatica Ltda.  PROPRIETARY/CONFIDENTIAL.     *
 * ========================================================================== */
package br.com.ews.mq.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Recepciona as mensagens e as envia pra a fila: <b>fila.transacao</b>
 * <p>
 * Deve enviar o parametro xml, para a URL abaixo:
 * <p>
 * http://localhost:8080/activemq-web/fila?xml=
 * 
 * @author Eduardo Wallace
 * @since 11/12/2013
 */
@WebServlet("/fila")
public class FilaTransacao extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jms/ConnectionFactory") 
	private ConnectionFactory connectionFactory;
     
	@Resource(name = "jms/transacao") 
	private Destination fila;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilaTransacao() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request, response);
	}
	
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String xml = (request.getParameter("xml") != null) ? request.getParameter("xml") : "<msg> Nenhum Parametro Enviado! </msg>";
		
		//Envia mensagem pro provedor ActiveMQ
		sendMessage(xml);
		
		String msg = "<msg>Mensagem enviada com Sucesso para " + fila.toString() + "</msg>";
		System.out.println(msg);
		System.out.println(xml);
		
		out.println(msg);
		out.println(xml);
		
	}
	
	/**
	 * Envia Mensagem para a Fila: <b>fila.transacao</b>
	 * @param mensagem a ser enviada
	 */
	public void sendMessage(String mensagem) {

		try {
			Connection connection = connectionFactory.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer producer = session.createProducer(fila);
			TextMessage textMessage = session.createTextMessage(mensagem);
			producer.send(textMessage);
			connection.close();

		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

}