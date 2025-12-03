package dev.redstone.vetheroac.lib;

import dev.redstone.vetheroac.block.VetheroacBlocks;
import dev.redstone.vetheroac.item.VetheroacItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TexturedModel;

public class ModelLibrary extends FabricModelProvider {
    public ModelLibrary(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSingleton(VetheroacBlocks.Powerful_Tnt_Block, TexturedModel.CUBE_BOTTOM_TOP);
        blockStateModelGenerator.registerSingleton(VetheroacBlocks.Split_Tnt_Block, TexturedModel.CUBE_BOTTOM_TOP);
        blockStateModelGenerator.registerSingleton(VetheroacBlocks.Paint_Tnt_Block, TexturedModel.CUBE_BOTTOM_TOP);
        blockStateModelGenerator.registerSingleton(VetheroacBlocks.Cube_Destroyer_Block, TexturedModel.CUBE_BOTTOM_TOP);
        blockStateModelGenerator.registerSingleton(VetheroacBlocks.Rex8916_Tnt_Block, TexturedModel.CUBE_BOTTOM_TOP);
        blockStateModelGenerator.registerSingleton(VetheroacBlocks.Rain_Tnt_Block, TexturedModel.CUBE_BOTTOM_TOP);
        blockStateModelGenerator.registerSingleton(VetheroacBlocks.Chunk_Tnt_Block, TexturedModel.CUBE_BOTTOM_TOP);
        blockStateModelGenerator.registerSingleton(VetheroacBlocks.Lucky_Tnt_Block, TexturedModel.CUBE_BOTTOM_TOP);
        blockStateModelGenerator.registerSingleton(VetheroacBlocks.Zombie_Tnt_Block, TexturedModel.CUBE_BOTTOM_TOP);
        blockStateModelGenerator.registerSingleton(VetheroacBlocks.Day_Tnt_Block, TexturedModel.CUBE_BOTTOM_TOP);
        blockStateModelGenerator.registerSingleton(VetheroacBlocks.Night_Tnt_Block, TexturedModel.CUBE_BOTTOM_TOP);
        blockStateModelGenerator.registerSingleton(VetheroacBlocks.Village_Tnt_Block, TexturedModel.CUBE_BOTTOM_TOP);
        blockStateModelGenerator.registerSingleton(VetheroacBlocks.twozero_Tnt_Block, TexturedModel.CUBE_BOTTOM_TOP);
        blockStateModelGenerator.registerSingleton(VetheroacBlocks.onezero_Tnt_Block, TexturedModel.CUBE_BOTTOM_TOP);
        blockStateModelGenerator.registerSingleton(VetheroacBlocks.five_Tnt_Block, TexturedModel.CUBE_BOTTOM_TOP);
        blockStateModelGenerator.registerSingleton(VetheroacBlocks.Xray_Tnt_Block, TexturedModel.CUBE_BOTTOM_TOP);
        blockStateModelGenerator.registerSingleton(VetheroacBlocks.WARRIAR_Tnt_Block, TexturedModel.CUBE_BOTTOM_TOP);
        blockStateModelGenerator.registerSingleton(VetheroacBlocks.Wither_Tnt_Block, TexturedModel.CUBE_BOTTOM_TOP);
        blockStateModelGenerator.registerSingleton(VetheroacBlocks.Hole_Tnt_Block, TexturedModel.CUBE_BOTTOM_TOP);
        blockStateModelGenerator.registerSingleton(VetheroacBlocks.Launch_Tnt_Block, TexturedModel.CUBE_BOTTOM_TOP);
        blockStateModelGenerator.registerSingleton(VetheroacBlocks.Timer_Tnt_Block, TexturedModel.CUBE_BOTTOM_TOP);
        blockStateModelGenerator.registerSingleton(VetheroacBlocks.Lucky_Timer_Tnt_Block, TexturedModel.CUBE_BOTTOM_TOP);
        blockStateModelGenerator.registerSingleton(VetheroacBlocks.Ore_Tnt_Block, TexturedModel.CUBE_BOTTOM_TOP);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(VetheroacItems.Tnt_Launcher, Models.GENERATED);
        itemModelGenerator.register(VetheroacItems.Clear_Physics_Entities, Models.GENERATED);
    }
}
