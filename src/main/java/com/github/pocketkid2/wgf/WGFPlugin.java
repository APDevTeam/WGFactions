package com.github.pocketkid2.wgf;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.pocketkid2.wgf.commands.WGFAddCommand;
import com.github.pocketkid2.wgf.commands.WGFRemoveCommand;

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
}
