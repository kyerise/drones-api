package io.comfortchauke.drones.enums;

public enum DroneModel {
    LIGHT_WEIGHT("Lightweight"),
    MIDDLE_WEIGHT("Middleweight"),
    CRUISER_WEIGHT("Cruiserweight"),
    HEAVY_WEIGHT("Heavyweight");

    private final String value;

    DroneModel(String val) {
        value = val;
    }

    public String getValue() {
        return value;
    }
}
