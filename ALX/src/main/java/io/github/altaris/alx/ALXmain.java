package io.github.altaris.alx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class ALXmain extends JavaPlugin implements Listener {
	
	Server server = Bukkit.getServer();
	HashMap<String, Location> returnLoc;
	List<String> replacements;
	
	public ALXmain() {
		super();
		this.replacements = new ArrayList<String>();
	}

	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)this);
		this.loadConfig();
		this.returnLoc = new HashMap<String, Location>();
	}
	
	@Override
	public void onDisable() {
		this.saveConfig();
	}

	public void loadConfig() {
		this.getConfig().options().copyDefaults(true);
		this.replacements = (List<String>)this.getConfig().getStringList("replacements");
		if (this.replacements.isEmpty()) {
			this.replacements.add("!goto ;/alx goto ");
			this.replacements.add("!bring ;/alx bring ");
			this.replacements.add("!return ;/alx return ");
			this.replacements.add("!help ;/alx help ");
		}
		this.getConfig().set("replacements", (Object)this.replacements);
		this.saveConfig();
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		String message = event.getMessage();
		for (final String currentreplacement : this.replacements) {
			message = message.replaceFirst("(?i)" + currentreplacement.split(";")[0], currentreplacement.split(";")[1]);
		}
		event.setMessage(message);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase("alx") && sender instanceof Player) {
			
			Player player = (Player) sender;
			
			//Arg 0 = first word entered after command, Arg 1 = second word, etc.
			int length = args.length;
			
			if (length == 1) {
				if (args[0].equalsIgnoreCase("help")) {
					player.chat("/alx help 1");
					return true;
				}
				
				if (args[0].equalsIgnoreCase("return")) {
					if (returnLoc.containsKey(player.getName())) {
						player.teleport(returnLoc.get(player.getName()));
						return true;
					}
					
					player.sendMessage("No previously saved location!");
					return false;
				}
			}
			
			if (length == 2) {
				if (args[0].equalsIgnoreCase("goto")) {
					
					for (Player playerToGoto : Bukkit.getServer().getOnlinePlayers()) {
						if(playerToGoto.getName().equalsIgnoreCase(args[1])) {
							returnLoc.put(player.getName(), player.getLocation());
							player.teleport(playerToGoto);
							return true;
						}
					}
					
					player.sendMessage("Player not found.");
					return false;
				}
				
				if (args[0].equalsIgnoreCase("bring")) {
					
					for (Player playerToBring : Bukkit.getServer().getOnlinePlayers()) {
						if(playerToBring.getName().equalsIgnoreCase(args[1])) {
							returnLoc.put(playerToBring.getName(), playerToBring.getLocation());
							playerToBring.teleport(player);
							return true;
						}
					}

					player.sendMessage("Player not found.");
					return false;
				}
				
				if (args[0].equalsIgnoreCase("help")) {
					if(args[1].equalsIgnoreCase("1")) {
						player.sendMessage("We'll write this soon");
						return true;
					}
					
					return false;
				}
				
				if (args[0].equalsIgnoreCase("return")) {
					for (Player playerToReturn : Bukkit.getServer().getOnlinePlayers()) {
						if(playerToReturn.getName().equalsIgnoreCase(args[1])) {
							if (returnLoc.containsKey(playerToReturn.getName())) {
								playerToReturn.teleport(returnLoc.get(playerToReturn.getName()));
								return true;
							}
						}
					}
				}
			}
			
			PluginDescriptionFile pdfFile = this.getDescription();
			player.sendMessage(pdfFile.getName() + "Version" + pdfFile.getVersion() + ". Use !help for help.");
			return true;
		}
		
		return false;
	}

}
