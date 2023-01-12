package lykrast.gunswithoutrosesadditions.item.botania;

import java.util.List;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import lykrast.gunswithoutroses.item.IBullet;
import lykrast.gunswithoutroses.item.ShotgunItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.api.mana.ILensEffect;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.entity.EntityManaBurst;
import vazkii.botania.common.item.equipment.tool.ToolCommons;

public class TerraShotgun extends ShotgunItem implements ILensEffect {
	//Match the Terra Blade
	private static final int MANA_PER_DAMAGE = 100;

	public TerraShotgun(Properties properties, int bonusDamage, double damageMultiplier, int fireDelay, double inaccuracy, int enchantability, int bulletCount) {
		super(properties, bonusDamage, damageMultiplier, fireDelay, inaccuracy, enchantability, bulletCount);
	}
	
	//Copied from Manasteel tools cause we need the same behavior
	@Override
	public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
		return ToolCommons.damageItemIfPossible(stack, amount, entity, MANA_PER_DAMAGE);
	}

	@Override
	public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
		if (!world.isClientSide && entity instanceof Player player && stack.getDamageValue() > 0 && ManaItemHandler.instance().requestManaExactForTool(stack, player, MANA_PER_DAMAGE * 2, true)) {
			stack.setDamageValue(stack.getDamageValue() - 1);
		}
	}

	@Override
	protected void addExtraStatsTooltip(ItemStack stack, @Nullable Level world, List<Component> tooltip) {
		super.addExtraStatsTooltip(stack, world, tooltip);
		tooltip.add(Component.translatable(getDescriptionId() + ".desc").withStyle(ChatFormatting.GRAY));
		tooltip.add(Component.translatable(ManaGun.MANA_REPAIR).withStyle(ChatFormatting.GRAY));
	}
	
	@Override
	protected void shoot(Level world, Player player, ItemStack gun, ItemStack ammo, IBullet bulletItem, boolean bulletFree) {
		super.shoot(world, player, gun, ammo, bulletItem, bulletFree);
		//Copied from the Terra Blade
		EntityManaBurst burst = getBurst(player, gun);
		player.level.addFreshEntity(burst);
		//Can't really hear it in game so might as well not play it for subtitles
		//player.level.playSound(null, player.getX(), player.getY(), player.getZ(), CompatBotaniaItems.BLADE_SOUND, SoundSource.PLAYERS, 1, 1);
	}
	
	//Mana Burst behavior is similar to Terra Blade but only hits 1 target because it needs to ignore iframes
	public EntityManaBurst getBurst(Player player, ItemStack stack) {
		EntityManaBurst burst = new EntityManaBurst(player);

		burst.setColor(0x20FF20);
		burst.setMana(MANA_PER_DAMAGE);
		burst.setStartingMana(MANA_PER_DAMAGE);
		burst.setMinManaLoss(40);
		burst.setManaLossPerTick(4);
		burst.setGravity(0);
		burst.setDeltaMovement(burst.getDeltaMovement().scale(7));

		burst.setSourceLens(stack.copy());
		return burst;
	}
	
	@Override
	public void apply(ItemStack stack, BurstProperties props, Level level) {}

	@Override
	public boolean collideBurst(IManaBurst burst, HitResult pos, boolean isManaBlock, boolean shouldKill, ItemStack stack) {
		return shouldKill;
	}

	@Override
	public void updateBurst(IManaBurst burst, ItemStack stack) {
		//Still copying the Terra Blade but slightly different cause we need to ignore iframes
		ThrowableProjectile entity = burst.entity();
		AABB axis = new AABB(entity.getX(), entity.getY(), entity.getZ(), entity.xOld, entity.yOld, entity.zOld).inflate(1);
		List<LivingEntity> entities = entity.level.getEntitiesOfClass(LivingEntity.class, axis);
		Entity thrower = entity.getOwner();

		for (LivingEntity living : entities) {
			if (living == thrower || living instanceof Player livingPlayer && thrower instanceof Player throwingPlayer && !throwingPlayer.canHarmPlayer(livingPlayer)) {
				continue;
			}

			int cost = MANA_PER_DAMAGE / 3;
			int mana = burst.getMana();
			if (mana >= cost) {
				burst.setMana(mana - cost);
				float damage = 7;
				if (!burst.isFake() && !entity.level.isClientSide) {
					DamageSource source = DamageSource.MAGIC;
					if (thrower instanceof Player player) {
						source = DamageSource.playerAttack(player);
					}
					else if (thrower instanceof LivingEntity livingEntity) {
						source = DamageSource.mobAttack(livingEntity);
					}
					int lastHurtResistant = living.invulnerableTime;
					living.invulnerableTime = 0;
					if (!living.hurt(source, damage)) living.invulnerableTime = lastHurtResistant;
					entity.discard();
					break;
				}
			}
		}
	}
	
	@Override
	public boolean doParticles(IManaBurst burst, ItemStack stack) {
		return true;
	}

}
