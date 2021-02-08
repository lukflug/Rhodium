package dev.rhodium.client.module.movement;

import dev.rhodium.Rhodium;
import dev.rhodium.backend.event.events.ClipAtLedgeEvent;
import dev.rhodium.client.module.Category;
import dev.rhodium.client.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.MinecraftClient;

import java.awt.*;

public class SafeWalk extends Module {

    public SafeWalk(){
        super("SafeWalk", Category.MOVEMENT, Color.BLUE);
    }

    @Override
    protected void onEnable() {
        Rhodium.EVENTBUS.subscribe(listener);
    }

    @Override
    protected void onDisable() {
        Rhodium.EVENTBUS.unsubscribe(listener);
    }

    @EventHandler
    private final Listener<ClipAtLedgeEvent> listener = new Listener<>(e -> {
        if(MinecraftClient.getInstance().player == null) return;
        e.clip = true;
    });
}
