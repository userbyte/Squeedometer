package squeek.squeedometer.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;


@Environment(EnvType.CLIENT)
@Config(name = "squeedometer")
public class SqueedometerConfig implements ConfigData {
    public boolean changeColors = true;
}