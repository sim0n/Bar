package dev.sim0n.bar.listener;

import dev.sim0n.bar.Bar;
import dev.sim0n.base.event.EventHandler;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class PlayerListener {
    private final Bar plugin = Bar.getInstance();

    public PlayerListener() {
        EventHandler.register(PlayerJoinEvent.class, event -> {
            event.setJoinMessage(null);

            // Force the player to do /kit
            event.getPlayer().performCommand("kit");
        });

        EventHandler.register(PlayerQuitEvent.class, event -> {
            event.setQuitMessage(null);
        });

        // maybe make this configurable to test regen?
        EventHandler.register(FoodLevelChangeEvent.class, event -> {
            event.setFoodLevel(20);
        });

        EventHandler.register(WeatherChangeEvent.class, event -> {
            event.setCancelled(true);
        });

        EventHandler.register(BlockPlaceEvent.class, event -> {
            Player player = event.getPlayer();

            if (player.getGameMode() != GameMode.SURVIVAL)
                return;

            player.getItemInHand().setAmount(64);

            plugin.runTaskLater(() -> {
                event.getBlockPlaced().setType(Material.AIR);
            }, 20L * 10L); // Clear the block after 10 seconds
        });

        EventHandler.register(BlockBreakEvent.class, event -> {
            Player player = event.getPlayer();

            if (player.getGameMode() != GameMode.SURVIVAL)
                return;

            event.setCancelled(true);
        });

        EventHandler.register(PlayerDeathEvent.class, event -> {
            event.getDrops().clear();
        });
    }
}
