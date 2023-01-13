package lykrast.gunswithoutrosesadditions.registry;

import lykrast.gunswithoutrosesadditions.CompatModids;
import lykrast.gunswithoutrosesadditions.item.EffectBullet;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.RegistryObject;

public class CompatBumblezone {
	public static RegistryObject<Item> honeyCrystalBullet;
	@ObjectHolder(registryName = CompatModids.EFFECT, value = CompatModids.BUMBLEZONE + ":wrath_of_the_hive")
	public static MobEffect WRATH = null;
	
	public static void registerItems() {
		honeyCrystalBullet = ModItems.initItem(() -> new EffectBullet(ModItems.defP(), 6, () -> new MobEffectInstance(WRATH, 20*60, 0)), "honey_crystal_bullet");
	}
}
