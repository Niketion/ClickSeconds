package it.nik.clickseconds.commands;

import it.nik.clickseconds.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Command for start CPS mode
 */
public class CommandClick implements CommandExecutor {
    /*
      Get instance of main class
     */
    private Main main = Main.getInstance();

    /*
      Save player to cps mode
     */
    public static HashMap<String, Integer> clickMode = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        // Declare that commandsender is a player
        Player player = (Player) commandSender;

        // Check if player has permission
        if (!commandSender.hasPermission("click.use")) {
            main.format(main.getConfig().getString("permission-denied"), player);
            return false;
        }

        if (clickMode.containsKey(player.getName())) {
            main.format(main.getConfig().getString("already-clickmode"), player);
            return false;
        }

        // Cooldown
        main.format(main.getConfig().getStringList("cooldown").get(0), player);
        Bukkit.getServer().getScheduler()
                .runTaskLater(main, () -> {
                    main.format(main.getConfig().getStringList("cooldown").get(1), player);
                    Bukkit.getServer().getScheduler()
                            .runTaskLater(main, () -> {
                                main.format(main.getConfig().getStringList("cooldown").get(2), player);
                                Bukkit.getServer().getScheduler()
                                        .runTaskLater(main, () -> {
                                            main.format(main.getConfig().getStringList("cooldown").get(3), player);
                                            clickMode.put(player.getName(), 0);
                                            Bukkit.getServer().getScheduler()
                                                    .runTaskLater(main, () -> {
                                                        /*
                                                          End cps mode
                                                         */
                                                        main.format(main.getConfig().getString("end").replaceAll("%clicks%", String.valueOf(clickMode.get(player.getName()))), player);
                                                        clickMode.remove(player.getName());
                                                    }, 20 * main.getConfig().getInt("duration-clickmode"));
                                        }, 20);
                            }, 20);
                }, 20);
        return true;
    }
}
