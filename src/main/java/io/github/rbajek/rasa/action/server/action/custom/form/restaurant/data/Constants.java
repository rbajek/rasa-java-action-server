package io.github.rbajek.rasa.action.server.action.custom.form.restaurant.data;

/**
 * Constants used by Restaurant Form Action
 *
 * @author Rafał Bajek
 */
public class Constants {

    /**
     * Supported slots
     */
    public static class Slots {
        public static final String CUISINE= "cuisine";
        public static final String NUM_PEOPLE= "num_people";
        public static final String OUTDOOR_SEATING= "outdoor_seating";
        public static final String PREFERENCES= "preferences";
        public static final String FEEDBACK= "feedback";
    }

    /**
     * Supported entities
     */
    public static class Entities {
        public static final String CUISINE= "cuisine";
        public static final String NUM_PEOPLE= "num_people";
        public static final String NUMBER= "number";
        public static final String SEATING= "seating";
        public static final String FEEDBACK= "feedback";
    }

    /**
     * Supported intents
     */
    public static class Intents {
        public static final String CHITCHAT= "chitchat";
        public static final String INFORM= "inform";
        public static final String REQUEST_RESTAURANT= "request_restaurant";
        public static final String AFFIRM= "affirm";
        public static final String DENY= "deny";
    }

    /**
     * Supported templates
     */
    public static class Templates {
        public static final String UTTER_SUBMIT= "utter_submit";
        public static final String UTTER_WRONG_CUISINE = "utter_wrong_cuisine";
        public static final String UTTER_WRONG_NUM_PEOPLE = "utter_wrong_num_people";
        public static final String UTTER_WRONG_OUTDOOR_SEATING = "utter_wrong_outdoor_seating";
    }
}
