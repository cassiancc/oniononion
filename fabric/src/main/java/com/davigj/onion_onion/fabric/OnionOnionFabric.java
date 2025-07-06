package com.davigj.onion_onion.fabric;

import com.davigj.onion_onion.core.OOConfig;
import com.davigj.onion_onion.core.OnionOnion;
import com.davigj.onion_onion.core.registry.OOItems;
import fuzs.forgeconfigapiport.fabric.api.v5.ConfigRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.fml.config.ModConfig;

import java.util.function.Supplier;

import static com.davigj.onion_onion.core.OnionOnion.MOD_ID;

public final class OnionOnionFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        OnionOnion.init();
        OnionOnion.commonSetup();
        ConfigRegistry.INSTANCE.register(MOD_ID, ModConfig.Type.COMMON, OOConfig.COMMON_SPEC);
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FOOD_AND_DRINKS).register((event) -> {
            for (Supplier<Item> itemSupplier : OOItems.FOOD.reversed()) {
                event.addAfter(Items.RABBIT_STEW.getDefaultInstance(), itemSupplier.get().getDefaultInstance());
            }
        });
    }
}
