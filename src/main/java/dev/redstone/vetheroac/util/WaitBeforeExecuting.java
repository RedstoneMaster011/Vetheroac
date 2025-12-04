package dev.redstone.vetheroac.util;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class WaitBeforeExecuting {
    private static final List<ScheduledTask> tasks = new LinkedList<>();

    public static void init() {
        ServerTickEvents.END_SERVER_TICK.register(WaitBeforeExecuting::tick);
    }

    public static void execute(int delayTicks, Runnable task) {
        tasks.add(new ScheduledTask(delayTicks, task));
    }

    private static void tick(MinecraftServer server) {
        Iterator<ScheduledTask> it = tasks.iterator();
        while (it.hasNext()) {
            ScheduledTask t = it.next();
            if (--t.ticksRemaining <= 0) {
                t.task.run();
                it.remove();
            }
        }
    }

    private static class ScheduledTask {
        int ticksRemaining;
        Runnable task;
        ScheduledTask(int ticks, Runnable task) {
            this.ticksRemaining = ticks;
            this.task = task;
        }
    }
}
