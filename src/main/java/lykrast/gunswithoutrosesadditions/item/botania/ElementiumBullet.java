package lykrast.gunswithoutrosesadditions.item.botania;

import java.util.List;

import javax.annotation.Nullable;

import lykrast.gunswithoutroses.entity.BulletEntity;
import lykrast.gunswithoutroses.item.BulletItem;
import lykrast.gunswithoutrosesadditions.registry.CompatBotania;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vazkii.botania.common.item.equipment.armor.elementium.ItemElementiumHelm;

public class ElementiumBullet extends BulletItem {
	//Copying the array cause it's private
	private static final MobEffect[] POTIONS = {MobEffects.BLINDNESS, MobEffects.WITHER, MobEffects.MOVEMENT_SLOWDOWN, MobEffects.WEAKNESS};

	public ElementiumBullet(Properties properties, int damage) {
		super(properties, damage);
	}
	
	//Sword bonus
	@Override
	public BulletEntity createProjectile(Level world, ItemStack stack, LivingEntity shooter) {
		BulletEntity bullet = super.createProjectile(world, stack, shooter);
		if (shooter.getMainHandItem().getItem() == CompatBotania.ELEMENTIUM_SWORD || shooter.getOffhandItem().getItem() == CompatBotania.ELEMENTIUM_SWORD) {
			//That's before the gun's damage multiplier btw
			bullet.setDamage(bullet.getDamage() + 2);
		}
		return bullet;
	}
	
	//Armor set bonus
	@Override
	public void onLivingEntityHit(BulletEntity projectile, LivingEntity target, @Nullable Entity shooter, Level world) {
		if (shooter instanceof Player && ((ItemElementiumHelm)CompatBotania.ELEMENTIUM_HELMET).hasArmorSet((Player)shooter)) {
			//Pixies are 40 ticks I'm making it a lil longer here
			target.addEffect(new MobEffectInstance(POTIONS[world.random.nextInt(POTIONS.length)], 80, 0));
		}
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add(Component.translatable(getDescriptionId(stack) + ".desc").withStyle(ChatFormatting.GRAY));
	}

}
