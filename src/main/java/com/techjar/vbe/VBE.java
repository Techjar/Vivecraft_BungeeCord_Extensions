package com.techjar.vbe;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

public class VBE extends Plugin implements Listener {

	public static final String CHANNEL = "vivecraft:data";

	@Override
	public void onEnable() {
		this.getProxy().registerChannel(CHANNEL);
	}

	@Override
	public void onDisable() {
		this.getProxy().unregisterChannel(CHANNEL);
	}

	@EventHandler
	public void on(PluginMessageEvent event) {
		if (!event.getTag().equalsIgnoreCase(CHANNEL))
			return;
		if (event.getReceiver() instanceof ProxiedPlayer) {
			ProxiedPlayer player = (ProxiedPlayer)event.getReceiver();
			player.sendData(CHANNEL, event.getData());
		} else if (event.getReceiver() instanceof Server) {
			Server server = (Server)event.getReceiver();
			server.sendData(CHANNEL, event.getData());
		}
	}


}
