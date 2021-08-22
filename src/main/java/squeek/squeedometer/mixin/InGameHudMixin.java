package squeek.squeedometer.mixin;

import squeek.squeedometer.Squeedometer;
import squeek.squeedometer.config.SqueedometerConfig;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    //SqueedometerConfig config = AutoConfig.getConfigHolder(SqueedometerConfig.class).getConfig();

    @Inject(at = @At("TAIL"), method = "render(Lnet/minecraft/client/util/math/MatrixStack;F)V")
    private void renderSqueedometerHud(MatrixStack matrices, float tickDelta, CallbackInfo info) {
        Squeedometer.squeedometerHud.draw(matrices, tickDelta);
    }
}