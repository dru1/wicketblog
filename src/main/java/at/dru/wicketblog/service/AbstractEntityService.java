package at.dru.wicketblog.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractEntityService<T> implements EntityService<T>, InitializingBean {

    @Autowired
    private EntityServiceRegistry entityServiceRegistry;

    @Override
    public void afterPropertiesSet() throws Exception {
        entityServiceRegistry.registerService(this);
    }

}
