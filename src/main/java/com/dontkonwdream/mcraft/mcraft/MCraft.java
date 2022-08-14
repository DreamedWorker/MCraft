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
import org.bukkit.inventory.ShapedRecipe;
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
        addCustomRecipes(); //添加自定义的合成表
        System.out.println("服务器启动");
    }

    //目前只能基于合成台添加配方，在考虑能否自建一个独立于官方的合成机制
    private void addCustomRecipes() { //向服务器添加
        //准备测试能否加入自定义的物品进入
        ShapedRecipe recipe = new ShapedRecipe(new ItemStack(Material.EMERALD));  //这个方法被弃用，准备新的方法使用
        recipe.shape("xxx", "x x", "xxx");
        recipe.setIngredient('x', Material.GOLD_INGOT);
        getServer().addRecipe(recipe);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().clearRecipes();  //删除自定义合成台
    }

    @EventHandler
    public void onPlayClick(PlayerInteractEvent playerInteractEvent){ //测试一下玩家点击物品的事件
        playerInteractEvent.getPlayer().sendMessage(Objects.requireNonNull(playerInteractEvent.getClickedBlock()).toString());
    }

    //命令管理工具（指令执行者的权限判断还存在问题）
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (label.equalsIgnoreCase("dreamtest")){
            sender.sendMessage("使用成功");
        }
        return true;
    }
}
