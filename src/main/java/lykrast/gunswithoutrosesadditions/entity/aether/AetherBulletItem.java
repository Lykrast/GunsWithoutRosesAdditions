package lykrast.gunswithoutrosesadditions.entity.aether;

import java.util.List;

import javax.annotation.Nullable;

import lykrast.gunswithoutroses.entity.BulletEntity;
import lykrast.gunswithoutroses.item.BulletItem;
import lykrast.gunswithoutrosesadditions.config.GWRAConfigValues;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class AetherBulletItem extends BulletItem {

	public AetherBulletItem(Properties properties, int damage) {
		super(properties, damage);
	}

	@Override
	public BulletEntity createProjectile(Level world, ItemStack stack, LivingEntity shooter) {
		AetherBulletEntity entity = new AetherBulletEntity(world, shooter);
		entity.setItem(stack);
		entity.setDamage(damage);
		return entity;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		if (GWRAConfigValues.AETHER_BULLET_TOOLTIP) tooltip.add(Component.translatable("tooltip.gunswithoutrosesadditions.bullet.aether").withStyle(ChatFormatting.GRAY));
	}
}
