
package com.rayzr522.bglib.building.events;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.rayzr522.bglib.building.BEvent;

public class BDeathBlood extends BEvent {

	@Override
	public void onCreate() {
		addEvent(PlayerDeathEvent.class);
	}

	public static int		BLOOD_AMOUNT	= 10;
	public static int		AGE				= 100;
	public static double	VELOCITY		= 1.0;
	public static ItemStack	BLOOD			= new ItemStack(Material.INK_SACK, 0, (short) 1);

	@Override
	public void call(Event event) {

		PlayerDeathEvent e = (PlayerDeathEvent) event;
		Player player = e.getEntity();

		Random rand = new Random();

		for (int i = 0; i < BLOOD_AMOUNT; i++) {

			int xv = (int) (rand.nextFloat() * VELOCITY - VELOCITY / 2);
			int yv = (int) (rand.nextFloat() * VELOCITY);
			int zv = (int) (rand.nextFloat() * VELOCITY - VELOCITY / 2);

			Item item = player.getWorld().dropItem(player.getLocation(), BLOOD.clone());
			item.setVelocity(new Vector(xv, yv, zv));
			item.setTicksLived(6000 - AGE);

		}

	}

}