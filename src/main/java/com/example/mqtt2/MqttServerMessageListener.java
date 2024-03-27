package com.example.mqtt2;

import net.dreamlu.iot.mqtt.codec.MqttPublishMessage;
import net.dreamlu.iot.mqtt.codec.MqttQoS;
import net.dreamlu.iot.mqtt.core.server.event.IMqttMessageListener;
import net.dreamlu.iot.mqtt.core.server.model.Message;
import net.dreamlu.iot.mqtt.spring.server.MqttServerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tio.core.ChannelContext;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

@Service
public class MqttServerMessageListener implements IMqttMessageListener {
    private static final Logger logger = LoggerFactory.getLogger(MqttServerMessageListener.class);

    @Override
    public void onMessage(ChannelContext context, String clientId, String topic, MqttQoS qoS, MqttPublishMessage message) {
        logger.info("clientId:{} message:{} payload:{}", clientId, message, new String(message.getPayload(), StandardCharsets.UTF_8));
        String body = "{\"c\":13,\"s\":1,\"d\":{\"dt\":1,\"ct\":\"09\"}}";
        MqttServerTemplate mqttServerTemplate = SpringUtil.getBean(MqttServerTemplate.class);
        mqttServerTemplate.publish(clientId,topic,body.getBytes());
    }
}
