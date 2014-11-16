package io.github.altaris.alx;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class ALXmain extends JavaPlugin {
	@Override
	public void onEnable() {
		new PlayerListener(this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase("alx") && sender instanceof Player) {
			
			Player player = (Player) sender;
			
			//Arg 0 = first word entered after command, Arg 1 = second word, etc.
			int length = args.length;
			
			if (length == 2) {
				if (args[0].equalsIgnoreCase("goto")) {
					
					for (Player playerToGoto : Bukkit.getServer().getOnlinePlayers()) {
						if(playerToGoto.getName().equalsIgnoreCase(args[1])) {
							player.teleport(playerToGoto);
							return true;
						}
						
						return false;
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
