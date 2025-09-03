package lykrast.gunswithoutrosesadditions.config;

import net.minecraftforge.fml.config.ModConfig;

public class GWRAConfigValues {
	//deep aether
	public static boolean SKYJADE_ALT_CURVE = false;
	
	public static void refresh(ModConfig config) {
		SKYJADE_ALT_CURVE = GWRAConfig.COMMON.skyjadeAltCurve.get();
	}

}
