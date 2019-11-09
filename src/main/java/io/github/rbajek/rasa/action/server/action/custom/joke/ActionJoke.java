package io.github.rbajek.rasa.action.server.action.custom.joke;

import io.github.rbajek.rasa.sdk.CollectingDispatcher;
import io.github.rbajek.rasa.sdk.action.Action;
import io.github.rbajek.rasa.sdk.dto.Domain;
import io.github.rbajek.rasa.sdk.dto.Tracker;
import io.github.rbajek.rasa.sdk.dto.event.AbstractEvent;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

/**
 * Custom action for handling jokes
 *
 * @author Rafał Bajek
 */
@Component
public class ActionJoke implements Action {

    private static final String REST_URI = "http://api.icndb.com/jokes/random";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String name() {
        return "action_joke";
    }

    @Override
    public List<AbstractEvent> run(CollectingDispatcher dispatcher, Tracker tracker, Domain domain) {
        // make an API call
        Joke jokeResponse = restTemplate.getForObject(REST_URI, Joke.class);

        // extract a joke from returned json response
        String joke = jokeResponse.getValue().getJoke();

        // send the message back to the user
        dispatcher.utterMessage(joke);

        return Collections.emptyList();
    }

    @ToString
    @Getter
    @Setter
    private static class Joke {
        String type;
        JokeValue value;
    }

    @ToString @Getter @Setter
    private static class JokeValue {
        Long id;
        String joke;
        Object[] categories;
    }
}
