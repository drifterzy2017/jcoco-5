package com.kkk.cocoapp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Jcoco 5.
 * <p>
 * Properties are configured in the application.yml file.
 * See {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
//@Data
public class ApplicationProperties {
    private Integer myRound;

    private Integer isAutoSubmit;

    private String qLibNames;

    public String getqLibNames() {
        return qLibNames;
    }

    public void setqLibNames(String qLibNames) {
        this.qLibNames = qLibNames;
    }

    public Integer getMyRound() {
        return myRound;
    }

    public void setMyRound(Integer myRound) {
        this.myRound = myRound;
    }

    public Integer getIsAutoSubmit() {
        return isAutoSubmit;
    }

    public void setIsAutoSubmit(Integer isAutoSubmit) {
        this.isAutoSubmit = isAutoSubmit;
    }
}

