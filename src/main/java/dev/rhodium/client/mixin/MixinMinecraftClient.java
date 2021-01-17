package dev.rhodium.client.mixin;

import dev.rhodium.Rhodium;
import dev.rhodium.backend.event.events.TickEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class MixinMinecraftClient {

    @Shadow
    public ClientWorld world;

    @Shadow public ClientPlayerEntity player;

    @Inject(method = "tick", at = @At(value = "INVOKE"), cancellable = true)
    public void tick(CallbackInfo info) {
        TickEvent event;
        if (player != null && world != null) {
            event = new TickEvent(TickEvent.Stage.INGAME);
        } else {
            event = new TickEvent(TickEvent.Stage.OUTGAME);
        }
        Rhodium.EVENTBUS.post(event);
        if (event.isCancelled()) info.cancel();
    }
}

