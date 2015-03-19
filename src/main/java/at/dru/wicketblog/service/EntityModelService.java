package at.dru.wicketblog.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
public class EntityModelService implements InitializingBean {

    public void scanEntities() {
        BeanDefinitionRegistry registry = new SimpleBeanDefinitionRegistry();
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry, false);
        scanner.setResourcePattern("**/*.class");
        int found = scanner.scan("at.dru.model");
        System.out.println("Count: " + registry.getBeanDefinitionCount());
        for (String beanDefinitionName : registry.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        scanEntities();
    }
}
