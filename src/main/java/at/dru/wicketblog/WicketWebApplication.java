package at.dru.wicketblog;


import at.dru.wicketblog.pages.*;
import at.dru.wicketblog.wicket.CurrentAuthenticatedWebSession;
import at.dru.wicketblog.wicket.MessageSourceResourceLoader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.Page;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Date;

@Configuration
@Component
@ComponentScan
@EnableScheduling
@EnableTransactionManagement
@EnableAutoConfiguration
public class WicketWebApplication extends AuthenticatedWebApplication {

    private static final Log LOG = LogFactory.getLog(WicketWebApplication.class);

    private final Date startup;

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${application.appName}")
    private String appName;

    @Value("${application.runtimeType}")
    private RuntimeConfigurationType runtimeType;

    public WicketWebApplication() {
        startup = new Date();
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }

    public Date getStartup() {
        return startup;
    }

    public String getAppName() {
        return appName;
    }

    @Override
    protected void init() {
        super.init();

        getComponentInstantiationListeners().add(
                new SpringComponentInjector(this, applicationContext));

        getResourceSettings().getStringResourceLoaders().add(new MessageSourceResourceLoader());

        getMarkupSettings().setDefaultMarkupEncoding("UTF-8");


        mountPage("/login", LoginPage.class);
        mountPage("/setup", SetupPage.class);
        mountPage("/admin/posts", AdminPostPage.class);
        mountPage("/admin/posts/categories", AdminPostCategoryPage.class);

        LOG.info("Application created at " + startup);
    }

    @Override
    public RuntimeConfigurationType getConfigurationType() {
        return runtimeType;
    }

    @Override
    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
        return CurrentAuthenticatedWebSession.class;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return LoginPage.class;
    }

    /**
     * simple run!
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(WicketWebApplication.class, args);
    }


}
