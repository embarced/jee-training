package jee.training.registration.json;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jee.training.registration.model.Event;
import jee.training.registration.model.Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonBTest {

    Jsonb jsonb = JsonbBuilder.create();
    Location darmstadt = new Location(1L, "Darmstadt");
    Event event = new Event("2022-09-22", darmstadt);

    @Test
    void jsonBinding() {
        assertEquals("{\"id\":1,\"name\":\"Darmstadt\"}", jsonb.toJson(darmstadt));
        var jsonLocation = """
                {"id":1,"name":"Darmstadt"}""";
        Location fromJson = jsonb.fromJson(jsonLocation, Location.class);
        assertEquals(darmstadt, fromJson);

        var jsonEvent = """
                {"date":"21.09.2022","id":"2022-09-22","location":{"id":1,"name":"Darmstadt"}}""";
        assertEquals(event, jsonb.fromJson(jsonEvent, Event.class));
    }
}
