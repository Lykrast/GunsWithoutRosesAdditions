package lykrast.gunswithoutrosesadditions.registry;

import lykrast.gunswithoutroses.registry.ItemGroupGunsWithoutRoses;
import lykrast.gunswithoutrosesadditions.CompatModids;
import lykrast.gunswithoutrosesadditions.GunsWithoutRosesAdditions;
import net.minecraft.world.item.Item;
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
		if (ModList.get().isLoaded(CompatModids.UNDERGARDEN)) CompatUndergarden.registerItems(reg);
		if (ModList.get().isLoaded(CompatModids.BUMBLEZONE)) CompatBumblezone.registerItems(reg);
		if (ModList.get().isLoaded(CompatModids.BOTANIA)) CompatBotania.registerItems(reg);
		if (ModList.get().isLoaded(CompatModids.CLOUD_STORAGE)) CompatCloudStorage.registerItems(reg);
	}

	public static Item.Properties defP() {
		return new Item.Properties().tab(ItemGroupGunsWithoutRoses.INSTANCE);
	}

	public static Item.Properties noStack() {
		return new Item.Properties().tab(ItemGroupGunsWithoutRoses.INSTANCE).stacksTo(1);
	}

	public static <I extends Item> I initItem(IForgeRegistry<Item> reg, I item, String name) {
		item.setRegistryName(GunsWithoutRosesAdditions.MODID, name);
		reg.register(item);
		return item;
	}
}
