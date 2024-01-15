package com.jansora.demo.rule.drools;

/**
 * @description:
 * @author: jansora (zhang.yangyuan)
 * @date: 2024-01-15 15:57:46
 */

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


/**
 * <p> 规则引擎自动配置类 </p>
 * @author ityml
 * @date 2019/9/10 11:20
 */
@Configuration
public class DroolsAutoConfiguration {

    private static final String RULES_PATH = "rules/";

    private KieServices getKieServices() {
        return KieServices.Factory.get();
    }

    @Bean
    @ConditionalOnMissingBean(KieFileSystem.class)
    public KieFileSystem kieFileSystem() throws IOException {
        KieFileSystem kieFileSystem = getKieServices().newKieFileSystem();
        for (Resource file : getRuleFiles()) {
            kieFileSystem.write(ResourceFactory.newByteArrayResource(Files.readAllBytes(path)));
            kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_PATH + file.getFilename(), "UTF-8"));
        }


        return kieFileSystem;
    }

    private byte[][] getRuleFiles() throws IOException {
//        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
//        return resourcePatternResolver.getResources("classpath*:" + RULES_PATH + "**/*.*");

        String filePath = "/Users/jansora/Documents/Github/demo/rule/src/main/resources/static/test.drl";
        // 将文件路径转化为File对象
        File file = new File(filePath);

        // 将File对象转化为Resource对象
        Resource resource = new ClassPathResource(file.getPath());
        // 检查资源是否存在
        if (resource.exists()) {
            // 执行您的操作，比如读取文件内容
            // ...

            System.out.println("Resource obtained successfully.");
        } else {
            System.out.println("Resource does not exist.");
        }
        return new Resource[] {resource};
    }

    @Bean
    @ConditionalOnMissingBean(KieContainer.class)
    public KieContainer kieContainer() throws IOException {
        final KieRepository kieRepository = getKieServices().getRepository();

        kieRepository.addKieModule(() -> kieRepository.getDefaultReleaseId());

        KieBuilder kieBuilder = getKieServices().newKieBuilder(kieFileSystem());
        kieBuilder.buildAll();

        KieContainer kieContainer = getKieServices().newKieContainer(kieRepository.getDefaultReleaseId());

        return kieContainer;
    }

    @Bean
    @ConditionalOnMissingBean(KieBase.class)
    public KieBase kieBase() throws IOException {
        return kieContainer().getKieBase();
    }

}