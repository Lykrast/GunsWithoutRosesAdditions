package lykrast.gunswithoutrosesadditions.registry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import lykrast.gunswithoutrosesadditions.CompatModids;
import lykrast.gunswithoutrosesadditions.GunsWithoutRosesAdditions;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {	
	public static final DeferredRegister<Item> REG = DeferredRegister.create(ForgeRegistries.ITEMS, GunsWithoutRosesAdditions.MODID);
	private static List<RegistryObject<? extends Item>> orderedItemsCreative = new ArrayList<>();
	@ObjectHolder(registryName = CompatModids.CREATIVE_TAB, value = CompatModids.GWR + ":gunswithoutroses")
	public static CreativeModeTab GWRTAB = null;
	
	public static void addToCreativeTab(BuildCreativeModeTabContentsEvent event) {
		if (event.getTab() == GWRTAB) {
			orderedItemsCreative.forEach(i -> event.accept(i));
		}
	}
	
	static {
		if (ModList.get().isLoaded(CompatModids.UNDERGARDEN)) CompatUndergarden.registerItems();
		if (ModList.get().isLoaded(CompatModids.BUMBLEZONE)) CompatBumblezone.registerItems();
		if (ModList.get().isLoaded(CompatModids.BOTANIA)) CompatBotania.registerItems();
		if (ModList.get().isLoaded(CompatModids.CLOUD_STORAGE)) CompatCloudStorage.registerItems();
	}

	public static Item.Properties defP() {
		return new Item.Properties();
	}

	public static Item.Properties noStack() {
		return new Item.Properties().stacksTo(1);
	}

	public static <I extends Item> RegistryObject<I> initItem(Supplier<I> item, String name) {
		REG.register(name, item);
		RegistryObject<I> rego = RegistryObject.create(GunsWithoutRosesAdditions.rl(name), ForgeRegistries.ITEMS);
		orderedItemsCreative.add(rego);
		return rego;
	}
}
