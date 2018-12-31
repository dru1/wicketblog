package at.dru.wicketblog.wicket.behavior;

import com.github.openjson.JSONObject;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.resource.UrlResourceReference;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShowdownBehavior extends Behavior {

    private final LinkedHashMap<String, String> options = new LinkedHashMap<>();

    private static final UrlResourceReference REF = new UrlResourceReference(
            Url.parse("https://cdnjs.cloudflare.com/ajax/libs/showdown/1.9.0/showdown.min.js")
    );

    @Nonnull
    public ShowdownBehavior option(@Nonnull String optionKey, @Nullable String optionValue) {
        if (optionValue == null) {
            options.remove(optionKey);
        } else {
            options.put(optionKey, optionValue);
        }
        return this;
    }

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);

        response.render(JavaScriptHeaderItem.forReference(REF));
    }

    @Override
    public void bind(Component component) {
        super.bind(component);

        component.setOutputMarkupId(true);
    }

    @Override
    public void afterRender(Component component) {
        super.afterRender(component);

        component.getResponse().write(buildShowdownInitScript(component));
    }

    @Nonnull
    private String buildShowdownInitScript(@Nonnull Component component) {
        return Stream.<String>builder()
                .add("<script type=\"text/javascript\">")
                .add("(function() {")
                .add(String.format("var node = jQuery('#%s');", component.getMarkupId()))
                .add(String.format("var converter = new showdown.Converter(%s);", new JSONObject(options)))
                .add("node.replaceWith(converter.makeHtml(node.text()));")
                .add("})();")
                .add("</script>")
                .build()
                .collect(Collectors.joining(System.lineSeparator()));
    }

}
