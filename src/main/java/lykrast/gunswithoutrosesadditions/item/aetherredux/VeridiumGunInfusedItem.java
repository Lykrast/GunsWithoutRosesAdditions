package lykrast.gunswithoutrosesadditions.item.aetherredux;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import lykrast.gunswithoutroses.item.GunItem;
import lykrast.gunswithoutrosesadditions.item.HasCreativeTabSpecial;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.ByteTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.zepalesque.redux.item.tools.VeridiumItem;

public class VeridiumGunInfusedItem extends GunItem implements VeridiumItem, HasCreativeTabSpecial {
	//infused, now there is other stuff to worry about
	private Supplier<? extends Item> uninfused;

	public VeridiumGunInfusedItem(Properties properties, int bonusDamage, double damageMultiplier, int fireDelay, double inaccuracy, int enchantability) {
		super(properties, bonusDamage, damageMultiplier, fireDelay, inaccuracy, enchantability);
	}

	public void linkWith(Supplier<? extends Item> uninfused) {
		//separate from constructor cause they need to refer to eachother
		this.uninfused = uninfused;
	}

	@Override
	public Item getUninfusedItem(ItemStack stack) {
		return uninfused.get();
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		var result = super.use(world, player, hand);
		if (result.getResult() == InteractionResult.CONSUME) {
			//if we fired, cooldown also the infused version and tick down the infusion
			player.getCooldowns().addCooldown(uninfused.get(), getFireDelay(player.getItemInHand(hand), player));
			ItemStack original = result.getObject();
			ItemStack transform = deplete(original, player, 1);
			if (!player.level().isClientSide() && transform != null && transform != original) {
				if (player instanceof ServerPlayer sp) sendSound(sp);
			}
			if (transform != null) return InteractionResultHolder.consume(transform);
		}
		return result;
	}

	@Override
	public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
		return super.damageItem(stack, amount, entity, onBroken) * VeridiumItem.DURABILITY_DMG_MULTIPLIER;
	}

	@Override
	protected boolean isDamageModified(ItemStack stack) {
		return true;
	}

	@Override
	protected boolean isFireDelayModified(ItemStack stack) {
		return true;
	}

	@Override
	protected void addExtraStatsTooltip(ItemStack stack, @Nullable Level world, List<Component> tooltip) {
		super.addExtraStatsTooltip(stack, world, tooltip);
		tooltip.add(Component.translatable("gui.aether_redux.infusion_info").withStyle(ChatFormatting.GRAY));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flagIn) {
		super.appendHoverText(stack, world, tooltip, flagIn);
		//put the charge outside the stat tooltip
		tooltip.add(Component.translatable("tooltip.aether_redux.infusion_charge", stack.getTag() == null ? 0 : stack.getTag().getByte(VeridiumItem.NBT_KEY)).withStyle(ChatFormatting.GRAY));
	}

	@Override
	public ItemStack getCreativeTabStack() {
		//charged in the creative tab like the normal veridium items
		ItemStack stack = new ItemStack(this);
		stack.addTagElement(VeridiumItem.NBT_KEY, ByteTag.valueOf((byte) 64));
		return stack;
	}

}
