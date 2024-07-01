package squeek.squeedometer.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import squeek.squeedometer.Squeedometer;
import squeek.squeedometer.config.ConfigWrapper;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Inject(at = @At("TAIL"), method = "render")
    private void renderSqueedometerHud(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (ConfigWrapper.config.enabled) {
            Squeedometer.squeedometerHud.draw(context, tickCounter);
        }
    }
}
