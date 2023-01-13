package lykrast.gunswithoutrosesadditions.registry;

import java.util.function.Supplier;

import lykrast.gunswithoutroses.registry.ItemGroupGunsWithoutRoses;
import lykrast.gunswithoutrosesadditions.CompatModids;
import lykrast.gunswithoutrosesadditions.GunsWithoutRosesAdditions;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {	
	public static final DeferredRegister<Item> REG = DeferredRegister.create(ForgeRegistries.ITEMS, GunsWithoutRosesAdditions.MODID);
	
	static {
		if (ModList.get().isLoaded(CompatModids.UNDERGARDEN)) CompatUndergarden.registerItems();
		if (ModList.get().isLoaded(CompatModids.BUMBLEZONE)) CompatBumblezone.registerItems();
		if (ModList.get().isLoaded(CompatModids.BOTANIA)) CompatBotania.registerItems();
		if (ModList.get().isLoaded(CompatModids.CLOUD_STORAGE)) CompatCloudStorage.registerItems();
	}

	public static Item.Properties defP() {
		return new Item.Properties().tab(ItemGroupGunsWithoutRoses.INSTANCE);
	}

	public static Item.Properties noStack() {
		return new Item.Properties().tab(ItemGroupGunsWithoutRoses.INSTANCE).stacksTo(1);
	}

	public static <I extends Item> RegistryObject<I> initItem(Supplier<I> item, String name) {
		REG.register(name, item);
		return RegistryObject.create(GunsWithoutRosesAdditions.rl(name), ForgeRegistries.ITEMS);
	}
}
