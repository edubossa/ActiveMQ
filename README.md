ActiveMQ - Support JMS 1.1
==========================

Exemplo de Envio e Consumo de Mensageria, Java Message Service Specification JMS 1.1 
com a integração do Provider ActiveMQ


===================================================================

Configuracões do provider ActiveMQ:

	Passo 01 - Criar Queue: fila.transacao
  
	Passo 02 - Criar Durable Topic Subscribers, com as credenciais:
    
	Client ID: EWS660992
	Subscriber Name: chatSubscriber
	Topic Name: topic.chat


===================================================================

activemq

	Este modulo se conecta com o provider ActiveMQ via JNDI. Sua configuração se encontra no arquivo jndi.properties que fica na pasta Resources.
	Neste projeto temos um exemplo de envio e recebimento de mensageria, em um modulo .jar o qual se comunica com o provider ActiveMQ, fazendo o lookup via JNDI.


===================================================================

activemq-web 

	Este modulo se integra com o servidor de aplicação apache tomcat, o qual e responsavel por se comunicar com o provider ActiveMQ, disponibilizando assim a conexao de acesso, pela injeção de dependencia por anotação.	

	Para este funcionamento, devemos adicionar a seguinte configuração, no arquivo /conf/context.xml do tomcat.

	<Resource name="jms/ConnectionFactory" auth="Container" type="org.apache.activemq.ActiveMQConnectionFactory" description="JMS Connection Factory" factory="org.apache.activemq.jndi.JNDIReferenceFactory" brokerURL="tcp://localhost:61616"  brokerName="EWS-BROKKER"/>
        
	<Resource name="jms/transacao" auth="Container" type="org.apache.activemq.command.ActiveMQQueue" description="Fila-Transacao" factory="org.apache.activemq.jndi.JNDIReferenceFactory" physicalName="fila.transacao"/>
       	 
	<Resource name="jms/chat" auth="Container" type="org.apache.activemq.command.ActiveMQTopic" description="Topic-Chat" factory="org.apache.activemq.jndi.JNDIReferenceFactory" physicalName="topic.chat"/>  	 		


===================================================================

  




