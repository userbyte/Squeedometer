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

    @ConfigEntry.ColorPicker
    public int textColor = 0xFFFFFF;

    @ConfigEntry.ColorPicker
    public int acceleratingColor = 0x00FF00;

    @ConfigEntry.ColorPicker
    public int deceleratingColor = 0xFF0000;

    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public Position position = Position.BOTTOM_LEFT;

    public static enum Position {
        BOTTOM_LEFT,
        BOTTOM_RIGHT,
        TOP_LEFT,
        TOP_RIGHT
    }
}