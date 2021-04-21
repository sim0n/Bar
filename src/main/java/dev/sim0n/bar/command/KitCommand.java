package dev.sim0n.bar.command;

import com.google.common.base.Preconditions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Arrays;

public class KitCommand extends Command {
    private static final ItemStack SWORD = new ItemStack(Material.DIAMOND_SWORD);

    private static final ItemStack HELMET = new ItemStack(Material.DIAMOND_HELMET);
    private static final ItemStack CHESTPLATE = new ItemStack(Material.DIAMOND_CHESTPLATE);
    private static final ItemStack LEGGINGS = new ItemStack(Material.DIAMOND_LEGGINGS);
    private static final ItemStack BOOTS = new ItemStack(Material.DIAMOND_BOOTS);

    private static final ItemStack PEARLS = new ItemStack(Material.ENDER_PEARL, 64);
    private static final ItemStack GOLDEN_APPLE = new ItemStack(Material.GOLDEN_APPLE, 64, (short) 1);
    private static final ItemStack SPEED = new ItemStack(Material.POTION, 64, (short) 8226);

    private static final ItemStack BLOCKS = new ItemStack(Material.DIAMOND_BLOCK, 64);

    static {
        SWORD.addEnchantment(Enchantment.DAMAGE_ALL, 1);

        Arrays.asList(HELMET, CHESTPLATE, LEGGINGS, BOOTS)
                .forEach(item -> {
                    item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
                    item.addEnchantment(Enchantment.DURABILITY, 3);
                });
    }

    public KitCommand() {
        super("kit");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        Preconditions.checkArgument(sender instanceof Player, "Sender must be a player!");

        Player player = (Player) sender;
        PlayerInventory inventory = player.getInventory();

        inventory.clear();

        inventory.setHelmet(HELMET.clone());
        inventory.setChestplate(CHESTPLATE.clone());
        inventory.setLeggings(LEGGINGS.clone());
        inventory.setBoots(BOOTS.clone());

        inventory.setItem(0, SWORD.clone());
        inventory.setItem(1, GOLDEN_APPLE.clone());
        inventory.setItem(2, PEARLS.clone());
        inventory.setItem(3, SPEED.clone());
        inventory.setItem(4, BLOCKS.clone());

        player.sendMessage(ChatColor.GREEN + "Supplied you with a testing kit!");

        return true;
    }
}
