package dev.redstone.vetheroac.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class PlayerJoinMixin {

    @Inject(method = "onSpawn", at = @At("TAIL"))
    private void onPlayerJoin(CallbackInfo ci) {
        ServerPlayerEntity player = (ServerPlayerEntity)(Object)this;
        MinecraftServer server = player.getServer();

        if (server != null) {

            String command = String.format("/tellraw %s \"[Vetheroac] Hello there, Vetheroac here, please wait until the bugfix initializer has ran before exploding anything unless you want to get bugs\"", player.getEntityName());
            ServerCommandSource source = server.getCommandSource();
            CommandManager commandManager = server.getCommandManager();
            commandManager.executeWithPrefix(source, command);
        }
    }
}