package com.kkk.cocoapp.web.rest.phx;

import org.springframework.http.HttpHeaders;

/**
 * Created by HP on 2017/6/8.
 */
public class HeaderUtil {
    public static HttpHeaders createAlert(String message, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-PhoenixApp-alert", message);
        headers.add("X-PhoenixApp-params", param);
        return headers;
    }

    public static HttpHeaders createEntityCreationAlert(String entityName, String param) {
        return createAlert("PhoenixApp." + entityName + ".created", param);
    }

    public static HttpHeaders createEntityUpdateAlert(String entityName, String param) {
        return createAlert("PhoenixApp." + entityName + ".updated", param);
    }

    public static HttpHeaders createEntityDeletionAlert(String entityName, String param) {
        return createAlert("PhoenixApp." + entityName + ".deleted", param);
    }

    public static HttpHeaders createFailureAlert(String entityName, String errorKey, String defaultMessage) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-PhoenixApp-error", "error." + errorKey);
        headers.add("X-PhoenixApp-params", entityName);
        return headers;
    }
}
