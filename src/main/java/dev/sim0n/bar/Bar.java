package dev.sim0n.bar;

import dev.sim0n.bar.command.KitCommand;
import dev.sim0n.bar.listener.PlayerListener;
import dev.sim0n.base.PluginBase;

import java.util.Optional;

public class Bar extends PluginBase {
    private static Optional<Bar> instance;

    public Bar() {
        instance = Optional.of(this);
    }

    public void onEnable() {
        registerEvent(new PlayerListener());
        registerCommand(new KitCommand());
    }

    public static Bar getInstance() {
        return instance.orElseThrow(() -> new IllegalStateException("Test Plugin instance is null."));
    }
}
