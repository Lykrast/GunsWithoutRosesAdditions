package lykrast.gunswithoutrosesadditions.registry;

import lykrast.gunswithoutroses.item.GunItem;
import lykrast.gunswithoutrosesadditions.CompatModids;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.RegistryObject;

public class CompatDeepAether {
	public static RegistryObject<Item> skyjadeGun;
	//public static RegistryObject<Item> skyjadeBullet;
	public static TagKey<Item> SKYJADE_REPAIR = ItemTags.create(new ResourceLocation(CompatModids.DEEP_AETHER, "skyjade_repairing"));
	//public static TagKey<Item> STRATUS_REPAIR = ItemTags.create(new ResourceLocation(CompatModids.DEEP_AETHER, "stratus_repairing"));
	
	public static void registerItems() {
		//skyjade has 60% of durability of zanite/iron and 0 enchatability?? (I'll just put a lower number)
		//aether gun have higher accuracy lower fire rate, and the skyjade bonus damage will kick in
		//TODO damage
		skyjadeGun = GWRAItems.initItem(() -> new GunItem(GWRAItems.defP().durability(307), 0, 1, 18, 1, 10).repair(() -> Ingredient.of(SKYJADE_REPAIR)), "skyjade_gun");
		
		//TODO make them aether bullets
		//skyjadeBullet = GWRAItems.initItem(() -> new BulletItem(GWRAItems.defP(), 6), "skyjade_bullet");
	}
}
