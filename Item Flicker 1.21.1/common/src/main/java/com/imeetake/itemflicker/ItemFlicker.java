package com.imeetake.itemflicker;

import com.imeetake.itemflicker.config.ItemFlickerConfig;

public final class ItemFlicker {
    public static final String MOD_ID = "itemflicker";

    public static void init() {
        ItemFlickerConfig.register();
    }
}
