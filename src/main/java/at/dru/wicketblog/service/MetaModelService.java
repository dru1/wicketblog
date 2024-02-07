package at.dru.wicketblog.service;

import at.dru.wicketblog.model.AbstractEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;

@Service
public class MetaModelService {

    public String getFieldName(@Nonnull Class<? extends AbstractEntity> entityClass, @Nonnull String property) {
        StringBuilder fieldNameBuilder = new StringBuilder(property);
        if (!fieldNameBuilder.isEmpty()) {
            fieldNameBuilder.setCharAt(0, Character.toTitleCase(fieldNameBuilder.charAt(0)));
        }
        return fieldNameBuilder.toString();
    }

}
