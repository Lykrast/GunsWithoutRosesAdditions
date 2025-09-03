package lykrast.gunswithoutrosesadditions.config;

import net.minecraftforge.fml.config.ModConfig;

public class GWRAConfigValues {
	//aether
	public static boolean AETHER_BULLET_TOOLTIP = true;
	//deep aether
	public static boolean SKYJADE_ALT_CURVE = false;
	
	public static void refresh(ModConfig config) {
		AETHER_BULLET_TOOLTIP = GWRAConfig.COMMON.aetherBulletTooltip.get();
		SKYJADE_ALT_CURVE = GWRAConfig.COMMON.skyjadeAltCurve.get();
	}

}
