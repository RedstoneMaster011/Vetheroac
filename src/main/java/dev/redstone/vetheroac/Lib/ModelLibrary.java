package dev.redstone.vetheroac.Lib;

import dev.redstone.vetheroac.block.VetheroacBlocks;
import dev.redstone.vetheroac.item.VetheroacItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModelLibrary extends FabricModelProvider {
    public ModelLibrary(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(VetheroacBlocks.Powerful_Tnt_Block);
        blockStateModelGenerator.registerSimpleCubeAll(VetheroacBlocks.Split_Tnt_Block);
        blockStateModelGenerator.registerSimpleCubeAll(VetheroacBlocks.Paint_Tnt_Block);
        blockStateModelGenerator.registerSimpleCubeAll(VetheroacBlocks.Cube_Destroyer_Block);
        blockStateModelGenerator.registerSimpleCubeAll(VetheroacBlocks.Rex8916_Tnt_Block);
        blockStateModelGenerator.registerSimpleCubeAll(VetheroacBlocks.Rain_Tnt_Block);
        blockStateModelGenerator.registerSimpleCubeAll(VetheroacBlocks.Chunk_Tnt_Block);
        blockStateModelGenerator.registerSimpleCubeAll(VetheroacBlocks.Lucky_Tnt_Block);
        blockStateModelGenerator.registerSimpleCubeAll(VetheroacBlocks.Zombie_Tnt_Block);
        blockStateModelGenerator.registerSimpleCubeAll(VetheroacBlocks.Day_Tnt_Block);
        blockStateModelGenerator.registerSimpleCubeAll(VetheroacBlocks.Night_Tnt_Block);
        blockStateModelGenerator.registerSimpleCubeAll(VetheroacBlocks.Village_Tnt_Block);
        blockStateModelGenerator.registerSimpleCubeAll(VetheroacBlocks.twozero_Tnt_Block);
        blockStateModelGenerator.registerSimpleCubeAll(VetheroacBlocks.onezero_Tnt_Block);
        blockStateModelGenerator.registerSimpleCubeAll(VetheroacBlocks.five_Tnt_Block);
        blockStateModelGenerator.registerSimpleCubeAll(VetheroacBlocks.Xray_Tnt_Block);
        blockStateModelGenerator.registerSimpleCubeAll(VetheroacBlocks.WARRIAR_Tnt_Block);
        blockStateModelGenerator.registerSimpleCubeAll(VetheroacBlocks.Wither_Tnt_Block);
        blockStateModelGenerator.registerSimpleCubeAll(VetheroacBlocks.Hole_Tnt_Block);
        blockStateModelGenerator.registerSimpleCubeAll(VetheroacBlocks.Launch_Tnt_Block);
        blockStateModelGenerator.registerSimpleCubeAll(VetheroacBlocks.Timer_Tnt_Block);
        blockStateModelGenerator.registerSimpleCubeAll(VetheroacBlocks.Lucky_Timer_Tnt_Block);
        blockStateModelGenerator.registerSimpleCubeAll(VetheroacBlocks.Ore_Tnt_Block);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(VetheroacItems.Tnt_Launcher, Models.GENERATED);
        itemModelGenerator.register(VetheroacItems.Clear_Physics_Entities, Models.GENERATED);

    }
}
