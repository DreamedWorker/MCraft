package com.dontkonwdream.mcraft.mcraft;

import org.bukkit.plugin.java.JavaPlugin;

public final class MCraft extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("服务器启动");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
