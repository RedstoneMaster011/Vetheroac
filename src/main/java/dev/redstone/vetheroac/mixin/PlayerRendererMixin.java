package dev.redstone.vetheroac.mixin;

import dev.redstone.vetheroac.physics.CapeRuntime;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityRenderer.class)
public class PlayerRendererMixin {
    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void disableVanillaCape(AbstractClientPlayerEntity player, float f, float g, MatrixStack matrices,
                                    VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (CapeRuntime.hasCape(player.getUuid())) {
            //ci.cancel();
        }
    }
}