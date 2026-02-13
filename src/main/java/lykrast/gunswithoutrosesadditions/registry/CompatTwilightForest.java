package lykrast.gunswithoutrosesadditions.registry;

import lykrast.gunswithoutroses.registry.GWRSounds;
import lykrast.gunswithoutrosesadditions.CompatModids;
import lykrast.gunswithoutrosesadditions.item.twilightforest.KnightmetalBullet;
import lykrast.gunswithoutrosesadditions.item.twilightforest.KnightmetalShotgun;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.RegistryObject;

public class CompatTwilightForest {
	public static RegistryObject<Item> knightmetalShotgun;
	public static RegistryObject<Item> knightmetalBullet;
	public static TagKey<Item> KNIGHTMETAL = ItemTags.create(new ResourceLocation(CompatModids.FORGE, "ingots/knightmetal"));
	
	public static void registerItems() {
		//knightmetal tools have 512 durability and diamond strength without their bonuses
		knightmetalShotgun = GWRAItems.initItem(() -> new KnightmetalShotgun(GWRAItems.defP().durability(1051), 1, 0.5, 25, 8, 8).projectiles(3).fireSound(GWRSounds.shotgun).repair(() -> Ingredient.of(KNIGHTMETAL)), "knightmetal_shotgun");
		
		knightmetalBullet = GWRAItems.initItem(() -> new KnightmetalBullet(GWRAItems.defP(), 6), "knightmetal_bullet");
	}
}
