package ru.deelter.nooblocker.commands;

import com.google.common.base.Preconditions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.deelter.nooblocker.NoobLocker;

import java.util.List;

public class NoobLockerCommand implements TabExecutor {

    public static void setup() {
        PluginCommand command = NoobLocker.getInstance().getCommand("nooblocker");
        Preconditions.checkNotNull(command, "Command is null in plugin.yml");
        command.setExecutor(new NoobLockerCommand());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage("У вас нет прав на это");
            return true;
        }
        if (args.length < 1) return true;
        if (args[0].equalsIgnoreCase("reload")) {
            sender.sendMessage("Конфиг перезагружен");
            NoobLocker.getLockerConfig().reload();
            return true;
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return List.of("reload");
    }
}
