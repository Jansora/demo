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
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * <p> 规则引擎自动配置类 </p>
 * @author ityml
 * @date 2019/9/10 11:20
 */
@Configuration
public class DroolsAutoConfiguration {

    public static final String RULES_PATH = "rules/";

    private KieServices getKieServices() {
        return KieServices.Factory.get();
    }

    @Bean
    @ConditionalOnMissingBean(KieFileSystem.class)
    public KieFileSystem kieFileSystem() throws IOException {
        KieFileSystem kieFileSystem = getKieServices().newKieFileSystem();
        for (String filePath : getRuleFiles()) {
//            String virvalPath = System.getProperty("java.io.tmpdir") + filePath;
//            writeBytesToFile(Files.readAllBytes(Paths.get(filePath)), virvalPath);
            kieFileSystem.write(ResourceFactory.newFileResource(filePath));
//            kieFileSystem.write(filePath, Files.readAllBytes(Paths.get(filePath)));
        }
//        for (Resource file : getResourcesRuleFiles()) {
//            kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_PATH + file.getFilename(), "UTF-8"));
//        }
        return kieFileSystem;
    }
    public static Resource[] getResourcesRuleFiles() throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        return resourcePatternResolver.getResources("classpath*:" + RULES_PATH + "**/*.*");
    }

    public static String[] getRuleFiles() throws IOException {
//        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
//        return resourcePatternResolver.getResources("classpath*:" + RULES_PATH + "**/*.*");

        String filePath = "/Users/jansora/Documents/Github/demo/rule/src/main/resources/rules/pom.drl";
        return new String[] {filePath};
    }

    // 将字节数组写入虚拟文件
    private static void writeBytesToFile(byte[] bytes, String filePath) throws IOException {
        Path path = Paths.get(filePath);
        Files.write(path, bytes);
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