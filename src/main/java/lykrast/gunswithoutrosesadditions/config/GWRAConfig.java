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
	
	public final BooleanValue skyjadeAltCurve;
	
	public GWRAConfig(ForgeConfigSpec.Builder builder) {
		builder.comment("Deep Aether");
		builder.push("deep_aether");
		skyjadeAltCurve = boolval(builder, "skyjadeAltCurve", false, "Which curve to use for the Skyjade Gun's bonus damage", "false: use the one from Deep Aether, matches the Skyjade Sword but is incredibly sharp", "true: use a reversed version of the Zanite Gun/Sword's curve, which is less harsh and has the bonus lasts ~3x longer");
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
