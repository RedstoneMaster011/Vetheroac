package dev.redstone.vetheroac.client;

import dev.redstone.vetheroac.Lib.BlockLootTableLibrary;
import dev.redstone.vetheroac.Lib.ModelLibrary;
import dev.redstone.vetheroac.Lib.RecipeLibrary;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class VetheroacDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(BlockLootTableLibrary::new);
        pack.addProvider(ModelLibrary::new);
        pack.addProvider(RecipeLibrary::new);
    }
}
