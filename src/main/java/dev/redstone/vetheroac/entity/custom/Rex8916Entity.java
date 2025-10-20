package dev.redstone.vetheroac.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;

public class Rex8916Entity extends MobEntity {
    public Rex8916Entity(EntityType<? extends MobEntity> type, World world) {
        super(type, world);
    }

    public static DefaultAttributeContainer.Builder createRex8916Attributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25);
    }
}