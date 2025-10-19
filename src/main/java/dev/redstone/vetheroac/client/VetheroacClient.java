package dev.redstone.vetheroac.client;

import dev.redstone.vetheroac.physics.VetheroacBodys;
import net.fabricmc.api.ClientModInitializer;

public class VetheroacClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        VetheroacBodys.registerClient();
    }
}
