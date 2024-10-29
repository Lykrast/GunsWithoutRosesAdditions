package lykrast.gunswithoutrosesadditions.item.undergarden;

import java.util.List;

import javax.annotation.Nullable;

import lykrast.gunswithoutroses.entity.BulletEntity;
import lykrast.gunswithoutroses.item.BulletItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.registry.UGTags;

public class UtheriumBullet extends BulletItem {
	private static final double MULTIPLIER = 1.5;

	public UtheriumBullet(Properties properties, int damage) {
		super(properties, damage);
	}

	@Override
	public double modifyDamage(double damage, BulletEntity projectile, Entity target, @Nullable Entity shooter, Level world) {
		return target.getType().is(UGTags.Entities.ROTSPAWN) ? damage * MULTIPLIER : damage;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add(Component.translatable(getDescriptionId(stack) + ".desc", (int)((MULTIPLIER-1)*100)).withStyle(ChatFormatting.GRAY));
	}

}
