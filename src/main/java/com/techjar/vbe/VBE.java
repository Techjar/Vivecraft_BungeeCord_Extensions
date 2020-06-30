package com.techjar.vbe;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

import java.util.Arrays;

public class VBE extends Plugin implements Listener {
	public static final String[] CHANNELS = new String[]{"vivecraft:data", "Vivecraft"};

	@Override
	public void onEnable() {
		for (String channel : CHANNELS)
			this.getProxy().registerChannel(channel);
	}

	@Override
	public void onDisable() {
		for (String channel : CHANNELS)
			this.getProxy().unregisterChannel(channel);
	}

	@EventHandler
	public void on(PluginMessageEvent event) {
		String channel = event.getTag();
		if (Arrays.stream(CHANNELS).noneMatch(channel::equals))
			return;
		if (event.getReceiver() instanceof ProxiedPlayer) {
			ProxiedPlayer player = (ProxiedPlayer)event.getReceiver();
			player.sendData(channel, event.getData());
		} else if (event.getReceiver() instanceof Server) {
			Server server = (Server)event.getReceiver();
			server.sendData(channel, event.getData());
		}
	}
}
