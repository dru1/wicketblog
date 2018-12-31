package at.dru.wicketblog;

import org.apache.wicket.protocol.http.WicketFilter;
import org.apache.wicket.spring.SpringWebApplicationFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;

@Configuration
public class WebInitializer implements ServletContextInitializer {

    private static final Logger logger = LoggerFactory.getLogger(WebInitializer.class);

    private static final String PARAM_APP_BEAN = "applicationBean";

    @Override
    public void onStartup(ServletContext servletContext) {
        FilterRegistration wicketFilter = servletContext.addFilter("wicket-filter", WicketFilter.class);
        wicketFilter.setInitParameter(WicketFilter.APP_FACT_PARAM, SpringWebApplicationFactory.class.getName());
        wicketFilter.setInitParameter(PARAM_APP_BEAN, "webApplication");
        wicketFilter.setInitParameter(WicketFilter.FILTER_MAPPING_PARAM, "/*");
        wicketFilter.addMappingForUrlPatterns(null, false, "/*");
        logger.info("Wicket Servlet Filter registered.");
    }

}
