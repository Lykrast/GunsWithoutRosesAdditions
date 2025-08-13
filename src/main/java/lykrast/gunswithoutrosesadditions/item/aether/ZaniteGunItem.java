package lykrast.gunswithoutrosesadditions.item.aether;

import java.util.List;

import javax.annotation.Nullable;

import lykrast.gunswithoutroses.item.GunItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ZaniteGunItem extends GunItem {
	//for a zanite sword the damage goes from 6 to 14
	private static final double MIN = 0.5, MAX = 5;

	public ZaniteGunItem(Properties properties, int bonusDamage, double damageMultiplier, int fireDelay, double inaccuracy, int enchantability) {
		super(properties, bonusDamage, damageMultiplier, fireDelay, inaccuracy, enchantability);
	}
	
	public static double getNormalizedZaniteBonus(ItemStack gun) {
		//original formula with all doubles (baseDamage * (2.0 * stack.getDamageValue() / stack.getMaxDamage() + 0.5))-baseDamage, min 0
		//therefore multiplier is between 0 and 1.5, so normalize that to 0 to 1
		double mult = 2.0 * (double)gun.getDamageValue() / (double)gun.getMaxDamage() - 0.5;
		if (mult <= 0) return 0;
		else return mult / 1.5;
	}
	

	@Override
	public double getBonusDamage(ItemStack stack, @Nullable LivingEntity shooter) {
		return super.getBonusDamage(stack, shooter) + MIN + getNormalizedZaniteBonus(stack)*(MAX-MIN);
	}
	
	@Override
	protected boolean isDamageModified(ItemStack stack) {
		return super.isDamageModified(stack) || getNormalizedZaniteBonus(stack) > 0;
	}

	@Override
	protected void addExtraStatsTooltip(ItemStack stack, @Nullable Level world, List<Component> tooltip) {
		super.addExtraStatsTooltip(stack, world, tooltip);
		tooltip.add(Component.translatable("item.gunswithoutrosesadditions.zanite_gun.desc").withStyle(ChatFormatting.GRAY));
		//copy damage tooltip calculation to display max amount
		MutableComponent values;
		double damageMultiplier = getDamageMultiplier(stack, null);
		//get super to bypass the bonus we already have
		double damageBonus = (super.getBonusDamage(stack, null) + MAX) * damageMultiplier;

		if (damageMultiplier == 1) values = Component.translatable("tooltip.gunswithoutroses.gun.damage.flat", ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(damageBonus));
		else if (damageBonus == 0) values = Component.translatable("tooltip.gunswithoutroses.gun.damage.mult", ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(damageMultiplier));
		else values = Component.translatable("tooltip.gunswithoutroses.gun.damage.both", ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(damageMultiplier), ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(damageBonus));
		
		tooltip.add(Component.translatable("item.gunswithoutrosesadditions.zanite_gun.cap", values.withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.GRAY));
	}

}
