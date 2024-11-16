package lykrast.gunswithoutrosesadditions.registry;

import lykrast.gunswithoutroses.item.BulletItem;
import lykrast.gunswithoutroses.registry.GWRSounds;
import lykrast.gunswithoutrosesadditions.CompatModids;
import lykrast.gunswithoutrosesadditions.item.botania.ElementiumBullet;
import lykrast.gunswithoutrosesadditions.item.botania.ManaGun;
import lykrast.gunswithoutrosesadditions.item.botania.TerraShotgun;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.RegistryObject;

public class CompatBotania {
	public static RegistryObject<Item> manasteelGun, elementiumGun, terraShotgun;
	public static RegistryObject<Item> manasteelBullet, elementiumBullet;
	
	@ObjectHolder(registryName = CompatModids.ITEM, value = CompatModids.BOTANIA + ":manasteel_ingot")
	public static Item MANASTEEL = null;
	@ObjectHolder(registryName = CompatModids.ITEM, value = CompatModids.BOTANIA + ":elementium_ingot")
	public static Item ELEMENTIUM = null;
	@ObjectHolder(registryName = CompatModids.ITEM, value = CompatModids.BOTANIA + ":terrasteel_ingot")
	public static Item TERRASTEEL = null;
	//See Terra Shotgun
	//@ObjectHolder(CompatModids.BOTANIA + ":terrablade")
	//public static final SoundEvent BLADE_SOUND = null;
	//For Elementium Bullet
	@ObjectHolder(registryName = CompatModids.ITEM, value = CompatModids.BOTANIA + ":elementium_sword")
	public static Item ELEMENTIUM_SWORD = null;
	@ObjectHolder(registryName = CompatModids.ITEM, value = CompatModids.BOTANIA + ":elementium_helmet")
	public static Item ELEMENTIUM_HELMET = null;
	
	public static void registerItems() {
		manasteelGun = GWRAItems.initItem(() -> new ManaGun(GWRAItems.defP().durability(616), 0, 1, 16, 2, 20).repair(() -> Ingredient.of(MANASTEEL)), "manasteel_gun");
		elementiumGun = GWRAItems.initItem(() -> new ManaGun(GWRAItems.defP().durability(1477), 0, 1, 16, 2, 20).chanceFreeShot(0.5).repair(() -> Ingredient.of(ELEMENTIUM)), "elementium_gun");
		terraShotgun = GWRAItems.initItem(() -> new TerraShotgun(GWRAItems.defP().durability(3058).fireResistant().rarity(Rarity.UNCOMMON), 0, 0.75, 24, 7, 26).projectiles(3).fireSound(GWRSounds.shotgun).repair(() -> Ingredient.of(TERRASTEEL)), "terra_shotgun");
		
		manasteelBullet = GWRAItems.initItem(() -> new BulletItem(GWRAItems.defP(), 7), "manasteel_bullet");
		elementiumBullet = GWRAItems.initItem(() -> new ElementiumBullet(GWRAItems.defP(), 7), "elementium_bullet");
	}
}
