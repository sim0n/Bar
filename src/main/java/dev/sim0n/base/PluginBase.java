package dev.sim0n.base;

import dev.sim0n.base.event.listener.EventListener;
import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PluginBase extends JavaPlugin {
    private final EventListener eventListener = new EventListener();
    private final CommandManager commandManager = new CommandManager();

    private final List<Object> registeredEvents = new ArrayList<>();

    public void runTask(Runnable runnable) {
        getServer().getScheduler().runTask(this, runnable);
    }

    public void runRepeatingTask(Runnable runnable, long delay) {
        getServer().getScheduler().runTaskTimer(this, runnable, delay, delay);
    }

    public void runAsyncTask(Runnable runnable) {
        getServer().getScheduler().runTaskAsynchronously(this, runnable);
    }

    public void runTaskLater(Runnable runnable, long delay) {
        getServer().getScheduler().runTaskLater(this, runnable, delay);
    }

    public void registerCommand(Command command) {
        commandManager.register("bar", command);
    }

    public void registerEvent(Object object) {
        registeredEvents.add(object);
    }
}
