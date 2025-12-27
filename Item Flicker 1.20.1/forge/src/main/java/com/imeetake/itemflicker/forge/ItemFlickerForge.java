package com.imeetake.itemflicker.forge;

import com.imeetake.itemflicker.config.ItemFlickerConfig;
import dev.architectury.platform.forge.EventBuses;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import com.imeetake.itemflicker.ItemFlicker;

@Mod(ItemFlicker.MOD_ID)
public final class ItemFlickerForge {
    public ItemFlickerForge() {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(ItemFlicker.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        // Run our common setup.
        ItemFlicker.init();

        ModLoadingContext.get().registerExtensionPoint(
                ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory(
                        (client, parent) -> AutoConfig.getConfigScreen(ItemFlickerConfig.class, parent).get()
                )
        );
    }
}
