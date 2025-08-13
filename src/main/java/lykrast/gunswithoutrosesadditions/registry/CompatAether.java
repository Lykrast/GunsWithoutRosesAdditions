package lykrast.gunswithoutrosesadditions.registry;

import lykrast.gunswithoutroses.item.BulletItem;
import lykrast.gunswithoutrosesadditions.CompatModids;
import lykrast.gunswithoutrosesadditions.item.aether.ZaniteGunItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.RegistryObject;

public class CompatAether {
	public static RegistryObject<Item> zaniteGun;
	public static RegistryObject<Item> zaniteBullet;
	public static TagKey<Item> ZANITE_REPAIR = ItemTags.create(new ResourceLocation(CompatModids.AETHER, "zanite_repairing"));
	//public static TagKey<Item> GRAVITITE_REPAIR = ItemTags.create(new ResourceLocation(CompatModids.AETHER, "gravitite_repairing"));
	
	public static void registerItems() {
		//zanite has same durability as iron
		//aether gun have higher accuracy lower fire rate, and the zanite bonus damage will kick in
		//TODO damage
		zaniteGun = GWRAItems.initItem(() -> new ZaniteGunItem(GWRAItems.defP().durability(513), 0, 1, 18, 1, 14).repair(() -> Ingredient.of(ZANITE_REPAIR)), "zanite_gun");
		
		//TODO make them aether bullets
		zaniteBullet = GWRAItems.initItem(() -> new BulletItem(GWRAItems.defP(), 6), "zanite_bullet");
	}
}
