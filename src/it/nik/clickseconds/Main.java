package it.nik.clickseconds;

import it.nik.clickseconds.commands.CommandClick;
import it.nik.clickseconds.commands.CommandClickSeconds;
import it.nik.clickseconds.listeners.ListenersClicks;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Level;

/**
 * The ViewCPS main class.
 * Date start project: 14/03/2017
 */
public class Main extends JavaPlugin {
    /**
     * Instance main class
     */
    private static Main instance;
    public static Main getInstance() {
        return instance;
    }

    /**
     * Method called when the server enables the plugin.
     */
    @Override
    public void onEnable() {
        instance = this;
        log(Level.INFO, "Version of plugin: " + getDescription().getVersion() + "\n> Developed by " + getDescription().getAuthors(), true);

        // Register commands and listeners
        getCommand("click").setExecutor(new CommandClick());
        getCommand("clickseconds").setExecutor(new CommandClickSeconds());
        getServer().getPluginManager().registerEvents(new ListenersClicks(), this);

        // Save config
        getConfig().options().copyDefaults(true);
        if (!new File(getDataFolder(), "config.yml").exists()) {
            saveResource("config.yml", false);
        }
    }

    /**
     * Utils for plugin, send log to console
     *
     * @param level - Level of warning
     * @param message
     */
    private void log(Level level, String message, boolean prefix) {
        if (prefix)
            getServer().getLogger().log(level, "[ClickSeconds] " + message.replaceAll("\n", System.lineSeparator()));
        else {
            getServer().getLogger().log(level, message.replaceAll("\n", System.lineSeparator()));
        }
    }

    /**
     * Format message in chat with apposite color and new line
     *
     * @param message - Translated message
     * @param player
     */
    public void format(String message, Player player) {
        String[] str = ChatColor.translateAlternateColorCodes('&', message).split("\n");
        player.sendMessage(str);
    }
    public void format(String message, CommandSender commandSender) {
        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
