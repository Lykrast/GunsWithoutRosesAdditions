package lykrast.gunswithoutrosesadditions.registry;

import lykrast.gunswithoutroses.item.GunItem;
import lykrast.gunswithoutroses.registry.GWRSounds;
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
	public static RegistryObject<Item> zaniteGun, gravititeShotgun;
	public static RegistryObject<Item> zaniteBullet;
	public static TagKey<Item> ZANITE_REPAIR = ItemTags.create(new ResourceLocation(CompatModids.AETHER, "zanite_repairing")),
			GRAVITITE_REPAIR = ItemTags.create(new ResourceLocation(CompatModids.AETHER, "gravitite_repairing"));
	
	public static RegistryObject<EntityType<AetherBulletEntity>> aetherBullet;
	
	public static void registerItems() {
		//zanite has same durability as iron
		//aether gun have higher accuracy lower fire rate, and the zanite bonus damage will kick in
		zaniteGun = GWRAItems.initItem(() -> new ZaniteGunItem(GWRAItems.defP().durability(513), 0, 1, 18, 1, 14).repair(() -> Ingredient.of(ZANITE_REPAIR)), "zanite_gun");
		//gravitite has durability of diamond
		//TODO real stat and the knockback, entity type tag aether:unlaunchable for immune
		gravititeShotgun = GWRAItems.initItem(() -> new GunItem(GWRAItems.defP().durability(2076), 0, 0.6, 20, 6, 10).projectiles(4).fireSound(GWRSounds.shotgun).repair(() -> Ingredient.of(GRAVITITE_REPAIR)), "gravitite_shotgun");
		
		zaniteBullet = GWRAItems.initItem(() -> new AetherBulletItem(GWRAItems.defP(), 6), "zanite_bullet");
	}
	
	public static void registerEntities() {
		aetherBullet = GWRAEntities.REG.register("aether_bullet", () -> EntityType.Builder.<AetherBulletEntity>of(AetherBulletEntity::new, MobCategory.MISC).sized(0.3125f, 0.3125f).setUpdateInterval(2).setTrackingRange(64).setShouldReceiveVelocityUpdates(true).build(""));
	}
}
