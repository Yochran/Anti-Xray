package me.yochran.ax.exception;

import me.yochran.ax.utils.Chat;
import org.bukkit.command.CommandSender;

public class NoPermissionException {

    // constructor
    public NoPermissionException(String message, CommandSender sender) {
        // throw
        sender.sendMessage(Chat.translate(message));
    }

}
