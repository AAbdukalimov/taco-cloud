package sia.tacocloud.configs;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import java.util.Locale;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public LocaleResolver localResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(Locale.FRANCE);
        resolver.setCookieName("locale");
        resolver.setCookieMaxAge(3600);
        return resolver;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("login");
    }


}
