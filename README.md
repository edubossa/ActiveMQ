ActiveMQ - Support JMS 1.1
==========================

Exemplo de Envio e Consumo de Mensageria, Java Message Service Specification JMS 1.1 
com a integração do Provider ActiveMQ


===================================================================

Configuracões provider ActiveMQ:

  1- Criar Queue: fila.transacao
  
  2- Criar Durable Topic Subscribers:
    
    Client ID: EWS660992
    Subscriber Name: chatSubscriber
    Topic Name: topic.chat
    
===================================================================
    
Configurações Servidor Apache Tomcat:

  1- Adicionar a configuração abaixo, no arquivo /conf/context.xml


<Resource name="jms/ConnectionFactory" auth="Container" type="org.apache.activemq.ActiveMQConnectionFactory" description="JMS Connection Factory"
        factory="org.apache.activemq.jndi.JNDIReferenceFactory" brokerURL="tcp://localhost:61616"  brokerName="EWS-BROKKER"/>
        
<Resource name="jms/transacao" auth="Container" type="org.apache.activemq.command.ActiveMQQueue" description="Fila-Transacao"
       	 factory="org.apache.activemq.jndi.JNDIReferenceFactory" physicalName="fila.transacao"/>
       	 
<Resource name="jms/chat" auth="Container" type="org.apache.activemq.command.ActiveMQTopic" description="Topic-Chat"
        factory="org.apache.activemq.jndi.JNDIReferenceFactory" physicalName="topic.chat"/>  


===================================================================  


  




