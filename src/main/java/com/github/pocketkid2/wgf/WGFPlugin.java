package com.github.pocketkid2.wgf;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.pocketkid2.wgf.commands.WGFAddCommand;
import com.github.pocketkid2.wgf.commands.WGFRemoveCommand;
import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class WGFPlugin extends JavaPlugin {

	@Override
	public void onEnable() {
		// Register commands
		getCommand("wgfadd").setExecutor(new WGFAddCommand(this));
		getCommand("wgfremove").setExecutor(new WGFRemoveCommand(this));
		// Log status
		getLogger().info("Done");
	}

	@Override
	public void onDisable() {
		// Log status
		getLogger().info("Done!");
	}

	/**
	 * This method retrieves the WorldGuardPlugin object for use.
	 * 
	 * @return
	 */
	public WorldGuardPlugin getWorldGuard() {
		return WGBukkit.getPlugin();
	}
}
