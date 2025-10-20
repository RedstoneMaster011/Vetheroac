package dev.redstone.vetheroac.block.custom;

import dev.redstone.vetheroac.block.VetheroacBlockEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class twozeroTntBlock extends BlockWithEntity {

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public twozeroTntBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new twozeroTntBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return world.isClient ? null : checkType(type, VetheroacBlockEntities.TWOZERO_TNT_BLOCK_ENTITY, twozeroTntBlockEntity::tick);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);

        boolean powered = false;

        for (Direction direction : Direction.values()) {
            BlockPos checkPos = pos.offset(direction);

            int weak = world.getEmittedRedstonePower(checkPos, direction.getOpposite());
            int strong = world.getStrongRedstonePower(checkPos, direction.getOpposite());

            if (weak > 0 || strong > 0) {
                powered = true;
                break;
            }
        }

        if (powered == true) {
            LightBlock(world, pos);
        }
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);

        boolean powered = false;

        for (Direction direction : Direction.values()) {
            BlockPos checkPos = pos.offset(direction);

            int weak = world.getEmittedRedstonePower(checkPos, direction.getOpposite());
            int strong = world.getStrongRedstonePower(checkPos, direction.getOpposite());

            if (weak > 0 || strong > 0) {
                powered = true;
                break;
            }
        }

        if (powered == true) {
            LightBlock(world, pos);
        }

    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient && player.getStackInHand(hand).isOf(Items.FLINT_AND_STEEL)) {
            world.playSound(null, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0f, 1.0f);
            LightBlock(world, pos);

            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.of("§c§lUse a flint and steel or power with redstone to light"));
        tooltip.add(Text.of("§4§lWARNING: this tnt is quite powerful, please be careful"));
        super.appendTooltip(stack, world, tooltip, options);
    }

    public void LightBlock(World world, BlockPos pos) {

        world.playSound(null, pos, SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0f, 1.0f);

        BlockEntity be = world.getBlockEntity(pos);
        if (be instanceof twozeroTntBlockEntity tnt) {
            tnt.trigger();
        }

        if (world instanceof ServerWorld serverWorld) {
            for (int i = 0; i < 15; i++) {
                double offsetX = 0.5 + (serverWorld.random.nextDouble() - 0.5);
                double offsetY = 1.0 + (serverWorld.random.nextDouble() * 0.5);
                double offsetZ = 0.5 + (serverWorld.random.nextDouble() - 0.5);

                serverWorld.spawnParticles(ParticleTypes.WAX_OFF,
                        pos.getX() + offsetX,
                        pos.getY() + offsetY,
                        pos.getZ() + offsetZ,
                        5,
                        0.0, 0.0, 0.0,
                        0.0);
            }
        }

    }
}