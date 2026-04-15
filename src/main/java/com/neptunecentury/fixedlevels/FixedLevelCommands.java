package com.neptunecentury.fixedlevels;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.permissions.Permission;
import net.minecraft.server.permissions.PermissionLevel;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class FixedLevelCommands {

    /**
     * Dispatches the config changes to all connected players
     *
     * @param cfg The config settings to dispatch
     */
    private static void dispatchConfig(LevelConfig cfg) {
        // Dispatch the config to the client if the server is not single player.
        var server = FixedLevels.getServer();
        if (server != null && !server.isSingleplayer()) {
            ConfigDispatcher.dispatch(server, cfg);
        }
    }

    /**
     * Registers the commands used by the mod
     *
     * @param commandName The root command name
     */
    public static void registerCommands(String commandName) {

        final ConfigManager<LevelConfig> _cfgManager = FixedLevels.getConfigManager();

        // Register the command tree
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(Commands.literal(commandName)
                    .then(Commands.literal("query")
                            .then(Commands.literal("useCustomExpLevels")
                                    .executes(context -> {
                                                var cfg = _cfgManager.getConfig();

                                                context.getSource().sendSuccess(() -> Component.literal("%s useCustomExpLevels is currently set to: %s".formatted(commandName, cfg.useCustomExpLevels)), false);
                                                return 1;
                                            }
                                    )
                            )
                            .then(Commands.literal("baseXPForOneLevel")
                                    .executes(context -> {
                                                var cfg = _cfgManager.getConfig();

                                                context.getSource().sendSuccess(() -> Component.literal("%s baseXPForOneLevel is currently set to: %s".formatted(commandName, cfg.baseXPForOneLevel)), false);
                                                return 1;
                                            }
                                    )
                            )
                            .then(Commands.literal("curveMode")
                                    .executes(context -> {
                                                var cfg = _cfgManager.getConfig();

                                                context.getSource().sendSuccess(() -> Component.literal("%s curveMode is currently set to: %s".formatted(commandName, cfg.curveMode)), false);
                                                return 1;
                                            }
                                    )
                            )
                            .then(Commands.literal("curveModeMultiplier")
                                    .executes(context -> {
                                                var cfg = _cfgManager.getConfig();

                                                context.getSource().sendSuccess(() -> Component.literal("%s curveModeMultiplier is currently set to: %s".formatted(commandName, cfg.curveModeMultiplier)), false);
                                                return 1;
                                            }
                                    )
                            )
                            .then(Commands.literal("useExpCap")
                                    .executes(context -> {
                                                var cfg = _cfgManager.getConfig();

                                                context.getSource().sendSuccess(() -> Component.literal("%s useExpCap is currently set to: %s".formatted(commandName, cfg.useExpCap)), false);
                                                return 1;
                                            }
                                    )
                            )
                            .then(Commands.literal("maxExpForNextLevel")
                                    .executes(context -> {
                                                var cfg = _cfgManager.getConfig();

                                                context.getSource().sendSuccess(() -> Component.literal("%s maxExpForNextLevel is currently set to: %s".formatted(commandName, cfg.maxExpForNextLevel)), false);
                                                return 1;
                                            }
                                    )
                            )
                    )
                    .then(Commands.literal("set")
                            .requires(source -> source.permissions().hasPermission(new Permission.HasCommandLevel(PermissionLevel.OWNERS)))
                            .then(Commands.literal("useCustomExpLevels")
                                    .then(Commands.argument("value", BoolArgumentType.bool())
                                            .executes(context -> {
                                                        var cfg = _cfgManager.getConfig();
                                                        // Get new value from command arg
                                                        final boolean value = BoolArgumentType.getBool(context, "value");
                                                        // Set new value
                                                        cfg.useCustomExpLevels = value;
                                                        // Update the config file
                                                        _cfgManager.save();
                                                        FixedLevels.initialize(FixedLevels.getServer(), null);
                                                        context.getSource().sendSuccess(() -> Component.literal("%s useCustomExpLevels is now set to: %s".formatted(commandName, value)), true);
                                                        return 1;
                                                    }
                                            )

                                    )
                            )
                            .then(Commands.literal("baseXPForOneLevel")
                                    .then(Commands.argument("value", IntegerArgumentType.integer())
                                            .executes(context -> {
                                                        var cfg = _cfgManager.getConfig();
                                                        // Get new value from command arg
                                                        final int value = IntegerArgumentType.getInteger(context, "value");
                                                        // Set new value
                                                        cfg.baseXPForOneLevel = value;
                                                        // Update the config file
                                                        _cfgManager.save();
                                                        dispatchConfig(cfg);
                                                        context.getSource().sendSuccess(() -> Component.literal("%s baseXPForOneLevel is now set to: %s".formatted(commandName, value)), true);
                                                        return 1;
                                                    }
                                            )

                                    )
                            )
                            .then(Commands.literal("curveMode")
                                    .then(Commands.argument("value", BoolArgumentType.bool())
                                            .executes(context -> {
                                                        var cfg = _cfgManager.getConfig();
                                                        // Get new value from command arg
                                                        final boolean value = BoolArgumentType.getBool(context, "value");
                                                        // Set new value
                                                        cfg.curveMode = value;
                                                        // Update the config file
                                                        _cfgManager.save();
                                                        dispatchConfig(cfg);
                                                        context.getSource().sendSuccess(() -> Component.literal("%s curveMode is now set to: %s".formatted(commandName, value)), true);
                                                        return 1;
                                                    }
                                            )

                                    )
                            )
                            .then(Commands.literal("curveModeMultiplier")
                                    .then(Commands.argument("value", IntegerArgumentType.integer())
                                            .executes(context -> {
                                                        var cfg = _cfgManager.getConfig();
                                                        // Get new value from command arg
                                                        final int value = IntegerArgumentType.getInteger(context, "value");
                                                        // Set new value
                                                        cfg.curveModeMultiplier = value;
                                                        // Update the config file
                                                        _cfgManager.save();
                                                        dispatchConfig(cfg);
                                                        context.getSource().sendSuccess(() -> Component.literal("%s curveModeMultiplier is now set to: %s".formatted(commandName, value)), true);
                                                        return 1;
                                                    }
                                            )

                                    )
                            )
                            .then(Commands.literal("useExpCap")
                                    .then(Commands.argument("value", BoolArgumentType.bool())
                                            .executes(context -> {
                                                        var cfg = _cfgManager.getConfig();
                                                        // Get new value from command arg
                                                        final boolean value = BoolArgumentType.getBool(context, "value");
                                                        // Set new value
                                                        cfg.useExpCap = value;
                                                        // Update the config file
                                                        _cfgManager.save();
                                                        dispatchConfig(cfg);
                                                        context.getSource().sendSuccess(() -> Component.literal("%s useExpCap is now set to: %s".formatted(commandName, value)), true);
                                                        return 1;
                                                    }
                                            )

                                    )
                            )
                            .then(Commands.literal("maxExpForNextLevel")
                                    .then(Commands.argument("value", IntegerArgumentType.integer())
                                            .executes(context -> {
                                                        var cfg = _cfgManager.getConfig();
                                                        // Get new value from command arg
                                                        final int value = IntegerArgumentType.getInteger(context, "value");
                                                        // Set new value
                                                        cfg.maxExpForNextLevel = value;
                                                        // Update the config file
                                                        _cfgManager.save();
                                                        dispatchConfig(cfg);
                                                        context.getSource().sendSuccess(() -> Component.literal("%s maxExpForNextLevel is now set to: %s".formatted(commandName, value)), true);
                                                        return 1;
                                                    }
                                            )

                                    )
                            )
                    )

            );
        });
    }
}
