package lykrast.gunswithoutrosesadditions.item.cloudstorage;

import java.util.List;

import javax.annotation.Nullable;

import com.github.alexthe668.cloudstorage.entity.BalloonEntity;

import lykrast.gunswithoutroses.entity.BulletEntity;
import lykrast.gunswithoutroses.item.BulletItem;
import lykrast.gunswithoutrosesadditions.registry.CompatCloudStorageItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BalloonBullet extends BulletItem {
	//I don't trust taking constants from other mods classes cause I don't trust people not to move them cause I'd do it
	//Downside if Cloud Storage changes those default I won't get the new values, but should be fine I guess
	public static final int DEFAULT_COLOR = 0XE72929;
	public static final int DEFAULT_STRING_LENGTH = 1;

	public BalloonBullet(Properties properties, int damage) {
		super(properties, damage);
	}

	@Override
	public void onLivingEntityHit(BulletEntity projectile, LivingEntity target, @Nullable Entity shooter, Level world) {
		//Same behavior as a balloon arrow
		BalloonEntity balloon = (BalloonEntity) CompatCloudStorageItems.BALLOON.create(world);
		balloon.setPos(target.getX(), target.getY(1.0), target.getZ());
		balloon.setChildId(target.getUUID());
		balloon.setBalloonColor(DEFAULT_COLOR);
		balloon.setStringLength(DEFAULT_STRING_LENGTH);
		balloon.setArrow(true);
		balloon.setArrowTime(world.random.nextInt(80) + 80);
		world.addFreshEntity(balloon);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add(new TranslatableComponent(getDescriptionId(stack) + ".desc").withStyle(ChatFormatting.GRAY));
	}

}
