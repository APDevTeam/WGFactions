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

import com.massivecraft.factions.FactionListComparator;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.factions.event.EventFactionsMembershipChange;
import com.massivecraft.factions.event.EventFactionsMembershipChange.MembershipChangeReason;
import com.sk89q.worldguard.bukkit.RegionContainer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag.State;
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
		WorldGuardPlugin plug = getWorldGuard();
		RegionManager RM = plug.getRegionContainer().get(Bukkit.getWorld("world"));
		HashMap<String, ProtectedRegion>map = (HashMap<String, ProtectedRegion>) RM.getRegions();
		for (ProtectedRegion region : map.values()) {
			
		}
	}
	public static main getInstance() {
		return instance;
	}
	private void factionLeave(EventFactionsMembershipChange event) {
		if (event.getReason() == MembershipChangeReason.JOIN) {
			Faction fac = event.getMPlayer().getFaction();
			event.getMPlayer().getName();
		}else {
			if (event.getReason() == MembershipChangeReason.LEAVE) {
				
			}
		}
		
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
		if (cmd.getName() == "WGFAdd" || cmd.getName() == "WGFRemove") {
			if (cmd.getName() == "WGFAdd" && args.length == 2){
				WorldGuardPlugin plugin = getWorldGuard();
				RegionManager RM = plugin.getRegionContainer().get(((Entity) sender).getWorld());
				ProtectedRegion region = RM.getRegion(args[0]);
				if (region.getOwners().contains(sender.getName())) {
					region.setFlag(DefaultFlag.ENTRY, State.DENY);
					Faction fac = Faction.get(args[1]);
					for(MPlayer mplayer : fac.getMPlayers()) {
						Player player = Bukkit.getPlayer(mplayer.getName());
						region.getMembers().addPlayer(player.getName());
					}
				}
				
				return true;
			} else {
				if(cmd.getName() == "WGFRemove" && args.length == 2) {
					WorldGuardPlugin plugin = getWorldGuard();
					RegionManager RM = plugin.getRegionContainer().get(((Entity) sender).getWorld());
					ProtectedRegion region = RM.getRegion(args[0]);
					if (region.getOwners().contains(sender.getName())) {
						region.setFlag(DefaultFlag.ENTRY, State.DENY);
						Faction fac = Faction.get(args[1]);
						for(MPlayer mplayer : fac.getMPlayers()) {
							Player player = Bukkit.getPlayer(mplayer.getName());
							region.getMembers().removePlayer(player.getName());
						}
					}
					return true;
				}else {
					return false;
				}
			}
		} else {
			return false;
		}
	}
}