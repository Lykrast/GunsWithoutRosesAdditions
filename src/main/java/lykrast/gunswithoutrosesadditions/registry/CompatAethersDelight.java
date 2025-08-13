package lykrast.gunswithoutrosesadditions.registry;

import lykrast.gunswithoutroses.item.GunItem;
import lykrast.gunswithoutrosesadditions.CompatModids;
import lykrast.gunswithoutrosesadditions.item.aethersdelight.ArkeniumBulletItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.RegistryObject;

public class CompatAethersDelight {
	public static RegistryObject<Item> arkeniumGun;
	public static RegistryObject<Item> arkeniumBullet;
	@ObjectHolder(registryName = CompatModids.ITEM, value = CompatModids.AETHERS_DELIGHT + ":arkenium_ingot")
	public static Item ARKENIUM_INGOT = null;
	
	public static void registerItems() {
		//arkenium is an iron replacement so exactly same stats as iron gun
		arkeniumGun = GWRAItems.initItem(() -> new GunItem(GWRAItems.defP().durability(513), 0, 1, 16, 2, 14).repair(() -> Ingredient.of(ARKENIUM_INGOT)), "arkenium_gun");
		
		//TODO make them aether bullets
		arkeniumBullet = GWRAItems.initItem(() -> new ArkeniumBulletItem(GWRAItems.defP(), 6), "arkenium_bullet");
	}
}
