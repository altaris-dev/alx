package io.github.altaris.alx;

import org.bukkit.event.Listener;

public class PlayerListener implements Listener {
	
	public PlayerListener(ALXmain plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
	
}
