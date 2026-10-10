package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/project-local.properties",
        "classpath:config/project.properties",
        "system:properties"
})

public interface ProjectConfig extends Config {

    // === UI ===
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

    // === API ===
    @Key("apiBaseUrl")
    @DefaultValue("https://reqres.in")
    String getApiBaseUrl();

    @Key("apiKey")
    String getApiKey();
}