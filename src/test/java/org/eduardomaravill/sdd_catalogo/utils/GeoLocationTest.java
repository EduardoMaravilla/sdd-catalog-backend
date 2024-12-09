package org.eduardomaravill.sdd_catalogo.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GeoLocationTest {


    @Test
    void testIsWithinRadius_WithinRadius() {
        double lat1 = 12.971598; // Bangalore, India
        double lon1 = 77.594566;
        double lat2 = 12.975828; // Cerca de la misma ubicación
        double lon2 = 77.590103;

        boolean result = GeoLocation.isWithinRadius(lat1, lon1, lat2, lon2);
        assertTrue(result, "Las coordenadas deberían estar dentro del radio.");
    }

    @Test
    void testIsWithinRadius_OutsideRadius() {
        double lat1 = 12.971598; // Bangalore, India
        double lon1 = 77.594566;
        double lat2 = 13.035542; // Más lejos, esta vez fuera del radio
        double lon2 = 77.597099;

        boolean result = GeoLocation.isWithinRadius(lat1, lon1, lat2, lon2);
        assertTrue(result, "Las coordenadas deberían estar fuera del radio.");
    }

    @Test
    void testIsWithinRadius_SameLocation() {
        double lat1 = 40.712776; // Nueva York
        double lon1 = -74.005974;

        boolean result = GeoLocation.isWithinRadius(lat1, lon1, lat1, lon1);
        assertTrue(result, "La distancia entre el mismo punto debe ser 0, por lo que está dentro del radio.");
    }

    @Test
    void testIsWithinRadius_AntipodalPoints() {
        double lat1 = 0.0; // Punto en el ecuador
        double lon1 = 0.0;
        double lat2 = -0.0; // Punto opuesto en la Tierra
        double lon2 = 180.0;

        boolean result = GeoLocation.isWithinRadius(lat1, lon1, lat2, lon2);
        assertFalse(result, "Los puntos antipodales están en extremos opuestos de la Tierra.");
    }

    @Test
    void testIsWithinRadius_AccurateDistance() {
        double lat1 = 40.712776; // Nueva York
        double lon1 = -74.005974;
        double lat2 = 34.052235; // Los Ángeles
        double lon2 = -118.243683;

        boolean result = GeoLocation.isWithinRadius(lat1, lon1, lat2, lon2);
        assertFalse(result, "La distancia entre Nueva York y Los Ángeles supera los 100 km.");
    }

    @Test
    void testValidateLocation_ValidLocation() {
        Map<String, String> locationToken = new HashMap<>();
        locationToken.put("latitude", Base64.getEncoder().encodeToString("12.971598".getBytes()));
        locationToken.put("longitude", Base64.getEncoder().encodeToString("77.594566".getBytes()));

        Map<String, String> locationRequest = new HashMap<>();
        locationRequest.put("latitude", Base64.getEncoder().encodeToString("12.975828".getBytes()));
        locationRequest.put("longitude", Base64.getEncoder().encodeToString("77.590103".getBytes()));

        boolean result = GeoLocation.validateLocation(locationToken, locationRequest);
        assertTrue(result, "La ubicación solicitada está dentro del radio permitido.");
    }

    @Test
    void testValidateLocation_InvalidLocation() {
        Map<String, String> locationToken = new HashMap<>();
        locationToken.put("latitude", Base64.getEncoder().encodeToString("12.971598".getBytes()));
        locationToken.put("longitude", Base64.getEncoder().encodeToString("77.594566".getBytes()));

        Map<String, String> locationRequest = new HashMap<>();
        locationRequest.put("latitude", Base64.getEncoder().encodeToString("13.035542".getBytes()));
        locationRequest.put("longitude", Base64.getEncoder().encodeToString("77.597099".getBytes()));

        boolean result = GeoLocation.validateLocation(locationToken, locationRequest);
        assertTrue(result, "La ubicación solicitada está fuera del radio permitido.");
    }

    @Test
    void testValidateLocation_IncompleteLocationToken() {
        Map<String, String> locationToken = new HashMap<>();
        locationToken.put("latitude", Base64.getEncoder().encodeToString("12.971598".getBytes()));

        Map<String, String> locationRequest = new HashMap<>();
        locationRequest.put("latitude", Base64.getEncoder().encodeToString("13.035542".getBytes()));
        locationRequest.put("longitude", Base64.getEncoder().encodeToString("77.597099".getBytes()));

        assertThrows(NullPointerException.class, () -> {
            GeoLocation.validateLocation(locationToken, locationRequest);
        }, "Se esperaba una NullPointerException porque la clave 'longitude' no está presente.");
    }

    @Test
    void testValidateLocation_InvalidBase64Value() {
        Map<String, String> locationToken = new HashMap<>();
        locationToken.put("latitude", "invalidBase64");
        locationToken.put("longitude", Base64.getEncoder().encodeToString("77.594566".getBytes()));

        Map<String, String> locationRequest = new HashMap<>();
        locationRequest.put("latitude", Base64.getEncoder().encodeToString("12.975828".getBytes()));
        locationRequest.put("longitude", Base64.getEncoder().encodeToString("77.590103".getBytes()));

        assertThrows(IllegalArgumentException.class, () -> {
            GeoLocation.validateLocation(locationToken, locationRequest);
        }, "Se esperaba una IllegalArgumentException debido a la cadena Base64 inválida.");
    }

}