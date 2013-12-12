package com.ews.mq;
/* ========================================================================== *
 * Copyright (c) 2013, Wallsistem Consultoria. All rights reserved.           *
 * Wallsistem Consultoria de Informatica Ltda.  PROPRIETARY/CONFIDENTIAL.     *
 * ========================================================================== */
import static org.junit.Assert.*;

import javax.jms.JMSException;

import org.junit.Test;

import com.ews.mq.Consumer;

/**
 * TestCase Recebimento de Mensagens
 * 
 * @author Eduardo Wallace
 * @since 11/12/2013
 */
public class ConsumerTest {

	@Test
	public void consomeMensagemTest() {
		
		try {
			new Consumer().initListener();
		} catch (JMSException e) {
			fail(e.getMessage()); 
		}
	}

}