package com.jansora.demo.rule.drools;

import jakarta.annotation.Resource;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DroolsTest implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    public KieBase kieBase;

    @Override
    public void run(String... args) throws Exception {
        while(true){
            KieSession kieSession = kieBase.newKieSession();
//            kieSession.insert(comparisonEntity);

            kieSession.fireAllRules();
            kieSession.dispose();
            Thread.sleep(1000);
        }
    }
}
