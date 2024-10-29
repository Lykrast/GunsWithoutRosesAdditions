package lykrast.gunswithoutrosesadditions.registry;

import lykrast.gunswithoutrosesadditions.CompatModids;
import lykrast.gunswithoutrosesadditions.item.cloudstorage.BalloonBullet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.RegistryObject;

public class CompatCloudStorage {
	public static RegistryObject<Item> balloonBullet;
	@ObjectHolder(registryName = CompatModids.ENTITY, value = CompatModids.CLOUD_STORAGE + ":balloon")
	public static EntityType<?> BALLOON = null;
	
	public static void registerItems() {
		balloonBullet = GWRAItems.initItem(() -> new BalloonBullet(GWRAItems.defP(), 5), "balloon_bullet");
	}
}
