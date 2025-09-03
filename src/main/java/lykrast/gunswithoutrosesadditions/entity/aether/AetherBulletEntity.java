package lykrast.gunswithoutrosesadditions.entity.aether;

import lykrast.gunswithoutroses.entity.BulletEntity;
import lykrast.gunswithoutrosesadditions.registry.CompatAether;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class AetherBulletEntity extends BulletEntity {
	public AetherBulletEntity(EntityType<? extends BulletEntity> type, Level level) {
		super(type, level);
	}

	public AetherBulletEntity(Level level, LivingEntity shooter) {
		super(CompatAether.aetherBullet.get(), shooter, level);
	}
}
