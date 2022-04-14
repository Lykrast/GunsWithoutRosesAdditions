package lykrast.gunswithoutrosesadditions.registry;

import lykrast.gunswithoutrosesadditions.item.compat.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.IForgeRegistry;

public class CompatGWRItems {
	public static Item phantasmalRifle;
	
	public static void registerItems(IForgeRegistry<Item> reg) {
		//Does not have the increased projectile speed of the sniper for more reliable hit through walls
		phantasmalRifle = ModItems.initItem(reg, 
				new PhantasmalRifle(ModItems.bossNS().durability(2376), 0, 1.6, 22, 0, 22)
				.fireSound(lykrast.gunswithoutroses.registry.ModSounds.sniper)
				.repair(() -> Ingredient.of(Items.ACACIA_BOAT)), 
				"test");
	}
}
