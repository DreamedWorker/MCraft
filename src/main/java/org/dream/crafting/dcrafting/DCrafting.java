package org.dream.crafting.dcrafting;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

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
}
