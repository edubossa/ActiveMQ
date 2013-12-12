package com.ews.mq;
/* ========================================================================== *
 * Copyright (c) 2013, Wallsistem Consultoria. All rights reserved.           *
 * Wallsistem Consultoria de Informatica Ltda.  PROPRIETARY/CONFIDENTIAL.     *
 * ========================================================================== */
import static org.junit.Assert.fail;

import javax.jms.JMSException;

import org.junit.Test;

import com.ews.mq.Producer;

/**
 * TestCase Envio de Mensagens
 * 
 * @author Eduardo Wallace
 * @since 11/12/2013
 */
public class ProducerTest {

	@Test
	public void sendMensagemTest() {
		
		StringBuilder xml = new StringBuilder();
		xml
		.append("<transacao>")
			.append("<numCartao>ETX-6982</numCartao>")
			.append("<chave>11162354</chave>")
			.append("<validade>02-2015</validade>")
		.append("</transacao>");
		
		try {
			
			new Producer().sendMessage(xml.toString());			
		
		}catch(JMSException e){  
			fail(e.getMessage()); 
		}
	    
	}

}