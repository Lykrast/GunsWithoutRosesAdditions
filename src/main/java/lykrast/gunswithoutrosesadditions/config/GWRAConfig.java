package lykrast.gunswithoutrosesadditions.config;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class GWRAConfig {
	//just copying what I did for meet your fight that was copied from book wyrms
	public static final ForgeConfigSpec COMMON_SPEC;
	public static final GWRAConfig COMMON;
	
	static {
		Pair<GWRAConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(GWRAConfig::new);
		COMMON = specPair.getLeft();
		COMMON_SPEC = specPair.getRight();
	}

	//aether
	public final BooleanValue aetherBulletTooltip;
	//deep aether
	public final BooleanValue skyjadeAltCurve;
	
	public GWRAConfig(ForgeConfigSpec.Builder builder) {
		builder.comment("Aether");
		builder.push("aether");
		aetherBulletTooltip = boolval(builder, "aetherBulletTooltip", true, "Should Aether bullets say they work good on Aether mobs? (THIS ONLY AFFECTS THE TOOLTIP)",
				"Turn this off if you disabled the Aether's tool nerf in its server config (or put a mod that nukes it) or if you're doing the following Tip", 
				"Tip: to make all bullets be unnerfed but keep the global tool nerf, add #gunswithoutroses:is_bullet to the entity tag aether:treated_as_aether_entity");
		builder.pop();
		builder.comment("Deep Aether");
		builder.push("deep_aether");
		skyjadeAltCurve = boolval(builder, "skyjadeAltCurve", false, "Which curve to use for the Skyjade Gun's bonus damage",
				"false: use the one from Deep Aether, matches the Skyjade Sword but is incredibly sharp",
				"true: use a reversed version of the Zanite Gun/Sword's curve, which is less harsh and has the bonus lasts ~3x longer");
		builder.pop();
	}
	
	@SuppressWarnings("unused")
	private IntValue intval(ForgeConfigSpec.Builder builder, String name, int def, int min, int max, String... comments) {
		return builder.translation(name).comment(comments).comment("Default: " + def).defineInRange(name, def, min, max);
	}
	@SuppressWarnings("unused")
	private DoubleValue doubleval(ForgeConfigSpec.Builder builder, String name, double def, double min, double max, String... comments) {
		return builder.translation(name).comment(comments).comment("Default: " + def).defineInRange(name, def, min, max);
	}
	private BooleanValue boolval(ForgeConfigSpec.Builder builder, String name, boolean def, String... comments) {
		return builder.translation(name).comment(comments).comment("Default: " + def).define(name, def);
	}

}
