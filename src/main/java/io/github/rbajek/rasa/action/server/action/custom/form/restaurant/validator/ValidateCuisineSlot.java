package io.github.rbajek.rasa.action.server.action.custom.form.restaurant.validator;

import io.github.rbajek.rasa.action.server.action.custom.form.restaurant.data.Constants;
import io.github.rbajek.rasa.sdk.CollectingDispatcher;
import io.github.rbajek.rasa.sdk.action.form.AbstractFormAction;
import io.github.rbajek.rasa.sdk.dto.Domain;
import io.github.rbajek.rasa.sdk.dto.Tracker;

import java.util.*;

/**
 * Validate cuisine value.
 *
 * @author Rafał Bajek
 */
public class ValidateCuisineSlot implements AbstractFormAction.ValidateSlot {

    /**
     * Database of supported cuisines
     *
     * @return list of supported cuisines
     */
    private List<String> cuisineDB() {
        return Arrays.asList("caribbean", "chinese", "french", "greek", "indian", "italian", "mexican");
    }

    @Override
    public Map<String, Object> validateAndConvert(Object value, CollectingDispatcher dispatcher, Tracker tracker, Domain domain) {
        Objects.requireNonNull(value, "Value cannot be null");

        Map<String, Object> validationMapResult = new HashMap<>();

        if(cuisineDB().contains(String.class.cast(value).toLowerCase())) {
            //  validation succeeded, return the value of the "cuisine" slot to value
            validationMapResult.put(Constants.Slots.CUISINE, value);
            return validationMapResult;
        }

        // validation failed, return "null", meaning the user will be asked for the slot again
        dispatcher.utterTemplate(Constants.Templates.UTTER_WRONG_CUISINE);
        validationMapResult.put(Constants.Slots.CUISINE, null);
        return validationMapResult;
    }
}
