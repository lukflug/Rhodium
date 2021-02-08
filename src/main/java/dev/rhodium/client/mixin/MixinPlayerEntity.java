package dev.rhodium.client.mixin;

import dev.rhodium.Rhodium;
import dev.rhodium.backend.event.events.ClipAtLedgeEvent;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class MixinPlayerEntity {

    @Inject(method = "clipAtLedge", at = @At("HEAD"), cancellable = true)
    public void onClipAtLedge(CallbackInfoReturnable<Boolean> cir) {
        ClipAtLedgeEvent event = new ClipAtLedgeEvent((PlayerEntity) (Object) this, null);
        Rhodium.EVENTBUS.post(event);
        if (event.clip != null) cir.setReturnValue(event.clip);
    }
}
