package at.dru.wicketblog.service;

import at.dru.wicketblog.model.AbstractEntity;
import org.springframework.stereotype.Service;

@Service
public class MetaModelService {

    public String getFieldName(Class<? extends AbstractEntity> entityClass, String property) {
        StringBuilder fieldNameBuilder = new StringBuilder(property);
        if (!fieldNameBuilder.isEmpty()) {
            fieldNameBuilder.setCharAt(0, Character.toTitleCase(fieldNameBuilder.charAt(0)));
        }
        return fieldNameBuilder.toString();
    }

}
