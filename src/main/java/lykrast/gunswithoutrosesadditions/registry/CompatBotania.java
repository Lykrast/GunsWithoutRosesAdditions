package lykrast.gunswithoutrosesadditions.registry;

import lykrast.gunswithoutroses.item.BulletItem;
import lykrast.gunswithoutroses.registry.ModSounds;
import lykrast.gunswithoutrosesadditions.CompatModids;
import lykrast.gunswithoutrosesadditions.item.botania.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

public class CompatBotania {
	public static Item manasteelGun, elementiumGun, terraShotgun;
	public static Item manasteelBullet, elementiumBullet;
	
	@ObjectHolder(CompatModids.BOTANIA + ":manasteel_ingot")
	public static final Item MANASTEEL = null;
	@ObjectHolder(CompatModids.BOTANIA + ":elementium_ingot")
	public static final Item ELEMENTIUM = null;
	@ObjectHolder(CompatModids.BOTANIA + ":terrasteel_ingot")
	public static final Item TERRASTEEL = null;
	//See Terra Shotgun
	//@ObjectHolder(CompatModids.BOTANIA + ":terrablade")
	//public static final SoundEvent BLADE_SOUND = null;
	
	public static void registerItems(IForgeRegistry<Item> reg) {
		manasteelGun = ModItems.initItem(reg, new ManaGun(ModItems.defP().durability(616), 0, 1, 16, 1.5, 20).repair(() -> Ingredient.of(MANASTEEL)), "manasteel_gun");
		elementiumGun = ModItems.initItem(reg, new ManaGun(ModItems.defP().durability(1477), 0, 1, 16, 1.5, 20).chanceFreeShot(0.5).repair(() -> Ingredient.of(ELEMENTIUM)), "elementium_gun");
		terraShotgun = ModItems.initItem(reg, new TerraShotgun(ModItems.defP().durability(3058).fireResistant().rarity(Rarity.UNCOMMON), 0, 0.5, 20, 7, 26, 4).ignoreInvulnerability(true).fireSound(ModSounds.shotgun).repair(() -> Ingredient.of(TERRASTEEL)), "terra_shotgun");
		
		manasteelBullet = ModItems.initItem(reg, new BulletItem(ModItems.defP(), 7), "manasteel_bullet");
		//TODO actual effect to elementium
		elementiumBullet = ModItems.initItem(reg, new BulletItem(ModItems.defP(), 7), "elementium_bullet");
	}
}
