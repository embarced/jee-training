package jee.training.registration.json;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.bind.adapter.JsonbAdapter;
import jakarta.persistence.EntityManager;
import jee.training.registration.model.Event;
import jee.training.registration.model.Location;

public class EventAdapter implements JsonbAdapter<Event, JsonObject> {

    private final EntityManager em;

    public EventAdapter(EntityManager em) {
        this.em = em;
    }

    @Override
    public JsonObject adaptToJson(Event event) throws Exception {
        return Json.createObjectBuilder()
                .add("id", event.getId())
                .add("locationId", event.getLocation().getId())
                .add("attendees", event.getAttendees().toString())
                .build();
    }

    @Override
    public Event adaptFromJson(JsonObject jsonObject) throws Exception {
        Location location = em.getReference(Location.class, Long.valueOf(jsonObject.getInt("locationId")));
        return new Event(jsonObject.getString("id"), location);
    }
}
