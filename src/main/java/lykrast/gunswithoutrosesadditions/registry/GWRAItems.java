package lykrast.gunswithoutrosesadditions.registry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import lykrast.gunswithoutrosesadditions.CompatModids;
import lykrast.gunswithoutrosesadditions.GunsWithoutRosesAdditions;
import lykrast.gunswithoutrosesadditions.item.HasCreativeTabSpecial;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GWRAItems {	
	public static final DeferredRegister<Item> REG = DeferredRegister.create(ForgeRegistries.ITEMS, GunsWithoutRosesAdditions.MODID);
	private static List<RegistryObject<? extends Item>> orderedItemsCreative = new ArrayList<>();
	
	public static void addToCreativeTab(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey().location().equals(new ResourceLocation(CompatModids.GWR, CompatModids.GWR))) {
			orderedItemsCreative.forEach(i -> {
				if (i.get() instanceof HasCreativeTabSpecial hcts) event.accept(hcts.getCreativeTabStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
				else event.accept(i);
			});
		}
	}
	
	static {
		if (ModList.get().isLoaded(CompatModids.UNDERGARDEN)) CompatUndergarden.registerItems();
		if (ModList.get().isLoaded(CompatModids.BUMBLEZONE)) CompatBumblezone.registerItems();
		if (ModList.get().isLoaded(CompatModids.BOTANIA)) CompatBotania.registerItems();
		if (ModList.get().isLoaded(CompatModids.CLOUD_STORAGE)) CompatCloudStorage.registerItems();
		if (ModList.get().isLoaded(CompatModids.AETHER)) {
			CompatAether.registerItems();
			if (ModList.get().isLoaded(CompatModids.DEEP_AETHER)) CompatDeepAether.registerItems();
			if (ModList.get().isLoaded(CompatModids.AETHER_REDUX)) CompatAetherRedux.registerItems();
			if (ModList.get().isLoaded(CompatModids.AETHERS_DELIGHT)) CompatAethersDelight.registerItems();
		}
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
