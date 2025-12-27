package com.imeetake.itemflicker.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

@Config(name = "itemflicker")
public class ItemFlickerConfig implements ConfigData {

    @ConfigEntry.BoundedDiscrete(min = 5, max = 300)
    public int secondsBeforeDespawn = 20;

    @ConfigEntry.BoundedDiscrete(min = 50, max = 200)
    public int flickerSpeedPercent = 100;

    private static ItemFlickerConfig instance;

    public static void register() {
        AutoConfig.register(ItemFlickerConfig.class, GsonConfigSerializer::new);
        instance = AutoConfig.getConfigHolder(ItemFlickerConfig.class).getConfig();
    }

    public static ItemFlickerConfig get() {
        return instance;
    }

    public int getThresholdTicks() {
        return secondsBeforeDespawn * 20;
    }

    public float getSpeedMultiplier() {
        return flickerSpeedPercent / 100f;
    }
}