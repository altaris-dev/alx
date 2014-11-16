package io.github.altaris.alx;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerListener implements Listener {
	
	List<String> replacements;
	
	public PlayerListener() {
		super();
		this.replacements = new ArrayList<String>();
	}

	public PlayerListener(ALXmain plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		String message = event.getMessage();
		
	}
	
}
