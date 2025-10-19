package dev.redstone.vetheroac.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TNTLauncher extends Item {
    public TNTLauncher(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            TntEntity tnt = new TntEntity(world, user.getX(), user.getEyeY(), user.getZ(), user);
            tnt.setFuse(80);

            // Launch forward
            Vec3d look = user.getRotationVec(1.0F);
            tnt.setVelocity(look.x * 1.5, look.y * 1.5, look.z * 1.5);

            world.spawnEntity(tnt);
        }
        return TypedActionResult.success(user.getStackInHand(hand), world.isClient());
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(Text.of("§c§lRight click to launch a tnt"));
    }
}
