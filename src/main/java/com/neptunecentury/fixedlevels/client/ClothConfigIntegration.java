package com.neptunecentury.fixedlevels.client;

import com.neptunecentury.fixedlevels.FixedLevels;
import com.neptunecentury.fixedlevels.ConfigManager;
import com.neptunecentury.fixedlevels.LevelConfig;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

/**
 * Integrates the cloth config api to create an options screen
 */
public class ClothConfigIntegration {
    // Gets the configuration object instance
    private static final ConfigManager<LevelConfig> cfgManager = FixedLevels.getConfigManager();
    private static final LevelConfig cfg = cfgManager.getConfig();

    /**
     * Gets text from the localization file
     * @param type A string representing the type of the text e.g. config
     * @param id The id of the text to display
     * @return The text from the built key using type and id
     */
    public static MutableText localize(String type, String id) {
        return Text.translatable(type + "." + FixedLevels.MOD_ID + "." + id);
    }

    /**
     * Builds the cloth config screen for the options
     * @param parent The parent screen to attach to
     * @return The generated screen from cloth config
     */
    public static Screen generateScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(localize("config", "title"))
                .setSavingRunnable(cfgManager::save)
                .setTransparentBackground(true);
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        builder.getOrCreateCategory(localize("config", "category.general"))
                .addEntry(entryBuilder
                        .startBooleanToggle(
                                localize("config", "option.curveMode"),
                                cfg.curveMode
                        )
                        .setSaveConsumer(value -> cfg.curveMode = value)
                        .build()
                )
                .addEntry(entryBuilder
                        .startIntField(
                                localize("config", "option.baseXPForOneLevel"),
                                cfg.baseXPForOneLevel
                        )
                        .setSaveConsumer(value -> cfg.baseXPForOneLevel = value)
                        .build()
                )
                .addEntry(entryBuilder
                        .startIntField(
                                localize("config", "option.curveModeMultiplier"),
                                cfg.curveModeMultiplier
                        )
                        .setSaveConsumer(value -> cfg.curveModeMultiplier = value)
                        .build()
                );

        // Build the screen and return it.
        return builder.build();
    }
}