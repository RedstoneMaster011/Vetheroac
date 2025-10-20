package dev.redstone.vetheroac.entity.custom;

import dev.redstone.vetheroac.entity.custom.goal.Rex8916Goal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.entity.Entity;

public class Rex8916Entity extends PathAwareEntity {
    public Rex8916Entity(EntityType<? extends PathAwareEntity> type, World world) {
        super(type, world);
        this.setStepHeight(1.0f);
    }

    public static DefaultAttributeContainer.Builder createRex8916Attributes() {
        return PathAwareEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(2, new LookAroundGoal(this));
        this.goalSelector.add(3, new MeleeAttackGoal(this, 1.2, false));
        this.goalSelector.add(4, new Rex8916Goal(this));
    }

    @Override
    public boolean tryAttack(Entity target) {
        boolean success = super.tryAttack(target);
        if (success) {
            this.swingHand(Hand.MAIN_HAND); // Triggers animation
            this.setAttacking(true);        // Triggers pose
        }
        return success;
    }

    @Override
    public void tick() {
        super.tick();
        this.tickHandSwing(); // Ensures swing animation updates
    }
}