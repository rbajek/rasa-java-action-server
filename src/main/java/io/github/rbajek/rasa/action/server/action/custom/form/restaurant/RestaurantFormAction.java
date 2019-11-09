package io.github.rbajek.rasa.action.server.action.custom.form.restaurant;

import io.github.rbajek.rasa.action.server.action.custom.form.restaurant.data.Constants;
import io.github.rbajek.rasa.action.server.action.custom.form.restaurant.validator.ValidateCuisineSlot;
import io.github.rbajek.rasa.action.server.action.custom.form.restaurant.validator.ValidateNumPeopleSlot;
import io.github.rbajek.rasa.action.server.action.custom.form.restaurant.validator.ValidateOutdoorSeatingSlot;
import io.github.rbajek.rasa.sdk.CollectingDispatcher;
import io.github.rbajek.rasa.sdk.action.form.AbstractFormAction;
import io.github.rbajek.rasa.sdk.action.form.slot.mapper.AbstractSlotMapping;
import io.github.rbajek.rasa.sdk.action.form.slot.mapper.EntitySlotMapping;
import io.github.rbajek.rasa.sdk.action.form.slot.mapper.IntentSlotMapping;
import io.github.rbajek.rasa.sdk.action.form.slot.mapper.TextSlotMapping;
import io.github.rbajek.rasa.sdk.dto.Tracker;
import io.github.rbajek.rasa.sdk.dto.event.AbstractEvent;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Example of a custom form action
 *
 * @author Rafał Bajek
 */
@Component
public class RestaurantFormAction extends AbstractFormAction {

    public RestaurantFormAction() {
        super("restaurant_form");
    }

    @Override
    protected List<String> requiredSlots(Tracker tracker) {
        return Arrays.asList(Constants.Slots.CUISINE,
                Constants.Slots.NUM_PEOPLE,
                Constants.Slots.OUTDOOR_SEATING,
                Constants.Slots.PREFERENCES,
                Constants.Slots.FEEDBACK);
    }

    @Override
    protected Map<String, List<AbstractSlotMapping>> slotMappings() {
        Map<String, List<AbstractSlotMapping>> slotMappingMap = new HashMap<>();

        slotMappingMap.put(Constants.Slots.CUISINE, Arrays.asList(EntitySlotMapping.builder(Constants.Entities.CUISINE)
                .notIntent(Constants.Intents.CHITCHAT).build()));

        slotMappingMap.put(Constants.Slots.NUM_PEOPLE, Arrays.asList(
                EntitySlotMapping.builder(Constants.Entities.NUM_PEOPLE).intent(Constants.Intents.INFORM).intent(Constants.Intents.REQUEST_RESTAURANT).build(),
                EntitySlotMapping.builder(Constants.Entities.NUMBER).build()
                )
        );

        slotMappingMap.put(Constants.Slots.OUTDOOR_SEATING, Arrays.asList(
                EntitySlotMapping.builder(Constants.Entities.SEATING).build(),
                IntentSlotMapping.builder().intent(Constants.Intents.AFFIRM).value(true).build(),
                IntentSlotMapping.builder().intent(Constants.Intents.DENY).value(false).build()
                )
        );

        slotMappingMap.put(Constants.Slots.PREFERENCES, Arrays.asList(
                IntentSlotMapping.builder().intent(Constants.Intents.DENY).value("no additional preferences").build(),
                TextSlotMapping.builder().notIntent(Constants.Intents.AFFIRM).build()
                )
        );

        slotMappingMap.put(Constants.Slots.FEEDBACK, Arrays.asList(
                EntitySlotMapping.builder(Constants.Entities.FEEDBACK).build(),
                TextSlotMapping.builder().build()
                )
        );

        return slotMappingMap;
    }

    /**
     * Define what the form has to do after all required slots are filled
     *
     * @return
     */
    @Override
    protected List<AbstractEvent> submit(CollectingDispatcher dispatcher) {
        // utter submit template
        dispatcher.utterTemplate(Constants.Templates.UTTER_SUBMIT);
        return Collections.emptyList();
    }

    @Override
    protected void registerSlotsValidators(Map<String, ValidateSlot> slotValidatorMap) {
        slotValidatorMap.put(Constants.Slots.CUISINE, new ValidateCuisineSlot());
        slotValidatorMap.put(Constants.Slots.NUM_PEOPLE, new ValidateNumPeopleSlot());
        slotValidatorMap.put(Constants.Slots.OUTDOOR_SEATING, new ValidateOutdoorSeatingSlot());
    }
}
