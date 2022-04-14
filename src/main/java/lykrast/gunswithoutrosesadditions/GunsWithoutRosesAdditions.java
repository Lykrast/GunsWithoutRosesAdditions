package lykrast.gunswithoutrosesadditions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;

@Mod(GunsWithoutRosesAdditions.MODID)
public class GunsWithoutRosesAdditions {
	public static final String MODID = "gunswithoutrosesadditions";
	
	public static final Logger LOG = LogManager.getLogger();
	
	public GunsWithoutRosesAdditions() {
		//Configs one day?
	}
	
	public static ResourceLocation rl(String name) {
		return new ResourceLocation(MODID, name);
	}
}
