package lykrast.gunswithoutrosesadditions.item.botania;

import java.util.List;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import lykrast.gunswithoutroses.item.GunItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.equipment.tool.ToolCommons;

public class ManaGun extends GunItem {
	//Match Manasteel/Elementium tools
	private static final int MANA_PER_DAMAGE = 60;
	public static final String MANA_REPAIR = "item.gunswithoutrosesadditions.manasteel_gun.desc";

	public ManaGun(Properties properties, int bonusDamage, double damageMultiplier, int fireDelay, double inaccuracy, int enchantability) {
		super(properties, bonusDamage, damageMultiplier, fireDelay, inaccuracy, enchantability);
	}
	
	//Copied from Manasteel tools cause we need the same behavior
	@Override
	public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
		return ToolCommons.damageItemIfPossible(stack, amount, entity, MANA_PER_DAMAGE);
	}

	@Override
	public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
		if (!world.isClientSide && entity instanceof Player player && stack.getDamageValue() > 0 && ManaItemHandler.instance().requestManaExactForTool(stack, player, MANA_PER_DAMAGE * 2, true)) {
			stack.setDamageValue(stack.getDamageValue() - 1);
		}
	}

	@Override
	protected void addExtraStatsTooltip(ItemStack stack, @Nullable Level world, List<Component> tooltip) {
		super.addExtraStatsTooltip(stack, world, tooltip);
		tooltip.add(Component.translatable(MANA_REPAIR).withStyle(ChatFormatting.GRAY));
	}

}
