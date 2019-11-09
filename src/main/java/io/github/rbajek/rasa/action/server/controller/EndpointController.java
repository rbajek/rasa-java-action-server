package io.github.rbajek.rasa.action.server.controller;

import io.github.rbajek.rasa.action.server.controller.dto.RegisteredAction;
import io.github.rbajek.rasa.action.server.controller.dto.Status;
import io.github.rbajek.rasa.sdk.ActionExecutor;
import io.github.rbajek.rasa.sdk.dto.ActionRequest;
import io.github.rbajek.rasa.sdk.dto.ActionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Rafał Bajek
 */
@RestController
@RequestMapping("/")
@Slf4j
public class EndpointController {

    @Autowired
    private ActionExecutor rasaActionExecutor;

    /**
     * The main entry point
     *
     * @return info about the action server
     */
    @GetMapping("/")
    public String home() {
        return "Rasa action server is up and running";
    }

    /**
     * Ping endpoint to check if the server is running and well.
     *
     * @return status of action server
     */
    @GetMapping("/health")
    public Status health() {
        return new Status("ok");
    }

    /**
     * List all registered actions.
     *
     * @return
     */
    @GetMapping("/actions")
    public List<RegisteredAction> actions() {
        return rasaActionExecutor.getRegisteredActionNames().stream().map(actionName -> new RegisteredAction((actionName))).collect(Collectors.toList());
   }

   @PostMapping("/webhook")
   public ActionResponse webhook(@RequestBody ActionRequest actionRequest) {
        return rasaActionExecutor.run(actionRequest);
   }
}
