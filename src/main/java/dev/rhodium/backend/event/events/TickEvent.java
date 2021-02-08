package dev.rhodium.backend.event.events;

import dev.rhodium.backend.event.RhodiumEvent;

public class TickEvent extends RhodiumEvent {

    public Stage stage;

    public enum Stage {
        INGAME, OUTGAME
    }

    public TickEvent(Stage stage){
        this.stage = stage;
    }

}
