package dev.redstone.vetheroac.item;

import dev.redstone.vetheroac.Vetheroac;
import dev.redstone.vetheroac.block.custom.OPTntBlock;
import dev.redstone.vetheroac.item.custom.ClearPhysicsEntities;
import dev.redstone.vetheroac.item.custom.TNTLauncher;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class VetheroacItems {
    public static final Item Tnt_Launcher = RegisterItem("tnt_launcher", new TNTLauncher(new FabricItemSettings()));
    public static final Item Clear_Physics_Entities = RegisterItem("clear_physics_entities", new ClearPhysicsEntities(new FabricItemSettings()));

    private static Item RegisterItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Vetheroac.MOD_ID, name), item);
    }

    public static void RegisterItems() {

    }
}
