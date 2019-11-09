package io.github.rbajek.rasa.action.server.action.support;

import io.github.rbajek.rasa.sdk.ActionExecutor;
import io.github.rbajek.rasa.sdk.action.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Discover and register custom rasa actions
 *
 * @author Rafał Bajek
 */
@Component
public class AutoRegisterAction implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ConfigurableListableBeanFactory beanFactory;

    @Autowired
    private ActionExecutor rasaActionExecutor;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        beanFactory.getBeansOfType(Action.class).values().forEach(rasaAction -> {
            rasaActionExecutor.registerAction(rasaAction);
        });
    }
}
