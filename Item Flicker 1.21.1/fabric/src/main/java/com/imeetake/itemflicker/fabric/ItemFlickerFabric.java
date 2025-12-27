package com.imeetake.itemflicker.fabric;

import net.fabricmc.api.ModInitializer;

import com.imeetake.itemflicker.ItemFlicker;

public final class ItemFlickerFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        ItemFlicker.init();
    }
}
