package lykrast.gunswithoutrosesadditions.registry;

import lykrast.gunswithoutroses.registry.ItemGroupGunsWithoutRoses;
import lykrast.gunswithoutrosesadditions.GunsWithoutRosesAdditions;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = GunsWithoutRosesAdditions.MODID)
public class ModItems {	
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> reg = event.getRegistry();
		if (ModList.get().isLoaded("gunswithoutroses")) CompatGWRItems.registerItems(reg);
	}

	public static Item.Properties defP() {
		return new Item.Properties().tab(ItemGroupGunsWithoutRoses.INSTANCE);
	}

	public static Item.Properties boss() {
		//Same rarity as Nether Star for boss drops
		return new Item.Properties().tab(ItemGroupGunsWithoutRoses.INSTANCE).rarity(Rarity.UNCOMMON);
	}

	public static Item.Properties noStack() {
		return new Item.Properties().tab(ItemGroupGunsWithoutRoses.INSTANCE).stacksTo(1);
	}

	public static Item.Properties bossNS() {
		//Same rarity as Nether Star for boss drops
		return new Item.Properties().tab(ItemGroupGunsWithoutRoses.INSTANCE).stacksTo(1).rarity(Rarity.UNCOMMON);
	}

	public static <I extends Item> I initItem(IForgeRegistry<Item> reg, I item, String name) {
		item.setRegistryName(GunsWithoutRosesAdditions.MODID, name);
		reg.register(item);
		return item;
	}
}
