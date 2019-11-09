package io.github.rbajek.rasa.action.server.action.custom.form.restaurant.validator;

import io.github.rbajek.rasa.action.server.action.custom.form.restaurant.data.Constants;
import io.github.rbajek.rasa.sdk.CollectingDispatcher;
import io.github.rbajek.rasa.sdk.action.form.AbstractFormAction;
import io.github.rbajek.rasa.sdk.dto.Domain;
import io.github.rbajek.rasa.sdk.dto.Tracker;

import java.util.HashMap;
import java.util.Map;

/**
 * Validate outdoor_seating value.
 *
 * @author Rafał Bajek
 */
public class ValidateOutdoorSeatingSlot implements AbstractFormAction.ValidateSlot {

    @Override
    public Map<String, Object> validateAndConvert(Object value, CollectingDispatcher dispatcher, Tracker tracker, Domain domain) {
        Map<String, Object> validationMapResult = new HashMap<>();
        if(String.class.isInstance(value)) {
            if ("out".equals(value)) {
                // convert "out..." to true
                validationMapResult.put(Constants.Slots.OUTDOOR_SEATING, true);
            } else if ("in".equals(value)) {
                // convert "in..." to false
                validationMapResult.put(Constants.Slots.OUTDOOR_SEATING, false);
            } else {
                dispatcher.utterTemplate(Constants.Templates.UTTER_WRONG_OUTDOOR_SEATING);
                // validation failed, set slot to None
                validationMapResult.put(Constants.Slots.OUTDOOR_SEATING, null);
            }
        } else {
            // affirm/deny was picked up as T/F
            validationMapResult.put(Constants.Slots.OUTDOOR_SEATING, value);
        }
        return validationMapResult;
    }
}
