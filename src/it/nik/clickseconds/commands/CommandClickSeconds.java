package it.nik.clickseconds.commands;

import it.nik.clickseconds.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * General command of plugin
 */
public class CommandClickSeconds implements CommandExecutor {
    /*
     Get instance of main class
    */
    private Main main = Main.getInstance();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        // Check number of args
        if (!(strings.length > 0)) {
            main.format("&6Plugin developed by &2Niketion&6, version:&2 " + main.getDescription().getVersion() + "\n&e/clickseconds reload\n&6Thanks for install &4♥", commandSender);
            return false;
        }

        // Check if player has permission
        if (!commandSender.hasPermission("click.admin")) {
            main.format(main.getConfig().getString("permission-denied"), commandSender);
            return false;
        }

        // Reload option
        if (strings[0].equalsIgnoreCase("reload")) {
            main.reloadConfig();
            main.reloadConfig();
            main.format("&6Plugin reloaded.", commandSender);
            return true;
        } else {
            // Usage
            main.format("&6Plugin developed by &2Niketion&6, version:&2 " + main.getDescription().getVersion() + "\n&e/clickseconds reload\n&6Thanks for install &4♥", commandSender);
            return false;
        }
    }
}
