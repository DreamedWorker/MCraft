package org.dream.crafting.dcrafting;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class DCrafting extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("插件启动成功！");
        //Register our own Listener
        Bukkit.getPluginManager().registerEvents(new BlockEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("插件正在注销...");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (label.equalsIgnoreCase("dreampath")){
            sender.sendMessage(System.getProperty("user.dir") + "是你的工作路径");
            //D:\MCServer是你的工作路径
            return true;
        }
        return true;
    }
}
