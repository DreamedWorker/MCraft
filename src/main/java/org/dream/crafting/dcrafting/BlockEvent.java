package org.dream.crafting.dcrafting;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.index.qual.PolyUpperBound;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

public class BlockEvent implements Listener {
    //This class is used to set the event logic after the player clicks a diamond block.

    @EventHandler
    public void RightClickBlock(@NotNull PlayerInteractEvent event){
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            //Only right clock can cause this logic.
            Player player = event.getPlayer();
            Block block = event.getClickedBlock();
            assert block != null;  //Block must not be null! I want to use Kotlin to finish this part to avoid this problem.
            if (block.getType().equals(Material.DIAMOND_BLOCK)) {
                int x = block.getX();
                int y = block.getY();
                int z = block.getZ();
                theNextStep(x, y, z, player);
            }
        }
    }

    @EventHandler
    public void menuButtonClick(@NotNull InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        String invTitle = event.getView().getTitle();
        if (invTitle.equals("高级合成窗口")){
            if (Objects.requireNonNull(event.getCurrentItem()).getType().equals(Material.BARRIER)){
                player.sendMessage("你关闭了窗口");
                player.closeInventory();
            } else if (event.getCurrentItem().getType().equals(Material.EMERALD)){
                player.sendMessage("合成按钮按下");
                player.getInventory().addItem(new ItemStack(Material.APPLE));
                player.closeInventory();
            } else {
                player.sendMessage("操作非法");
            }
        }
    }

    private void theNextStep(int x, int y, int z, Player player) {
        World mainWorld = Bukkit.getWorld("world");  //This function is only available when using in the main world.
        assert mainWorld != null;
        //Get the block upside the one that is clicked.
        Block craftingTable = mainWorld.getBlockAt(x, y + 1, z);
        if (craftingTable.getType().equals(Material.CRAFTING_TABLE)){
            player.sendMessage("机器设置正常，正在加载");
            Inventory inventory = Bukkit.createInventory(player, 18, "高级合成窗口");
            inventory.setItem(15, new ItemStack(Material.BARRIER));
            inventory.setItem(11, new ItemStack(Material.EMERALD));
            player.openInventory(inventory);
        } else {
            player.sendMessage("机器配置错误，请检查你的方块放置！");
        }
    }
}
