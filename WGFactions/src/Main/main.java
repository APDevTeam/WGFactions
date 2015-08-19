package Main;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.sk89q.worldguard.bukkit.RegionContainer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class main extends JavaPlugin implements CommandExecutor{
	private static main instance;
	
	@Override
	public void onEnable() {
		instance = this;
	}
	@Override
	public void onDisable() {
		
	}
	public static main getInstance() {
		return instance;
	}
	private WorldGuardPlugin getWorldGuard() {
	    Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");

	    // WorldGuard may not be loaded
	    if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
	        return null; // Maybe you want throw an exception instead
	    }

	    return (WorldGuardPlugin) plugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		WorldGuardPlugin WG = getWorldGuard();
		String arg1 = args[0];
		String arg2 = args[1];
		RegionContainer container = getWorldGuard().getRegionContainer();
		RegionManager regions = container.get(((Entity) sender).getWorld());
		ProtectedRegion region = regions.getRegion(arg1);
		Bukkit.dispatchCommand(sender, "region flag entry deny");
		if(cmd.getName() == "WGFAdd" || cmd.getName() == "WGFRemove") {
			
			
			
			if (cmd.getName() == "WGFAdd" && args.length == 2) {
				sender.sendMessage(ChatColor.BLUE + "Success!");
				Faction fac = Faction.get(args[1]);
				for (MPlayer MPlayer : fac.getMPlayers()) {
					Player Member = getServer().getPlayer(MPlayer.getName());
					Bukkit.dispatchCommand(sender, "region addmember " + arg1 + " " + Member.getName());
					if (ProtectedRegion.isValidId(arg1)) {
						region.getMembers().addPlayer(Member.getName());
					}else{
						sender.sendMessage(ChatColor.RED + arg1 + " Is not a valid region!");
					}
				}
			}
			if (cmd.getName() == "WGFRemove" && args.length == 2) {
				sender.sendMessage(ChatColor.BLUE + "Success!");
				Faction fac = Faction.get(args[2]);
				for (MPlayer MPlayer : fac.getMPlayers()) {
					Player Member = getServer().getPlayer(MPlayer.getName());
					if (ProtectedRegion.isValidId(arg1)) {
						region.getMembers().removePlayer(Member.getName());
					}else{
						sender.sendMessage(ChatColor.RED + arg1 + " Is not a valid region!");
					}
				}
			}
			return true;
		}
			else {
				return false;
		}
	}
}
