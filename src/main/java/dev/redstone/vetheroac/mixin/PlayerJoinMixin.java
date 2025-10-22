package dev.redstone.vetheroac.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import dev.redstone.vetheroac.config.VetheroacConfigs;

@Mixin(ServerPlayerEntity.class)
public class PlayerJoinMixin {

    private static boolean bugfixMessageSent = false;

    @Inject(method = "onSpawn", at = @At("TAIL"))
    private void onPlayerJoin(CallbackInfo ci) {
        ServerPlayerEntity player = (ServerPlayerEntity)(Object)this;
        MinecraftServer server = player.getServer();
        if (server == null) return;
        if (bugfixMessageSent) return;

        bugfixMessageSent = true;

        server.execute(() -> {
            String command = String.format("/tellraw %s \"[Vetheroac] Hello there, Vetheroac here, the first 1 or 2 explosions may be a little buggy, this mod is still in alpha and so is the api, farewell " + player.getEntityName() + "\"", player.getEntityName());
            ServerCommandSource source = server.getCommandSource();
            CommandManager commandManager = server.getCommandManager();
            commandManager.executeWithPrefix(source, command);
        });
    }
}