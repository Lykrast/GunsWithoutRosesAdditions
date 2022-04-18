package lykrast.gunswithoutrosesadditions.registry;

import lykrast.gunswithoutrosesadditions.CompatModids;
import lykrast.gunswithoutrosesadditions.item.cloudstorage.BalloonBullet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

public class CompatCloudStorageItems {
	public static Item balloonBullet;
	@ObjectHolder(CompatModids.CLOUD_STORAGE + ":balloon")
	public static final EntityType<?> BALLOON = null;
	
	public static void registerItems(IForgeRegistry<Item> reg) {
		balloonBullet = ModItems.initItem(reg, new BalloonBullet(ModItems.defP(), 5), "balloon_bullet");
	}
}
