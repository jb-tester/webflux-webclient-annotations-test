package com.mytests.spring.webflux.firstwebfluxtest;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * *******************************
 * Created by Irina.Petrovskaya on 10/28/2019.
 * Project: webflux-webclient-annotations-test
 * *******************************
 */
@ConfigurationProperties("mymappings")
public class ConfProps {

    String baseUri = "/";
    String uriRegex = ".+";

    public String getUriRegex() {
        return uriRegex;
    }

    public void setUriRegex(String uriRegex) {
        this.uriRegex = uriRegex;
    }

    public String getBaseUri() {
        return baseUri;
    }

    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }
}
