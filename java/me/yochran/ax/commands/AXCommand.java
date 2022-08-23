package me.yochran.ax.commands;

import me.yochran.ax.exception.ConsoleException;
import me.yochran.ax.exception.NoPermissionException;
import me.yochran.ax.gui.GUI;
import me.yochran.ax.gui.guis.AXGui;
import me.yochran.ax.utils.Chat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AXCommand implements CommandExecutor {

    /**
     * Execute a command
     * @param sender Source of the command
     * @param command Command which was executed
     * @param label Alias of the command which was used
     * @param args Passed command arguments
     * @return
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        // check if sender is console
        if (!(sender instanceof Player)) {
            new ConsoleException("&cYou must be a player to execute that command.", sender);
            return true;
        }

        // get player
        Player player = (Player) sender;

        // check if player has permission
        if (!player.hasPermission("ax.command")) {
            new NoPermissionException("&cYou do not have permission to use that command.", (CommandSender) player);
            return true;
        }

        // get gui variables
        int size = 36;
        String title = Chat.translate("&7Anti-Xray GUI");

        // create GUI
        AXGui gui = new AXGui(player, size, title);
        gui.setup();

        // open
        GUI.open(gui.getGui());

        return true;
    }
}
