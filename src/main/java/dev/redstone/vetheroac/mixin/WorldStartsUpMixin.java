package dev.redstone.vetheroac.mixin;

import dev.redstone.vetheroac.config.VetheroacConfig;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class WorldStartsUpMixin {

    @Unique
    private int ticksUntilKill = -1;
    @Unique
    private MinecraftServer delayedServer = null;

    @Inject(method = "loadWorld", at = @At("TAIL"))
    private void onWorldLoad(CallbackInfo ci) {
        MinecraftServer server = (MinecraftServer)(Object)this;
        ServerWorld overworld = server.getWorld(World.OVERWORLD);

        VetheroacConfig config = new VetheroacConfig();

        if (config.Do_Bugfix_At_Join == false) return;

        if (overworld != null) {
            server.getCommandManager().executeWithPrefix(server.getCommandSource(), "/summon minecraft:tnt 0 0 0");
            server.getCommandManager().executeWithPrefix(server.getCommandSource(), "/vxsummon velthoric:box 0 0 0");
            server.getCommandManager().executeWithPrefix(server.getCommandSource(), "/vxsummon velthoric:box 0 -1 0");
            server.getCommandManager().executeWithPrefix(server.getCommandSource(), "/vxsummon velthoric:box 0 -2 0");
            server.getCommandManager().executeWithPrefix(server.getCommandSource(), "/vxsummon velthoric:box 0 -3 0");

            ticksUntilKill = 500;
            delayedServer = server;

            ServerTickEvents.END_SERVER_TICK.register(this::tick);
        }
    }

    @Unique
    private void tick(MinecraftServer server) {
        if (server != delayedServer || ticksUntilKill < 0) return;

        ticksUntilKill--;
        if (ticksUntilKill == 0) {
            server.getCommandManager().executeWithPrefix(server.getCommandSource(), "/vxkill @x");
                server.getCommandManager().executeWithPrefix(server.getCommandSource(),
                        "/tellraw @a \"[Vetheroac] Hello there, Vetheroac here, the bugfix initializer has ran feel free to explode stuff now\"");
            ticksUntilKill = -1;
            delayedServer = null;
        }
    }
}