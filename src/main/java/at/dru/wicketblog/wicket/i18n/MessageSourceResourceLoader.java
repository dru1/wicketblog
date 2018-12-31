package at.dru.wicketblog.wicket.i18n;

import org.apache.wicket.Component;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.resource.loader.IStringResourceLoader;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.context.MessageSource;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Locale;

public class MessageSourceResourceLoader implements IStringResourceLoader {

    @SpringBean
    private MessageSource messageSource;

    public MessageSourceResourceLoader() {
        Injector.get().inject(this);
    }

    @Override
    public String loadStringResource(@Nullable Class<?> clazz,
                                     @Nonnull String key,
                                     @Nullable Locale locale,
                                     @Nullable String style,
                                     @Nullable String variation) {
        return messageSource.getMessage(key, null, null, locale);
    }

    @Override
    public String loadStringResource(@Nullable Component component,
                                     @Nonnull String key,
                                     @Nullable Locale locale,
                                     @Nullable String style,
                                     @Nullable String variation) {
        return loadStringResource((Class<?>) null, key, locale, style, variation);
    }
}
