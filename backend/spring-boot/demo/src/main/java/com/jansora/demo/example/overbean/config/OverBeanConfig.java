package com.jansora.demo.example.overbean.config;

import com.jansora.demo.example.overbean.service.impl.custom.Korean;
import com.jansora.demo.example.overbean.service.impl.product.Asian;
import com.jansora.demo.example.overbean.service.impl.product.European;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;


@Configuration
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class OverBeanConfig implements BeanFactoryPostProcessor {

//    @Autowired
//    AppProperties appProperties;

    private static final List<String> classNames = Arrays.asList(
            Korean.class.getName(),
            Asian.class.getName(),
            European.class.getName()
    );

    /**
     * 排除掉 bean 元信息
     *
     * @param factory bean factory
     */
    public void doExcludeBeans(DefaultListableBeanFactory factory) {
        if (ObjectUtils.isEmpty(factory)) {
            return;
        }

        for (String className : classNames) {
            try {
                Class<?> clazz = Class.forName(className);
                String[] beanNamesForClazz = factory.getBeanNamesForType(clazz);

                for (String beanName : beanNamesForClazz) {
                    System.out.println("remove bean from springContext. beanName: " + beanName);
                    factory.removeBeanDefinition(beanName);
                }
            } catch (ClassNotFoundException e) {
                System.out.println("can't find class of " + className);
            } catch (NoSuchBeanDefinitionException f) {
                System.out.println("can't find bean of " + className);
            }
        }

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        doExcludeBeans((DefaultListableBeanFactory) beanFactory);
    }
}
