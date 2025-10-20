package dev.redstone.vetheroac;

import dev.redstone.vetheroac.block.VetheroacBlockEntities;
import dev.redstone.vetheroac.block.VetheroacBlocks;
import dev.redstone.vetheroac.config.VetheroacConfigs;
import dev.redstone.vetheroac.entity.VetheroacEntities;
import dev.redstone.vetheroac.item.ItemList;
import dev.redstone.vetheroac.item.VetheroacItems;
import dev.redstone.vetheroac.physics.VetheroacBodys;
import net.fabricmc.api.ModInitializer;

public class Vetheroac implements ModInitializer {
    public static final String MOD_ID = "vetheroac";

    @Override
    public void onInitialize() {
        System.out.println("Registering Modded");

        VetheroacBodys.register();

        VetheroacConfigs.init();

        VetheroacEntities.register();


        VetheroacBlocks.RegisterBlocks();
        VetheroacItems.RegisterItems();

        VetheroacBlockEntities.register();

        ItemList.RegisterItemList();

    }
}
