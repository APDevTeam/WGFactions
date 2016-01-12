package com.github.pocketkid2.wgf.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.pocketkid2.wgf.Messages;
import com.github.pocketkid2.wgf.WGFPlugin;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import com.massivecraft.factions.entity.MPlayer;
import com.sk89q.worldguard.bukkit.RegionContainer;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class WGFRemoveCommand implements CommandExecutor {

	private WGFPlugin plugin;

	public WGFRemoveCommand(WGFPlugin pl) {
		plugin = pl;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		// Check for not enough arguments
		if (args.length < 2) {
			sender.sendMessage(Messages.NOT_ENOUGH_ARGUMENTS);
			return false;
		}

		// Check for too many arguments
		if (args.length > 3) {
			sender.sendMessage(Messages.TOO_MANY_ARGUMENTS);
			return false;
		}

		// World being used
		World world;

		// Check for a third argument
		if (args.length == 3) {
			// Then get the world
			world = Bukkit.getWorld(args[2]);

			// Check for null
			if (world == null) {
				sender.sendMessage(Messages.INVALID_WORLD);
				return false;
			}
		} else {
			// If not, he must be a player
			if (sender instanceof Player) {
				world = ((Player) sender).getWorld();
			} else {
				// Because if not we don't know what world
				sender.sendMessage(Messages.NOT_ENOUGH_ARGUMENTS);
				return false;
			}
		}

		// Get the region
		RegionContainer container = plugin.getWorldGuard().getRegionContainer();
		RegionManager manager = container.get(world);
		ProtectedRegion region = manager.getRegion(args[0]);

		// Check for null
		if (region == null) {
			sender.sendMessage(Messages.INVALID_REGION);
			return false;
		}

		//Check if sender is the owner of the region or has the override permissions 
		if (sender.hasPermission(wgf.override)||region.isOwner((LocalPlayer)sender))
		{
			sender.sendMessage(Messages.SENDER_NOT_OWNER);	
			return false;
		}

		// Get the faction
		Faction faction = FactionColl.get().getByName(args[1]);

		// Check for null
		if (faction == null) {
			sender.sendMessage(Messages.INVALID_FACTION);
			return false;
		}

		// Showtime
		for (MPlayer player : faction.getMPlayers()) {
			if(player==(player)sender)
			region.getMembers().removePlayer(player.getUuid());
		}

		// Notify the sender
		sender.sendMessage(String.format(Messages.REMOVED, faction.getName(), region.getId()));

		// We're done
		return true;
	}

}
