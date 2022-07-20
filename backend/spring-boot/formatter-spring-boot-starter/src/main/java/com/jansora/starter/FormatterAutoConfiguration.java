package com.jansora.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <Description> <br>
 *
 * @author zhang.yangyuan <br>
 * @version 1.0 <br>
 * @CreateDate 2020/11/24 11:54:26  <br>
 * @see com.jansora.starter <br>
 * @since 1.0 <br>
 */
@Configuration
public class FormatterAutoConfiguration {

    @Bean
    public Formatter defaultFormatter() {
        return new DefaultFormatter();
    }
}
