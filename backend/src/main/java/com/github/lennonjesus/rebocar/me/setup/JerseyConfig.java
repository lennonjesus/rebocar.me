package com.github.lennonjesus.rebocar.me.setup;

import com.github.lennonjesus.rebocar.me.endpoint.CaminhaoEndpoint;
import com.github.lennonjesus.rebocar.me.endpoint.RebocadoEndpoint;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(CaminhaoEndpoint.class);
        register(RebocadoEndpoint.class);
    }

}
