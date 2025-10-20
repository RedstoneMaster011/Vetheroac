package dev.redstone.vetheroac.config;

import me.fzzyhmstrs.fzzy_config.api.ConfigApiJava;

public class VetheroacConfigs {

    public static VetheroacConfig VetheroacConfig = ConfigApiJava.registerAndLoadConfig(VetheroacConfig::new);

    public static void init() {}
}
