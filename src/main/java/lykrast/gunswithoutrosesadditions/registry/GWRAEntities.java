package lykrast.gunswithoutrosesadditions.registry;

import lykrast.gunswithoutrosesadditions.CompatModids;
import lykrast.gunswithoutrosesadditions.GunsWithoutRosesAdditions;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GWRAEntities {
	public static final DeferredRegister<EntityType<?>> REG = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, GunsWithoutRosesAdditions.MODID);
	
	static {
		if (ModList.get().isLoaded(CompatModids.AETHER)) CompatAether.registerEntities();
	}

}
