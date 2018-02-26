package com.mcc.buildit.service;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.mcc.libjokejava.MyJoke;

/** An endpoint class we are exposing */
@Api(
        name = "jokeGce",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "service.buildit.mcc.com",
                ownerName = "service.buildit.mcc.com",
                packagePath = ""
        )
)
public class JokeEndpoint {

    @ApiMethod(name = "todaysJoke")
    public JokeModel todaysJoke() {
        MyJoke joke  = new MyJoke();
        String jokestr = joke.getJokeOfTheDay();

        JokeModel response = new JokeModel();
        response.setJoke(jokestr);

        return response;
    }

}
