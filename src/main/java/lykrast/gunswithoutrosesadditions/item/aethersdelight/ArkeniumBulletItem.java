package lykrast.gunswithoutrosesadditions.item.aethersdelight;

import java.util.List;

import javax.annotation.Nullable;

import lykrast.gunswithoutrosesadditions.entity.aether.AetherBulletItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class ArkeniumBulletItem extends AetherBulletItem {

	public ArkeniumBulletItem(Properties properties, int damage) {
		super(properties, damage);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add(Component.translatable("tooltip.gunswithoutrosesadditions.bullet.base").withStyle(ChatFormatting.GRAY));
	}

}
