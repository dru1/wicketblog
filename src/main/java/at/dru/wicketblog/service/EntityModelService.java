package at.dru.wicketblog.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.stereotype.Service;

@Service
public class EntityModelService implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(EntityModelService.class);

    public void scanEntities() {
        BeanDefinitionRegistry registry = new SimpleBeanDefinitionRegistry();
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry, false);
        scanner.setResourcePattern("**/*.class");
        scanner.scan("at.dru.model");
        logger.info("Found {} beans", registry.getBeanDefinitionCount());
        for (String beanDefinitionName : registry.getBeanDefinitionNames()) {
            logger.info("Bean {}", beanDefinitionName);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        scanEntities();
    }

}
