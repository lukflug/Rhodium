package dev.rhodium.backend.event.events;

import dev.rhodium.backend.event.RhodiumEvent;
import net.minecraft.entity.player.PlayerEntity;

public class ClipAtLedgeEvent extends RhodiumEvent {

    public Boolean clip = false;
    public PlayerEntity player = null;

    public ClipAtLedgeEvent(PlayerEntity player, Boolean clip){
        this.player = player;
        this.clip = clip;
    }

}
