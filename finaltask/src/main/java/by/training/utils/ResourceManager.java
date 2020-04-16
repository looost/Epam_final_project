package by.training.utils;

import by.training.controller.command.CommandUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

public enum ResourceManager {
    INSTANCE;
    private ResourceBundle resourceBundle;
    private final String resourceName = "property.text";
    private static final String LOCALE_COOKIE_NAME = "language";

    ResourceManager() {
        resourceBundle = ResourceBundle.getBundle(resourceName, Locale.getDefault());
    }

    public void changeResource(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(resourceName, locale);
    }

    public void changeResource(String stringLocale) {
        Locale locale = Locale.forLanguageTag(stringLocale.replace('_', '-'));
        resourceBundle = ResourceBundle.getBundle(resourceName, locale);
    }

    public ResourceManager changeResource(HttpServletRequest req) {
        Cookie cookie = CommandUtil.searchCookie(LOCALE_COOKIE_NAME, req);
        if (cookie != null) {
            Locale locale = Locale.forLanguageTag(cookie.getValue().replace('_', '-'));
            resourceBundle = ResourceBundle.getBundle(resourceName, locale);
        }
        return this;
    }

    public String getString(String key) {
        return resourceBundle.getString(key);
    }
}
