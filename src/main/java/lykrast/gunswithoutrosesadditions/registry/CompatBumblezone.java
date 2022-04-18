package lykrast.gunswithoutrosesadditions.registry;

import lykrast.gunswithoutrosesadditions.CompatModids;
import lykrast.gunswithoutrosesadditions.item.EffectBullet;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

public class CompatBumblezone {
	public static Item honeyCrystalBullet;
	@ObjectHolder(CompatModids.BUMBLEZONE + ":wrath_of_the_hive")
	public static final MobEffect WRATH = null;
	
	public static void registerItems(IForgeRegistry<Item> reg) {
		honeyCrystalBullet = ModItems.initItem(reg, new EffectBullet(ModItems.defP(), 6, () -> new MobEffectInstance(WRATH, 20*60, 0)), "honey_crystal_bullet");
	}
}
