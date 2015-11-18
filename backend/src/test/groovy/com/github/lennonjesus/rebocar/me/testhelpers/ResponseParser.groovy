package com.github.lennonjesus.rebocar.me.testhelpers

import com.google.gson.Gson

import javax.ws.rs.core.Response

/**
 * Created by felipe on 18/11/15.
 */
class ResponseParser {

    int status
    Map attrs

    ResponseParser(Response response) {
        status = response.status
        attrs = new Gson().fromJson(response.entity, Map.class)
    }
}
