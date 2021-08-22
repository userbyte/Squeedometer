package squeek.squeedometer.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
@Config(name = "squeedometer")
public class SqueedometerConfig implements ConfigData {
    public boolean enabled = true;
    public boolean showVertical = false;
    public boolean changeColors = true;

    @ConfigEntry.BoundedDiscrete(max = 100, min = 1)
    @ConfigEntry.Gui.Tooltip(count = 2)
    public int tickInterval = 15;
}