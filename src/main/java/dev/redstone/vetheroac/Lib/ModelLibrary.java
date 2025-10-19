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
        blockStateModelGenerator.registerSimpleCubeAll(VetheroacBlocks.OP_Tnt_Block);
        blockStateModelGenerator.registerSimpleCubeAll(VetheroacBlocks.Split_Tnt_Block);
        blockStateModelGenerator.registerSimpleCubeAll(VetheroacBlocks.Paint_Tnt_Block);
        blockStateModelGenerator.registerSimpleCubeAll(VetheroacBlocks.Cube_Destroyer_Block);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(VetheroacItems.Tnt_Launcher, Models.GENERATED);
        itemModelGenerator.register(VetheroacItems.Clear_Physics_Entities, Models.GENERATED);

    }
}
