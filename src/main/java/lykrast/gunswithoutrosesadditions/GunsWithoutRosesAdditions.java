package lykrast.gunswithoutrosesadditions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lykrast.gunswithoutrosesadditions.registry.GWRAItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(GunsWithoutRosesAdditions.MODID)
public class GunsWithoutRosesAdditions {
	public static final String MODID = "gunswithoutrosesadditions";
	
	public static final Logger LOG = LogManager.getLogger();
	
	public GunsWithoutRosesAdditions() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		GWRAItems.REG.register(bus);
		bus.addListener(GWRAItems::addToCreativeTab);
	}
	
	public static ResourceLocation rl(String name) {
		return new ResourceLocation(MODID, name);
	}
}
