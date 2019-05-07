package com.syex.api.mq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;

import javax.jms.Topic;

//MQ configuration class
@Configuration
public class MqConfig {

    @Value("${spring.activemq.user}")
    private String userName;
    @Value("${spring.activemq.password}")
    private String password;
    @Value("${spring.activemq.broker-url}")
    private String brokerURL;

    @Bean
    public Topic topic() {
        return new ActiveMQTopic("market.trade.detail");
    }

    @Bean
    public Topic klinetopic() {
        return new ActiveMQTopic("marketklineadd");
    }

    /**
     * topic模式的ListenerContainer
     * <br/>
     *
     * @param connectionFactory ActiveMQConnectionFactory
     * @return JmsListenerContainerFactory<?>
     * @author Mr.Lming
     * @date 2019/4/24 10:44
     **/
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        //开启topic支持
        bean.setPubSubDomain(true);
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }

    /**
     * queue模式的ListenerContainer
     * <br/>
     *
     * @param connectionFactory ActiveMQConnectionFactory
     * @return JmsListenerContainerFactory<?>
     * @author Mr.Lming
     * @date 2019/4/24 10:44
     **/
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }

    @Bean
    public JmsMessagingTemplate jmsMessagingTemplate(ActiveMQConnectionFactory connectionFactory) {
        return new JmsMessagingTemplate(connectionFactory);
    }
}