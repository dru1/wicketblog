package at.dru.wicketblog;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * to generate web.xml and configuration for the servlet container
 */
public class SpringBootWicketInitializer extends SpringBootServletInitializer {

    @Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
        return application.sources(WicketWebApplication.class);
    }

}
