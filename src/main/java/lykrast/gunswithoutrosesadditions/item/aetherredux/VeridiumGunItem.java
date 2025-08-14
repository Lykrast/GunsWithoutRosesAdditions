package lykrast.gunswithoutrosesadditions.item.aetherredux;

import java.util.List;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import lykrast.gunswithoutroses.item.GunItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class VeridiumGunItem extends GunItem {
	//uninfused, just needs the tooltip and the other version of the gun to share cooldown
	private Supplier<? extends Item> infused;

	public VeridiumGunItem(Properties properties, int bonusDamage, double damageMultiplier, int fireDelay, double inaccuracy, int enchantability) {
		super(properties, bonusDamage, damageMultiplier, fireDelay, inaccuracy, enchantability);
	}
	
	public void linkWith(Supplier<? extends Item> infused) {
		//separate from constructor cause they need to refer to eachother
		this.infused = infused;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		var result = super.use(world, player, hand);
		if (result.getResult() == InteractionResult.CONSUME) {
			//if we fired, cooldown also the infused version
			player.getCooldowns().addCooldown(infused.get(), getFireDelay(player.getItemInHand(hand), player));
		}
		return result;
	}

	@Override
	protected void addExtraStatsTooltip(ItemStack stack, @Nullable Level world, List<Component> tooltip) {
		super.addExtraStatsTooltip(stack, world, tooltip);
		tooltip.add(Component.translatable("gui.aether_redux.infusion_info").withStyle(ChatFormatting.GRAY));
	}

}
