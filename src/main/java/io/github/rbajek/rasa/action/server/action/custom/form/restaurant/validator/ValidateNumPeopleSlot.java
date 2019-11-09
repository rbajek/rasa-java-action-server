package io.github.rbajek.rasa.action.server.action.custom.form.restaurant.validator;

import io.github.rbajek.rasa.action.server.action.custom.form.restaurant.data.Constants;
import io.github.rbajek.rasa.sdk.CollectingDispatcher;
import io.github.rbajek.rasa.sdk.action.form.AbstractFormAction;
import io.github.rbajek.rasa.sdk.dto.Domain;
import io.github.rbajek.rasa.sdk.dto.Tracker;

import java.util.HashMap;
import java.util.Map;

/**
 * Validate num_people value.
 *
 * @author Rafał Bajek
 */
public class ValidateNumPeopleSlot implements AbstractFormAction.ValidateSlot {

    @Override
    public Map<String, Object> validateAndConvert(Object value, CollectingDispatcher dispatcher, Tracker tracker, Domain domain) {
        Map<String, Object> validationMapResult = new HashMap<>();

        if(String.class.isInstance(value) && Integer.parseInt((String)value) > 0) {
            validationMapResult.put(Constants.Slots.NUM_PEOPLE, value);
        } else if(Integer.class.isInstance(value) && Integer.class.cast(value) > 0) {
            validationMapResult.put(Constants.Slots.NUM_PEOPLE, value);
        } else {
            dispatcher.utterTemplate(Constants.Templates.UTTER_WRONG_NUM_PEOPLE);
            // validation failed, set slot to null
            validationMapResult.put(Constants.Slots.NUM_PEOPLE, null);
        }
        return validationMapResult;
    }
}
