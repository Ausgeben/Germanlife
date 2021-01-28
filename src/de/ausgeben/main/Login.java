package de.ausgeben.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.security.auth.login.LoginException;

import de.ausgeben.commands.Commands;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

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

public class Login {
	
	public static Login INSTANCE;
	public ShardManager shardMan;
	
	public static void main (String[] args) throws LoginException, IllegalArgumentException  {

		new Login();
		
	}
	public Login() throws LoginException, IllegalArgumentException {
		
		INSTANCE = this;
		DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createLight("ODAzOTI0MjIwNzUyMjk4MDM1.YBE2xg.pYFu-mO1pIVs9gM6JMymzK2nuSM");
		
		builder.setStatus(OnlineStatus.ONLINE);
		builder.setActivity(Activity.playing("Dev"));
		builder.addEventListeners(new Commands()); // Initalisize Command Manager
		builder.enableIntents(GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS));
		
		shardMan = builder.build();
		System.out.println("Bot Online");
		
		shutdown();
		
	}
	
	public static Login getInstance() {
		return INSTANCE;
	}
	
	public void shutdown() {

		new Thread(() -> {

			String eingabe = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			try {
				while ((eingabe = reader.readLine()) != null) {
					if (eingabe.equalsIgnoreCase("exit")) {
						if (shardMan != null) {

							shardMan.setStatus(OnlineStatus.OFFLINE);
							System.out.println("Bot heruntergefahren.");
							shardMan.shutdown();
							final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
							executor.schedule(new Runnable() {
								@Override
								public void run() {
									System.exit(1);
								}
							}, 1500, TimeUnit.MILLISECONDS);
						} else {
							System.out.println("NullPointerException: ShardMan ist null.");
						}
						reader.close();
					} else {
						System.out.println("Du kannst nur mit 'exit' disconnecten.");
					}

				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}).start();

	}
}
