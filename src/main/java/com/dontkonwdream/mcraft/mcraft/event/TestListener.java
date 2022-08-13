package com.dontkonwdream.mcraft.mcraft.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.jetbrains.annotations.NotNull;

public class TestListener implements Listener {

    @EventHandler  //加入这个注解告诉服务器这是一个事件监听器
    public void onPlayLogin(@NotNull PlayerLoginEvent event){
        System.out.println(event.getPlayer().getName() + "登录了服务器，欢迎！！！");
    }
}
