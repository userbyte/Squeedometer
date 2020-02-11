package squeek.squeedometer.mixin;

import squeek.squeedometer.Squeedometer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.hud.InGameHud;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    @Inject(at = @At("TAIL"), method = "render()V")
    private void renderSqueedometerHud(CallbackInfo info) {
        Squeedometer.squeedometerHud.render();
    }
}