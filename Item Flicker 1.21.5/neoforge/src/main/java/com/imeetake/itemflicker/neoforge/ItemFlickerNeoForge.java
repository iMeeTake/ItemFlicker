package com.imeetake.itemflicker.neoforge;

import com.imeetake.itemflicker.ItemFlicker;
import com.imeetake.itemflicker.config.ItemFlickerConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(ItemFlicker.MOD_ID)
public final class ItemFlickerNeoForge {
    public ItemFlickerNeoForge(ModContainer modContainer) {
        // Run our common setup.
        ItemFlicker.init();

        modContainer.registerExtensionPoint(
                IConfigScreenFactory.class,
                (container, parent) -> AutoConfig.getConfigScreen(ItemFlickerConfig.class, parent).get()
        );
    }
}
