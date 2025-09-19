package lykrast.gunswithoutrosesadditions.item.aether;

import java.util.List;

import javax.annotation.Nullable;

import lykrast.gunswithoutroses.item.GunItem;
import lykrast.gunswithoutroses.item.IBullet;
import lykrast.gunswithoutrosesadditions.registry.CompatAether;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GravititeShotgunItem extends GunItem {
	private static final double RANGE = 4, RANGESQR = RANGE*RANGE, KNOCKBACK = 1;

	public GravititeShotgunItem(Properties properties, int bonusDamage, double damageMultiplier, int fireDelay, double inaccuracy, int enchantability) {
		super(properties, bonusDamage, damageMultiplier, fireDelay, inaccuracy, enchantability);
	}
	
	@Override
	protected void shoot(Level world, Player player, ItemStack gun, ItemStack ammo, IBullet bulletItem, boolean bulletFree) {
		knocbackBurst(player, world);
		super.shoot(world, player, gun, ammo, bulletItem, bulletFree);
	}
	

	@Override
	public void shootAt(LivingEntity shooter, double x, double y, double z, ItemStack gun, ItemStack ammo, IBullet bulletItem, double mobSpread, boolean bulletFree) {
		knocbackBurst(shooter, shooter.level());
		super.shootAt(shooter, x, y, z, gun, ammo, bulletItem, mobSpread, bulletFree);
	}
	
	protected void knocbackBurst(LivingEntity shooter, Level level) {
		//TODO particles
		var origin = shooter.position();
		var look = shooter.getLookAngle();
		for (LivingEntity ent : level.getEntitiesOfClass(LivingEntity.class, shooter.getBoundingBox().inflate(RANGE))) {
			//immunity check
			if (ent.getType().is(CompatAether.UNLAUNCHABLE)) continue;
			//distance check so we actually a sphere
			double distSqr = ent.distanceToSqr(origin);
			if (distSqr > RANGESQR) continue;
			//angle check, if I'm mathing right dot product of 0 is 90° to either side, and negative is behind, so that should be a half sphere
			//plus a lil sphere for what's adjacent for leniency
			var toTarget = ent.position().subtract(origin).normalize();
			if (distSqr > 1 && toTarget.dot(look) < 0) continue;
			//and now we actually knock back
			double amplitude = KNOCKBACK*((1-(distSqr / RANGESQR))*0.8 + 0.2);
			amplitude *= Math.max(0, 1 - ent.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
			ent.push(toTarget.x*amplitude,(Math.max(0,toTarget.y)+0.2)*amplitude,toTarget.z*amplitude);
			
		}
	}

	@Override
	protected void addExtraStatsTooltip(ItemStack stack, @Nullable Level world, List<Component> tooltip) {
		super.addExtraStatsTooltip(stack, world, tooltip);
		tooltip.add(Component.translatable("item.gunswithoutrosesadditions.gravitite_shotgun.desc").withStyle(ChatFormatting.GRAY));
	}

}
