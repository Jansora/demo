package com.jansora.pulsar.consumer;

import com.jansora.app.repo.core.exception.transform.FormatException;
import com.jansora.app.repo.core.function.DoSomethingWithThrowable;
import com.jansora.app.repo.core.utils.JsonUtils;
import com.jansora.pulsar.dto.Message;
import com.jansora.repo.demo.AbstractDemoFactory;
import io.github.majusko.pulsar.annotation.PulsarConsumer;
import io.github.majusko.pulsar.constant.Serialization;
import io.github.majusko.pulsar.producer.ProducerFactory;
import org.apache.pulsar.client.api.SubscriptionType;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * <Description> Description for ProductDemo <br>
 *
 * @author jansora (zhang.yangyuan) <br>
 * @version 1.0 <br>
 * @email zhangyue1936@gmail.com
 * @transId null
 * @CreateDate 2022/8/1 PM12:13 <br>
 * @since 1.0 <br>
 */
@Component
public class ConsumerDemo extends AbstractDemoFactory {


    @PulsarConsumer(topic="t1")
    public void consumer1(byte[] msg) throws FormatException {
        try {
            System.out.println("t1: " + JsonUtils.fromJson(new String(msg), Message.class));
        }
        catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    protected DoSomethingWithThrowable doSomething(String[] args) throws Throwable {
        return null;
    }
}
