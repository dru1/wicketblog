package at.dru.wicketblog.service;

import at.dru.wicketblog.model.DefaultEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class MetaModelService {

    public String getFieldName(Class<? extends DefaultEntity> entityClass, String property) {
        return StringUtils.capitalize(property);
    }

}
