package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/web.properties",
        "classpath:config/api.properties",
        "system:properties"
})

public interface ProjectConfig extends Config {

    @Key("UIbaseUrl")
    @DefaultValue("https://1exon.ru")
    String getUIBaseUrl();

    @Key("remoteUrl")
    String getRemoteUrl();

    @Key("browser")
    @DefaultValue("chrome")
    String getBrowser();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String getBrowserSize();

    @Key("browserVersion")
    @DefaultValue("155")
    String getBrowserVersion();


    @Key("apiBaseUrl")
    @DefaultValue("https://reqres.in/api")
    String getApiBaseUrl();

    @Key("apiKey")
    String getApiKey();

    @Key("apiLogin")
    String getApiLogin();

    @Key("apiPassword")
    String getApiPassword();

}