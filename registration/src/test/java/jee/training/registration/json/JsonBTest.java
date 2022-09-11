package jee.training.registration.json;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;
import jee.training.registration.model.Event;
import jee.training.registration.model.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonBTest {

    JsonbConfig config = new JsonbConfig().withAdapters(new EventAdapter(null));
    Jsonb jsonb = JsonbBuilder.create(config);
    Location darmstadt = new Location(1L, "Darmstadt");
    Event event = new Event("2022-09-22", darmstadt);

    @Test
    void jsonBinding() {
        var jsonLocation = "{\"id\":1,\"name\":\"Darmstadt\"}";
        assertEquals(jsonLocation, jsonb.toJson(darmstadt));
        Location fromJson = jsonb.fromJson(jsonLocation, Location.class);
        assertEquals(darmstadt, fromJson);

        var jsonEvent = "{\"id\":\"2022-09-22\",\"location\":1}";
        assertEquals(jsonEvent, jsonb.toJson(event));
        assertEquals(event, jsonb.fromJson(jsonEvent, Event.class));
    }
}
