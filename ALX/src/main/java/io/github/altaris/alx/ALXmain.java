package io.github.altaris.alx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
	HashMap<UUID, Location> returnLoc;
	HashMap<String, Boolean> tpAccept;
	HashMap<String, UUID> tpaPlayer;
	List<String> replacements;
	
	public ALXmain() {
		super();
		this.replacements = new ArrayList<String>();
	}

	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)this);
		this.loadConfig();
		this.returnLoc = new HashMap<UUID, Location>();
		this.tpAccept = new HashMap<String, Boolean>();
		this.tpaPlayer = new HashMap<String, UUID>();
	}
	
	@Override
	public void onDisable() {
		this.saveConfig();
	}

	public void loadConfig() {
		this.getConfig().options().copyDefaults(true);
		this.replacements = (List<String>)this.getConfig().getStringList("replacements");
		if (this.replacements.isEmpty()) {
			// For all implemented commands, please specify so.
			// Some of these may have un-needed spaces following them, please review
			// I'll be organizing the coding for the commands below these at a later date, as well.
			// Help and Menu Commands
			this.replacements.add("!help;/alx help");
			this.replacements.add("!menu;/alx menu");
			this.replacements.add("!motd;/alx motd");
			this.replacements.add("!rules;/alx rules");
			this.replacements.add("!afk;/alx afk");
			this.replacements.add("@;/alx @");
			// The command above shall be for admin chat
			// Further development shall occur
			// Inspired by ULX and Essentials
			
			// Teleportation Commands
			this.replacements.add("!goto;/alx goto"); //implemented
			this.replacements.add("!bring;/alx bring"); //implemented
			this.replacements.add("!return;/alx return"); //implemented
			this.replacements.add("!send;/alx send"); //might've implemented
			this.replacements.add("!tpa;/alx tpa"); //implemented
			this.replacements.add("!tpaccept;/alx tpaccept"); //implemented
			this.replacements.add("!home;/alx home");
			this.replacements.add("!sethome;/alx sethome");
			this.replacements.add("!delhome;/alx delhome");
			this.replacements.add("!back;/alx back");
			this.replacements.add("!warp;/alx warp");
			this.replacements.add("!setwarp;/alx setwarp");
			this.replacements.add("!delwarp;/alx delwarp");
			this.replacements.add("!setspawn;/alx setspawn");
			this.replacements.add("!spawn;/alx spawn");
			// "Fun" Commands
			this.replacements.add("!armor;alx armor");
			this.replacements.add("!blind;/alx blind");
			this.replacements.add("!cloak;/alx cloak");
			this.replacements.add("!freeze;/alx freeze");
			this.replacements.add("!strip;/alx strip");
			// Health Related Commands
			this.replacements.add("!hp;/alx hp");
			this.replacements.add("!heal;/alx heal");
			this.replacements.add("!god;/alx god");
			this.replacements.add("!maul;/alx maul");
			this.replacements.add("!slay;/alx slay");
			this.replacements.add("!sslay;/alx sslay");
			this.replacements.add("!slap;/alx slap");
			this.replacements.add("!ignite;/alx ignite");
			this.replacements.add("!unigniteall;/alx unigniteall");
			this.replacements.add("!smite;/alx smite");
			this.replacements.add("!nuke;/alx nuke");
			// Jailing Commands
			this.replacements.add("!jail;/alx jail");
			this.replacements.add("!jailtp;/alx jailtp");
			// Utility Commands
			this.replacements.add("!ban;/alx ban");
			this.replacements.add("!banip;/alx banip");
			this.replacements.add("!unban;/alx unban");
			this.replacements.add("!kick;/alx kick");
			this.replacements.add("!kickall;/alx kickall");
			this.replacements.add("!noclip;/alx noclip");
			this.replacements.add("!spectate;/alx spectate");
			this.replacements.add("!who;/alx who");
			this.replacements.add("!seen;/alx seen");
			this.replacements.add("!clearinventory;/alx clearinventory"); // Let's make it also recognize !ci
			this.replacements.add("!invsee;/alx invsee");
			this.replacements.add("!spy;/alx spy"); //Social Spy
			this.replacements.add("!gamemode;/alx gamemode");
			this.replacements.add("!give;/alx give");
			this.replacements.add("!item;/alx item");
			this.replacements.add("!unlimited;/alx unlimited");
			this.replacements.add("!getpos;/alx getpos");
			this.replacements.add("!setjail;/alx setjail");
			this.replacements.add("!deljail;/alx deljail");
			this.replacements.add("!sudo;/alx sudo");
			this.replacements.add("!spawnmob;/alx spawnmob");
			this.replacements.add("!togglejail;/alx togglejail");
			// Chat Commands
			this.replacements.add("!asay;/alx asay");
			this.replacements.add("!csay;/alx csay");
			this.replacements.add("!psay;/alx psay");
			this.replacements.add("!gimp;/alx gimp");
			this.replacements.add("!mute;/alx mute");
			this.replacements.add("!thetime;/alx thetime");
			this.replacements.add("!tsay;/alx tsay");
			// Voting Commands
			this.replacements.add("!veto;/alx veto");
			this.replacements.add("!vote;/alx vote");
			this.replacements.add("!voteban;/alx voteban");
			this.replacements.add("!votekick;/alx votekick");
			// User Management Commands, implementing later
			// this.replacements.add("!addgroup;/alx addgroup");
			// this.replacements.add("!adduser;/alx adduser");
			// this.replacements.add("!groupallow;/alx groupallow");
			// this.replacements.add("!groupdeny;/alx groupdeny");
			// this.replacements.add("!removegroup;/alx removegroup");
			// this.replacements.add("!removeuser;/alx removeuser");
			// this.replacements.add("!renamegroup;/alx renamegroup");
			// this.replacements.add("!setgroupcantarget;/alx setgroupcantarget");
			// this.replacements.add("!userallow;/alx userallow");
			// this.replacements.add("!userdeny;/alx userdeny");
			// Rcon commands
			this.replacements.add("!cexec;/alx cexec");
			this.replacements.add("!rcon;/alx rcon");
			// Roleplay Commands
			this.replacements.add("!enchant;/alx enchant");
			this.replacements.add("!exp;/alx exp");
			this.replacements.add("!feed;/alx feed");
			this.replacements.add("!hat;/alx hat");
			this.replacements.add("!break;/alx break");
			this.replacements.add("!kit;/alx kit");
			this.replacements.add("!repair;/alx repair");
			this.replacements.add("!repairall;/alx repairall");
			this.replacements.add("!me;/alx me");
			this.replacements.add("!msg;/alx msg"); // Possibly replace or work with psay?
			this.replacements.add("!near;/alx near");
			this.replacements.add("!nick;/alx nick");
			this.replacements.add("!playsound;/alx playsound"); // Not sure if possible
			// Weather and Time
			this.replacements.add("!time ;/alx time ");
			this.replacements.add("!thunder;/alx thunder");
			this.replacements.add("!weather;/alx weather");
			this.replacements.add("!lighting;/alx lightning");
			// Economy, implementing later
			// this.replacements.add("!balance;/alx balance");
			// this.replacements.add("!balancetop'/alx balancetop");
			// this.replacements.add("!eco;/alx eco");
			// this.replacements.add("!pay;/alx pay");
			// this.replacements.add("!sell;/alx sell");
			// this.replacements.add("!setworth;/alx setworth");
			// this.replacements.add("!worth;/alx worth");
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
			
			if (length == 0) {
				PluginDescriptionFile pdfFile = this.getDescription();
				player.sendMessage(pdfFile.getName() + " Version " + pdfFile.getVersion() + ". Use !help for help.");
				return true;
			}
			
			if (length == 1) {
				if (args[0].equalsIgnoreCase("help")) {
					player.chat("/alx help 1");
					return true;
				}
				
				if (args[0].equalsIgnoreCase("return")) {
					if (returnLoc.containsKey(player.getUniqueId())) {
						player.teleport(returnLoc.get(player.getUniqueId()));
						return true;
					}
					
					player.sendMessage("No previously saved location!");
					return false;
				}
				
				if (args[0].equalsIgnoreCase("hp")) {
					player.sendMessage("=Currently Unable to be Implemented=");
					player.sendMessage("Sorry");
					player.sendMessage("Usage:");
					player.sendMessage("!hp" + ChatColor.GREEN + "<health>" + ChatColor.GOLD + "[targetplayer]");
					player.sendMessage("If no player is given, default to self");
					return false;
				}
				
				if (args[0].equalsIgnoreCase("tpaccept")) {
					if (tpaPlayer.containsKey(player.getName())) {
						for (Player playerToTp : Bukkit.getServer().getOnlinePlayers()) {
							if(playerToTp.getUniqueId().equals(tpaPlayer.get(player))) {
								Player playerToBeTeleported = Bukkit.getServer().getPlayer(tpaPlayer.get(player));
								player.sendMessage("Request accepted, commencing teleport.");
								playerToBeTeleported.sendMessage("Request accepted.");
								playerToBeTeleported.sendMessage("Teleportation will commence now.");
								playerToBeTeleported.sendMessage("Don't move.");
								returnLoc.put(playerToBeTeleported.getUniqueId(), playerToBeTeleported.getLocation());
								playerToBeTeleported.teleport(player);
							}
						}
						player.sendMessage("Unable to find player.");
						return false;
					}
					player.sendMessage("No pending teleport requests.");
					return true;
				}
			}
			
			if (length == 2) {
				if (args[0].equalsIgnoreCase("goto")) {
					
					for (Player playerToGoto : Bukkit.getServer().getOnlinePlayers()) {
						if(playerToGoto.getName().equalsIgnoreCase(args[1])) {
							returnLoc.put(player.getUniqueId(), player.getLocation());
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
							returnLoc.put(playerToBring.getUniqueId(), playerToBring.getLocation());
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
							if (returnLoc.containsKey(playerToReturn.getUniqueId())) {
								playerToReturn.teleport(returnLoc.get(playerToReturn.getName()));
								return true;
							}
						}
					}
					
					player.sendMessage("No saved location.");
					return false;
				}
				
				if (args[0].equalsIgnoreCase("tpa")) {
					for (Player tpaPlayer : Bukkit.getServer().getOnlinePlayers()) {
						if(tpaPlayer.getName().equalsIgnoreCase(args[1])) {
							this.tpaPlayer.put(tpaPlayer.getName(), player.getUniqueId());
							tpaPlayer.sendMessage(player.getName() + " is requesting teleport.");
							tpaPlayer.sendMessage("Accept using !tpaccept");
							player.sendMessage("Teleport request sent.");
							return true;
						}
					}
					
					player.sendMessage("Player not found.");
					return false;
				}
			}
			
			if (length == 3) {

				if (args[0].equalsIgnoreCase("send")) {
					
					for (Player playerToSend : Bukkit.getServer().getOnlinePlayers()) {
						if(playerToSend.getName().equalsIgnoreCase(args[1])) {
							for (Player playerToBeReceived : Bukkit.getServer().getOnlinePlayers()) {
								if(playerToBeReceived.getName().equalsIgnoreCase(args[2])) {
									returnLoc.put(playerToSend.getUniqueId(), playerToSend.getLocation());
									playerToSend.teleport(playerToBeReceived);
								}
							}
						}
					}
				}
			}
			
			return false;
		}
		
		return false;
	}

}
