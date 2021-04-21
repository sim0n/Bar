package dev.sim0n.base;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;

import java.lang.reflect.Field;

public class CommandManager {
    private static Field commandMapField;
    private static SimpleCommandMap commandMap;

    static {
        try {
            Server server = Bukkit.getServer();

            commandMapField = server.getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);

            commandMap = (SimpleCommandMap) commandMapField.get(server);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Registers a command to the server command map
     * @param fallbackPrefix The prefix
     * @param command The command to register
     */
    public void register(String fallbackPrefix, Command command) {
        commandMap.register(fallbackPrefix, command);
    }
}
