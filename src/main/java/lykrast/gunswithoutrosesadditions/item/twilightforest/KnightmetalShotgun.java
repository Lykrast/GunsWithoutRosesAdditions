package lykrast.gunswithoutrosesadditions.item.twilightforest;

import java.util.List;

import javax.annotation.Nullable;

import lykrast.gunswithoutroses.item.GunItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class KnightmetalShotgun extends GunItem {
	private static final int PER_ARMOR = 6, MAX = 3;

	public KnightmetalShotgun(Properties properties, int bonusDamage, double damageMultiplier, int fireDelay, double inaccuracy, int enchantability) {
		super(properties, bonusDamage, damageMultiplier, fireDelay, inaccuracy, enchantability);
	}
	
	@Override
	public int getProjectilesPerShot(ItemStack stack, @Nullable LivingEntity shooter) {
		if (shooter != null) {
			return super.getProjectilesPerShot(stack, shooter) + Mth.clamp(shooter.getArmorValue() / PER_ARMOR, 0, MAX);
		}
		else return super.getProjectilesPerShot(stack, shooter);
	}

	@Override
	protected void addExtraStatsTooltip(ItemStack stack, @Nullable Level world, List<Component> tooltip) {
		super.addExtraStatsTooltip(stack, world, tooltip);
		tooltip.add(Component.translatable("item.gunswithoutrosesadditions.knightmetal_shotgun.desc", PER_ARMOR).withStyle(ChatFormatting.GRAY));
		
		//Copy tooltip formatting from super
		MutableComponent values;
		double damageMultiplier = getDamageMultiplier(stack, null);
		double damageBonus = getBonusDamage(stack, null) * damageMultiplier;
		int projectiles = getProjectilesPerShot(stack, null) + MAX;
		//get the formatted total damage
		double sgDamageMultiplier = damageMultiplier*projectiles;
		double sgDamageBonus = damageBonus*projectiles;
		if (damageBonus == 0) values = Component.translatable("tooltip.gunswithoutroses.gun.damage.mult", ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(sgDamageMultiplier));
		else values = Component.translatable("tooltip.gunswithoutroses.gun.damage.both", ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(sgDamageMultiplier), ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(sgDamageBonus));
		//get that on the values component
		values = Component.translatable("tooltip.gunswithoutroses.shotgun.projectiles.values",
				Component.literal(Integer.toString(projectiles)).withStyle(ChatFormatting.WHITE),
				values.withStyle(ChatFormatting.WHITE))
				.withStyle(ChatFormatting.GRAY);
		
		tooltip.add(Component.translatable("item.gunswithoutrosesadditions.knightmetal_shotgun.cap", values).withStyle(ChatFormatting.GRAY));
	}

}
