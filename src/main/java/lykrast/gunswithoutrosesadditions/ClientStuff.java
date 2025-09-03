package lykrast.gunswithoutrosesadditions;

import lykrast.gunswithoutrosesadditions.registry.CompatAether;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = GunsWithoutRosesAdditions.MODID, value = Dist.CLIENT)
public class ClientStuff {

	@SubscribeEvent
	public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
		//custom bullets
		if (ModList.get().isLoaded(CompatModids.AETHER)) event.registerEntityRenderer(CompatAether.aetherBullet.get(), (context) -> new ThrownItemRenderer<>(context));
	}
}
