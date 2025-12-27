package com.imeetake.itemflicker.fabric;

import com.imeetake.itemflicker.config.ItemFlickerConfig;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;

public class ItemFlickerModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> AutoConfig.getConfigScreen(ItemFlickerConfig.class, parent).get();
    }
}
