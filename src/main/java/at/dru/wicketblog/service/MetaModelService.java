package at.dru.wicketblog.service;

import at.dru.wicketblog.model.AbstractEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;

@Service
public class MetaModelService {

    public String getFieldName(@Nonnull Class<? extends AbstractEntity> entityClass, @Nonnull String property) {
        return StringUtils.capitalize(property);
    }

}
