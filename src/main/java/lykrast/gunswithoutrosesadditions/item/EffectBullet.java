package lykrast.gunswithoutrosesadditions.item;

import java.util.List;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import lykrast.gunswithoutroses.entity.BulletEntity;
import lykrast.gunswithoutroses.item.BulletItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EffectBullet extends BulletItem {
	private Supplier<MobEffectInstance> effect;
	
	public EffectBullet(Properties properties, int damage, Supplier<MobEffectInstance> effect) {
		super(properties, damage);
		this.effect = effect;
	}
	
	@Override
	public void onLivingEntityHit(BulletEntity projectile, LivingEntity target, @Nullable Entity shooter, Level world) {
		target.addEffect(effect.get());
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add(new TranslatableComponent(getDescriptionId(stack) + ".desc").withStyle(ChatFormatting.GRAY));
	}

}
