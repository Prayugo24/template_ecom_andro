package com.indokoding.apiholder;

import com.indokoding.talabia.BuildConfig;

/**
 * Created by Admin on 14/02/2018.
 */

public class utilsApi {
    public static final String BASE_URL_API = BuildConfig.SERVER_URL;

    public static baseApiService getApiServices() {
        return retrofitClient.getClient(BASE_URL_API).create(baseApiService.class);
    }
}
