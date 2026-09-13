package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "system:properties",
        "classpath:config/system.properties"
})

public interface ProjectConfig extends Config {

    @Key("baseUrl")
    @DefaultValue("https://1exon.ru/")
    String getBaseUrl();

    @Key("remoteUrl")
    String getRemoteUrl();

    @Key("browser")
    @DefaultValue("chrome")
    String getBrowser();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String getBrowserSize();

    @Key("browserVersion")
    @DefaultValue("152")
    String getBrowserVersion();
}