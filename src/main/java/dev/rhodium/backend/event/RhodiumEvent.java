package dev.rhodium.backend.event;

import me.zero.alpine.event.type.Cancellable;
import net.minecraft.client.MinecraftClient;

public class RhodiumEvent extends Cancellable {

    public static Era era = Era.PRE;

    public static Float partialTicks = MinecraftClient.getInstance().getTickDelta();

    public enum Era {
        PRE, POST
    }

}
