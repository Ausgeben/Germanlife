package de.ausgeben.commands;

import java.awt.Color;
import java.util.Date;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/*
 * This file was created by Ausgeben
 * Discord: Ausgeben#5578
 * 
 * This project is an order from ThunderArts Discord Server
 * Discord Invite: https://discord.gg/FX5qWgpZaK
 * 
 * Everyone can edit this file on his own and
 * develope/customize it for his own use.
 */

public class Commands extends ListenerAdapter {

	@Override
	public void onMessageReceived (MessageReceivedEvent event) {
		String[] args = event.getMessage().getContentRaw().split(" ");
			if (args[0].equalsIgnoreCase("PREFIX")) {
				if (args.length >= 2) {
					if (args[1].equalsIgnoreCase("SUBCOMMAND")) {
						
					} else {
						EmbedBuilder builder = new EmbedBuilder();
						builder.setAuthor("Fehler!");
						builder.setColor(Color.red);
						builder.setDescription("Dieser Command konnte nicht gefunden werden: \n" + args[1]);
						builder.setTimestamp(new Date().toInstant());
						builder.setFooter("Command von " + event.getMember().getEffectiveName());
						event.getChannel().sendMessage(builder.build()).queue();
					}
				} else {
					EmbedBuilder builder = new EmbedBuilder();
					builder.setAuthor("Fehler!");
					builder.setColor(Color.red);
					builder.setDescription("Dieser Command konnte nicht gefunden werden: \nzu wenig Argumente!");
					builder.setTimestamp(new Date().toInstant());
					builder.setFooter("Command von " + event.getMember().getEffectiveName());
					event.getChannel().sendMessage(builder.build()).queue();
				}
			} 
	}
	
}
