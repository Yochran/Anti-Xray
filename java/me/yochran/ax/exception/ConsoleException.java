package me.yochran.ax.exception;

import me.yochran.ax.utils.Chat;
import org.bukkit.command.CommandSender;

public class ConsoleException {

    // constructor
    public ConsoleException(String message, CommandSender sender) {
        // throw
        sender.sendMessage(Chat.translate(message));
    }

}
