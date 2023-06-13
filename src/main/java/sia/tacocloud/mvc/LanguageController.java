package sia.tacocloud.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
public class LanguageController {

    @PostMapping("/change-language")
    public String changeLanguage(HttpServletRequest request, HttpServletResponse response, @RequestParam String lang, @RequestParam(required=false) boolean cookie) {
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        assert localeResolver != null;
        localeResolver.setLocale(request, response, new Locale(lang));

        if (cookie) {
            CookieLocaleResolver cookieLocaleResolver = (CookieLocaleResolver) localeResolver;
            cookieLocaleResolver.setLocale(request, response, new Locale(lang));
        }

        return "redirect:/";
    }

}

