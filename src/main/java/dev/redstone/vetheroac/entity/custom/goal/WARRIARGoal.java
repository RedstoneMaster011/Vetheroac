package dev.redstone.vetheroac.entity.custom.goal;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class WARRIARGoal extends Goal {
    private final PathAwareEntity mob;
    private final double speed;
    private PlayerEntity target;

    public WARRIARGoal(PathAwareEntity mob, double speed) {
        this.mob = mob;
        this.speed = speed;
    }

    @Override
    public boolean canStart() {
        List<PlayerEntity> players = mob.getWorld().getEntitiesByClass(PlayerEntity.class, new Box(mob.getBlockPos()).expand(16.0), PlayerEntity::isAlive);
        if (!players.isEmpty()) {
            target = players.stream().min((a, b) -> Double.compare(mob.squaredDistanceTo(a), mob.squaredDistanceTo(b))).orElse(null);
        }
        return target != null;
    }

    @Override
    public void start() {
        mob.getNavigation().startMovingTo(target, speed);
    }

    @Override
    public void tick() {
        if (target == null || !target.isAlive()) return;

        mob.getNavigation().startMovingTo(target, speed);

        if (mob.squaredDistanceTo(target) <= 1.5 * 1.5) {
            World world = mob.getWorld();
            Vec3d pos = mob.getPos();
            for (int i = 0; i < 3; i++) {
                double x = (i - 1) * 0.5;
                double y = 0;
                double z = (i % 2 == 0 ? -0.5 : 0.5);
                TntEntity tnt = new TntEntity(world, pos.getX() + 0.5 + x, pos.getY() + y - 5, pos.getZ() + 0.5 + z, null);
                tnt.setFuse(1);
                world.spawnEntity(tnt);
            }
            mob.discard();
        }
    }

    @Override
    public boolean shouldContinue() {
        return target != null && target.isAlive() && mob.isAlive();
    }

    @Override
    public void stop() {
        target = null;
        mob.getNavigation().stop();
    }
}