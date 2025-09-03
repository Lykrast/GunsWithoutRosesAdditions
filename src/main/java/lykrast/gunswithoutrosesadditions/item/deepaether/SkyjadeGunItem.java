package lykrast.gunswithoutrosesadditions.item.deepaether;

import java.util.List;

import javax.annotation.Nullable;

import lykrast.gunswithoutroses.item.GunItem;
import lykrast.gunswithoutrosesadditions.config.GWRAConfigValues;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SkyjadeGunItem extends GunItem {
	//for a skyjade sword the damage goes from 11 to 6
	private static final double MIN = 0.5, MAX = 4;

	public SkyjadeGunItem(Properties properties, int bonusDamage, double damageMultiplier, int fireDelay, double inaccuracy, int enchantability) {
		super(properties, bonusDamage, damageMultiplier, fireDelay, inaccuracy, enchantability);
	}
	
	public static final double getNormalSkyjadeBonus(ItemStack gun) {
		//original formula with all doubles (baseDamage / (2.0 * stack.getDamageValue() / stack.getMaxDamage() + 0.5))-baseDamage, min 0
		//therefore multiplier is between 0 and 1, which is actually already normalized
		//it is such a harsh curve... it drops off after a few shots and it's all gone at 75% remaining durability
		double mult = 1 / (2.0 * (double)gun.getDamageValue() / (double)gun.getMaxDamage() + 0.5) - 1;
		if (mult <= 0) return 0;
		else return mult;
	}
	
	public static final double getAlternateSkyjadeBonus(ItemStack gun) {
		//this one is the zanite formula but with durability reversed so it's significantly less harsh
		//the damage is now only fully lost at 25% remaining durability
		double mult = 2.0 * (1 - (double)gun.getDamageValue() / (double)gun.getMaxDamage()) - 0.5;
		if (mult <= 0) return 0;
		else return mult / 1.5;
	}
	
	public static final double getSkyjadeBonus(ItemStack gun) {
		double mult = GWRAConfigValues.SKYJADE_ALT_CURVE ? getAlternateSkyjadeBonus(gun) : getNormalSkyjadeBonus(gun);
		//now mult has the normalized multipier, round the bonus to 0.5 (the sword rounds to 1s already)
		return Math.round(mult*2*(MAX-MIN))*0.5;
	}

	@Override
	public double getBonusDamage(ItemStack stack, @Nullable LivingEntity shooter) {
		return super.getBonusDamage(stack, shooter) + MIN + getSkyjadeBonus(stack);
	}
	
	@Override
	protected boolean isDamageModified(ItemStack stack) {
		return super.isDamageModified(stack) || getSkyjadeBonus(stack) > 0;
	}

	@Override
	protected void addExtraStatsTooltip(ItemStack stack, @Nullable Level world, List<Component> tooltip) {
		super.addExtraStatsTooltip(stack, world, tooltip);
		tooltip.add(Component.translatable("item.gunswithoutrosesadditions.skyjade_gun.desc").withStyle(ChatFormatting.GRAY));
		//copy damage tooltip calculation to display max amount
		MutableComponent values;
		double damageMultiplier = getDamageMultiplier(stack, null);
		//get super to bypass the bonus we already have
		double damageBonus = (super.getBonusDamage(stack, null) + MIN) * damageMultiplier;

		if (damageMultiplier == 1) values = Component.translatable("tooltip.gunswithoutroses.gun.damage.flat", ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(damageBonus));
		else if (damageBonus == 0) values = Component.translatable("tooltip.gunswithoutroses.gun.damage.mult", ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(damageMultiplier));
		else values = Component.translatable("tooltip.gunswithoutroses.gun.damage.both", ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(damageMultiplier), ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(damageBonus));
		
		tooltip.add(Component.translatable("item.gunswithoutrosesadditions.skyjade_gun.cap", values.withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.GRAY));
	}

}
