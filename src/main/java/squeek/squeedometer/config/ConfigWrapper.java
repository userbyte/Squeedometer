package squeek.squeedometer.config;

import me.shedaniel.autoconfig.AutoConfig;

public class ConfigWrapper {
    public static SqueedometerConfig config;

    public static void loadConfig() {
        config = AutoConfig.getConfigHolder(SqueedometerConfig.class).getConfig();
    }
}
