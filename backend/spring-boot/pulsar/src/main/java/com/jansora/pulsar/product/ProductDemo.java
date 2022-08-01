package com.jansora.pulsar.product;

import com.jansora.app.repo.core.function.DoSomethingWithThrowable;
import com.jansora.app.repo.core.utils.DateUtils;
import com.jansora.pulsar.dto.Message;
import com.jansora.repo.demo.AbstractDemoFactory;
import com.jansora.repo.spring.SpringContext;
import io.github.majusko.pulsar.producer.ProducerFactory;
import io.github.majusko.pulsar.producer.PulsarTemplate;
import org.apache.pulsar.client.api.PulsarClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Objects;

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
public class ProductDemo extends AbstractDemoFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductDemo.class);

    @Autowired
    private PulsarTemplate<Message> producer;




    @Override
    protected DoSomethingWithThrowable doSomething(String[] args) throws Throwable {
        if (Objects.isNull(producer)) {
            producer = SpringContext.context.getBean(PulsarTemplate.class);
        }
        return () -> {
            producer.send("t1", new Message("t1: " + DateUtils.formatNow()));
        };
    }
}
