package dev.redstone.vetheroac.entity.custom.goal;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.Hand;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;

import java.util.List;
import java.util.Random;

public class Rex8916Goal extends Goal {
    private final MobEntity mob;
    private LivingEntity attacker;
    private int attackCount = 0;
    private int maxHits = 3;
    private int cooldown = 0;
    private final Random random = new Random();

    public Rex8916Goal(MobEntity mob) {
        this.mob = mob;
    }

    @Override
    public boolean canStart() {
        LivingEntity currentAttacker = mob.getAttacker();
        if (currentAttacker != null && currentAttacker != attacker) {
            return true;
        }

        if (random.nextInt(200) == 0) {
            List<PlayerEntity> players = mob.getWorld().getEntitiesByClass(PlayerEntity.class, new Box(mob.getBlockPos()).expand(8.0), LivingEntity::isAlive);
            if (!players.isEmpty()) {
                attacker = players.get(0);
                mob.setTarget(attacker);
                return true;
            }
        }

        return false;
    }

    @Override
    public void start() {
        if (attacker == null) {
            attacker = mob.getAttacker();
        }
        mob.setTarget(attacker);
        attackCount = 0;
        maxHits = 2 + random.nextInt(5);
        cooldown = 0;
    }

    @Override
    public void tick() {
        if (cooldown > 0) {
            cooldown--;
            return;
        }

        if (attacker != null && attacker.isAlive() && mob.canSee(attacker) && mob.squaredDistanceTo(attacker) < 4.0) {
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
        } else if (attacker == null || !attacker.isAlive()) {
            mob.setTarget(null);
            attacker = null;
            mob.setAttacking(false);
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