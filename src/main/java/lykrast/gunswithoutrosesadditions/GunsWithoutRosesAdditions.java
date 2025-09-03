package lykrast.gunswithoutrosesadditions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lykrast.gunswithoutrosesadditions.config.GWRAConfig;
import lykrast.gunswithoutrosesadditions.config.GWRAConfigValues;
import lykrast.gunswithoutrosesadditions.registry.CompatAetherRedux;
import lykrast.gunswithoutrosesadditions.registry.GWRAItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(GunsWithoutRosesAdditions.MODID)
public class GunsWithoutRosesAdditions {
	public static final String MODID = "gunswithoutrosesadditions";

	public static final Logger LOG = LogManager.getLogger();

	public GunsWithoutRosesAdditions() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		GWRAItems.REG.register(bus);
		bus.addListener(GWRAItems::addToCreativeTab);
		bus.addListener(this::commonSetup);

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, GWRAConfig.COMMON_SPEC);
		bus.addListener(this::onModConfigEvent);
	}

	public static ResourceLocation rl(String name) {
		return new ResourceLocation(MODID, name);
	}

	public void commonSetup(FMLCommonSetupEvent event) {
		if (ModList.get().isLoaded(CompatModids.AETHER) && ModList.get().isLoaded(CompatModids.AETHER_REDUX)) CompatAetherRedux.makeTheVeridiumRevolversKiss();
	}

	public void onModConfigEvent(final ModConfigEvent event) {
		ModConfig config = event.getConfig();
		// Recalculate the configs when they change
		if (config.getSpec() == GWRAConfig.COMMON_SPEC) GWRAConfigValues.refresh(config);
	}
}
