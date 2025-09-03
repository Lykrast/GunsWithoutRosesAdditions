package lykrast.gunswithoutrosesadditions.registry;

import lykrast.gunswithoutrosesadditions.CompatModids;
import lykrast.gunswithoutrosesadditions.entity.aether.AetherBulletEntity;
import lykrast.gunswithoutrosesadditions.entity.aether.AetherBulletItem;
import lykrast.gunswithoutrosesadditions.item.aether.ZaniteGunItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.RegistryObject;

public class CompatAether {
	public static RegistryObject<Item> zaniteGun;
	public static RegistryObject<Item> zaniteBullet;
	public static TagKey<Item> ZANITE_REPAIR = ItemTags.create(new ResourceLocation(CompatModids.AETHER, "zanite_repairing"));
	//public static TagKey<Item> GRAVITITE_REPAIR = ItemTags.create(new ResourceLocation(CompatModids.AETHER, "gravitite_repairing"));
	
	public static RegistryObject<EntityType<AetherBulletEntity>> aetherBullet;
	
	public static void registerItems() {
		//zanite has same durability as iron
		//aether gun have higher accuracy lower fire rate, and the zanite bonus damage will kick in
		zaniteGun = GWRAItems.initItem(() -> new ZaniteGunItem(GWRAItems.defP().durability(513), 0, 1, 18, 1, 14).repair(() -> Ingredient.of(ZANITE_REPAIR)), "zanite_gun");
		
		zaniteBullet = GWRAItems.initItem(() -> new AetherBulletItem(GWRAItems.defP(), 6), "zanite_bullet");
	}
	
	public static void registerEntities() {
		aetherBullet = GWRAEntities.REG.register("aether_bullet", () -> EntityType.Builder.<AetherBulletEntity>of(AetherBulletEntity::new, MobCategory.MISC).sized(0.3125f, 0.3125f).setUpdateInterval(2).setTrackingRange(64).setShouldReceiveVelocityUpdates(true).build(""));
	}
}
