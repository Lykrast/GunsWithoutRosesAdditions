package lykrast.gunswithoutrosesadditions.registry;

import lykrast.gunswithoutrosesadditions.item.EffectBullet;
import lykrast.gunswithoutrosesadditions.item.undergarden.UtheriumBullet;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class CompatUndergarden {
	public static RegistryObject<Item> utheriumBullet, froststeelBullet;
	
	public static void registerItems() {
		utheriumBullet = GWRAItems.initItem(() -> new UtheriumBullet(GWRAItems.defP(), 7), "utherium_bullet");
		//Though for real why "Froststeel" when you could have "Frosteel"?
		//Same slow as the tools because ranged is safer than the melee weapons for same as the sword and axe it would be:
		//target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600, 2));
		froststeelBullet = GWRAItems.initItem(() -> new EffectBullet(GWRAItems.defP(), 7, () -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600, 1)), "froststeel_bullet");
	}
}
