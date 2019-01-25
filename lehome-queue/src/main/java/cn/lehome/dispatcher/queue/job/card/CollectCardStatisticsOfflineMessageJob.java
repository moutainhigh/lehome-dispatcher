package cn.lehome.dispatcher.queue.job.card;

import cn.lehome.base.api.tool.constant.EventConstants;
import cn.lehome.dispatcher.queue.listener.activity.AdvertStatisticsOfflineListener;
import cn.lehome.framework.base.api.core.compoment.jms.SimpleJmsQueueFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.ConnectionFactory;

/**
 * @author yanwenkai
 * @date 2018/11/7
 */
@Configuration
public class CollectCardStatisticsOfflineMessageJob {

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private SimpleJmsQueueFactoryBean simpleJmsQueueFactoryBean;

    @Bean
    public DefaultMessageListenerContainer collectCardStatisticsOfflineListenerContainer() {
        DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
        defaultMessageListenerContainer.setConnectionFactory(connectionFactory);
        defaultMessageListenerContainer.setDestination(simpleJmsQueueFactoryBean.getInstance(EventConstants.ADVERT_STATISTICS_OFFLINE_MESSAGE_EVENT.getTopicName()));
        defaultMessageListenerContainer.setMessageListener(collectCardStatisticsOfflineListener());
        defaultMessageListenerContainer.setSessionTransacted(true);
        return defaultMessageListenerContainer;
    }

    @Bean
    public AdvertStatisticsOfflineListener collectCardStatisticsOfflineListener() {
        return new AdvertStatisticsOfflineListener();
    }
}
