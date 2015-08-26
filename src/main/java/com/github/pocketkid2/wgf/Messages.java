package com.github.pocketkid2.wgf;

import org.bukkit.ChatColor;

public interface Messages {

	String TOO_MANY_ARGUMENTS = ChatColor.RED + "Too many arguments!";
	String NOT_ENOUGH_ARGUMENTS = ChatColor.RED + "Not enough arguments!";
	String INVALID_WORLD = ChatColor.RED + "Invalid world!";
	String INVALID_REGION = ChatColor.RED + "Invalid region!";
	String INVALID_FACTION = ChatColor.RED + "Invalid faction!";
	String ADDED = ChatColor.AQUA + "Added all players from faction " + ChatColor.GREEN + "%s " + ChatColor.AQUA + "to region " + ChatColor.BLUE + "%s";
	String REMOVED = ChatColor.AQUA + "Removed all players from faction " + ChatColor.GREEN + "%s " + ChatColor.AQUA + "from region " + ChatColor.BLUE + "%s";

}
