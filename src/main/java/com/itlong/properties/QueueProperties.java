package com.itlong.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "amqp.queue")
public class QueueProperties {

    private String queueName;

    private  boolean durable;

    private boolean exclusive;

    private boolean autoDelete;

    private final Arguments arguments = new Arguments();

    public Arguments getArguments() {
        return arguments;
    }



    public static  class  Arguments {
        private  String xMessageTtl;
        private  String xExpires;
        private  String xMaxLength;
        private  String xMaxLengthBytes;
        private  String xDeadLetterExchange;
        private  String xDeadLetterRoutingKey;
        private  String xMaxPriority;
        private  String xQueueMode;


        public String getxMessageTtl() {
            return xMessageTtl;
        }

        public void setxMessageTtl(String xMessageTtl) {
            this.xMessageTtl = xMessageTtl;
        }

        public String getxExpires() {
            return xExpires;
        }

        public void setxExpires(String xExpires) {
            this.xExpires = xExpires;
        }

        public String getxMaxLength() {
            return xMaxLength;
        }

        public void setxMaxLength(String xMaxLength) {
            this.xMaxLength = xMaxLength;
        }

        public String getxMaxLengthBytes() {
            return xMaxLengthBytes;
        }

        public void setxMaxLengthBytes(String xMaxLengthBytes) {
            this.xMaxLengthBytes = xMaxLengthBytes;
        }

        public String getxDeadLetterExchange() {
            return xDeadLetterExchange;
        }

        public void setxDeadLetterExchange(String xDeadLetterExchange) {
            this.xDeadLetterExchange = xDeadLetterExchange;
        }

        public String getxDeadLetterRoutingKey() {
            return xDeadLetterRoutingKey;
        }

        public void setxDeadLetterRoutingKey(String xDeadLetterRoutingKey) {
            this.xDeadLetterRoutingKey = xDeadLetterRoutingKey;
        }

        public String getxMaxPriority() {
            return xMaxPriority;
        }

        public void setxMaxPriority(String xMaxPriority) {
            this.xMaxPriority = xMaxPriority;
        }

        public String getxQueueMode() {
            return xQueueMode;
        }

        public void setxQueueMode(String xQueueMode) {
            this.xQueueMode = xQueueMode;
        }
    }


    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public boolean isDurable() {
        return durable;
    }

    public void setDurable(boolean durable) {
        this.durable = durable;
    }

    public boolean isExclusive() {
        return exclusive;
    }

    public void setExclusive(boolean exclusive) {
        this.exclusive = exclusive;
    }

    public boolean isAutoDelete() {
        return autoDelete;
    }

    public void setAutoDelete(boolean autoDelete) {
        this.autoDelete = autoDelete;
    }
}
