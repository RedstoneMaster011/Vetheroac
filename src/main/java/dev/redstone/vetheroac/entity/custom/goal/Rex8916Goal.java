package dev.redstone.vetheroac.entity.custom.goal;

import dev.redstone.vetheroac.entity.custom.WARRIAREntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;

import java.util.Comparator;
import java.util.List;

public class Rex8916Goal extends Goal {
    private final MobEntity mob;
    private LivingEntity attacker;
    private int attackCount = 0;
    private int maxHits = 3;
    private int cooldown = 0;

    public Rex8916Goal(MobEntity mob) {
        this.mob = mob;
    }

    @Override
    public boolean canStart() {
        List<LivingEntity> candidates = mob.getWorld().getEntitiesByClass(LivingEntity.class,
                new Box(mob.getBlockPos()).expand(16.0),
                e -> e.isAlive() && (e instanceof PlayerEntity || e instanceof WARRIAREntity) && e != mob);

        attacker = candidates.stream()
                .min(Comparator.comparingDouble(mob::squaredDistanceTo))
                .orElse(null);

        if (attacker != null) {
            mob.setTarget(attacker);
            return true;
        }

        return false;
    }

    @Override
    public void start() {
        mob.setTarget(attacker);
        attackCount = 0;
        cooldown = 0;
    }

    @Override
    public void tick() {
        if (!mob.isAlive()) return;

        List<LivingEntity> candidates = mob.getWorld().getEntitiesByClass(LivingEntity.class,
                new Box(mob.getBlockPos()).expand(16.0),
                e -> e.isAlive() && (e instanceof PlayerEntity || e instanceof WARRIAREntity) && e != mob);

        LivingEntity closest = candidates.stream()
                .min(Comparator.comparingDouble(mob::squaredDistanceTo))
                .orElse(null);

        if (closest != null && closest != attacker) {
            attacker = closest;
            mob.setTarget(attacker);
        }

        if (attacker != null && attacker.isAlive()) {
            mob.getNavigation().startMovingTo(attacker, 1.2);

            if (mob.canSee(attacker) && mob.squaredDistanceTo(attacker) < 4.0) {
                mob.swingHand(Hand.MAIN_HAND);
                mob.setAttacking(true);
                mob.tryAttack(attacker);
                attackCount++;
                cooldown = 20;

                if (attackCount >= maxHits) {
                    mob.setTarget(null);
                    attacker = null;
                    mob.setAttacking(false);
                }
            }
        } else {
            mob.setTarget(null);
            attacker = null;
            mob.setAttacking(false);
        }

        if (cooldown > 0) {
            cooldown--;
        }
    }

    @Override
    public boolean shouldContinue() {
        return attacker != null && attacker.isAlive() && attackCount < maxHits;
    }

    @Override
    public void stop() {
        mob.setTarget(null);
        attacker = null;
        mob.setAttacking(false);
    }
}