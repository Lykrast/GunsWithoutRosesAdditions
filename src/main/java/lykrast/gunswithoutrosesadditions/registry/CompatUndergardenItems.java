package lykrast.gunswithoutrosesadditions.registry;

import lykrast.gunswithoutrosesadditions.item.undergarden.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class CompatUndergardenItems {
	public static Item utheriumBullet, froststeelBullet;
	
	public static void registerItems(IForgeRegistry<Item> reg) {
		utheriumBullet = ModItems.initItem(reg, new UtheriumBullet(ModItems.defP(), 7), "utherium_bullet");
		froststeelBullet = ModItems.initItem(reg, new FroststeelBullet(ModItems.defP(), 7), "froststeel_bullet");
	}
}
