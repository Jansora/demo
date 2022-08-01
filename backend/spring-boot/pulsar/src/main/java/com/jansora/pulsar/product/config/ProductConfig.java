package com.jansora.pulsar.product.config;

import com.jansora.pulsar.dto.Message;
import io.github.majusko.pulsar.producer.ProducerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <Description> Description for ProductConfig <br>
 *
 * @author jansora (zhang.yangyuan) <br>
 * @version 1.0 <br>
 * @email zhangyue1936@gmail.com
 * @transId null
 * @CreateDate 2022/8/1 PM01:57 <br>
 * @since 1.0 <br>
 */
@Configuration
public class ProductConfig {


    @Bean
    public ProducerFactory producerFactory() {

        return new ProducerFactory()
                .addProducer("t1", Message.class);

    }
}
