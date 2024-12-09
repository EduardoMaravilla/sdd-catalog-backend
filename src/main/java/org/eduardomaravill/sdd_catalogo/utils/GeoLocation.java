package org.eduardomaravill.sdd_catalogo.utils;

import org.springframework.stereotype.Component;

import java.util.Map;

import static org.eduardomaravill.sdd_catalogo.utils.MyEncoderBase64.decodeBase64ToDouble;

@Component
public class GeoLocation {
    private static final double EARTH_RADIUS = 6371.0;

    private static final Double RADIUS = 100.0;

    private GeoLocation() {
    }

    public static boolean isWithinRadius(double lat1, double lon1, double lat2, double lon2) {
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = lon2Rad - lon1Rad;

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS * c;

        return distance <= RADIUS;
    }

    public static boolean validateLocation(Map<String, String> locationToken, Map<String, String> locationRequest){
        return isWithinRadius(
                decodeBase64ToDouble(locationToken.get("latitude")),
                decodeBase64ToDouble(locationToken.get("longitude")),
                decodeBase64ToDouble(locationRequest.get("latitude")),
                decodeBase64ToDouble(locationRequest.get("longitude")));
    }
}
