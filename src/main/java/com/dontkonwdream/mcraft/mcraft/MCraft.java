package com.dontkonwdream.mcraft.mcraft;

import com.dontkonwdream.mcraft.mcraft.event.TestListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class MCraft extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new TestListener(), this);  //定义外部事件监听器————成功
        Objects.requireNonNull(Bukkit.getPluginCommand("dreamtest")).setExecutor(this);
        System.out.println("服务器启动");
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler // 这个注解告诉Bukkit这个方法正在监听某个事件
    public void onPlayerJoin(PlayerJoinEvent e) { // 玩家登录服务器就会调用这个方法
        System.out.println("玩家" + e.getPlayer().getName() + "加入了游戏");
        Inventory inv = Bukkit.createInventory(e.getPlayer(), 9, "欢迎你");
        ItemStack item_bk = new ItemStack(Material.IRON_DOOR);
        for (int i = 0; i < 9; i ++){
            inv.setItem(i, item_bk);
        }
        e.getPlayer().openInventory(inv);
    }

    @EventHandler
    public void onPlayClick(PlayerInteractEvent playerInteractEvent){ //测试一下玩家点击物品的事件
        playerInteractEvent.getPlayer().sendMessage(Objects.requireNonNull(playerInteractEvent.getClickedBlock()).toString());
    }

    //命令管理工具（指令执行者的权限判断还存在问题）
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (label.equalsIgnoreCase("dreamtest")){
            if (sender instanceof Player){
                sender.sendMessage("仅限玩家使用");
            } else {
                sender.sendMessage("使用成功");
            }
        }
        return true;
    }
}
