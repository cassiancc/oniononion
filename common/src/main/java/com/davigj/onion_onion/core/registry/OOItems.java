package com.davigj.onion_onion.core.registry;

import com.davigj.onion_onion.core.OnionOnion;
import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.registry.ModEffects;

import java.util.List;
import java.util.function.Supplier;

import static com.davigj.onion_onion.core.OnionOnion.MOD_ID;

public class OOItems {
    public static final DeferredRegister<Item> HELPER = DeferredRegister.create(MOD_ID, Registries.ITEM);

    public static final Supplier<Item> ONION_SLICE = HELPER.register("onion_slice", () ->
            new Item(properties("onion_slice").food((new FoodProperties.Builder()).nutrition(1).saturationModifier(0.6F).build())));

    public static final Supplier<Item> ONION_RINGS = HELPER.register("onion_rings", () ->
            new Item(properties("onion_rings").food((new FoodProperties.Builder()).nutrition(2).saturationModifier(0.7F).build())));

    public static final Supplier<Item> MOTLEY_GRILL_BLOCK = HELPER.register("motley_grill_block", () -> new BlockItem(
            OOBlocks.MOTLEY_GRILL_BLOCK.get(), properties("motley_grill_block").useBlockDescriptionPrefix().stacksTo(1).craftRemainder(Items.IRON_INGOT)));

    public static final Supplier<Item> MOTLEY_GRILL = HELPER.register("motley_grill", () -> new ConsumableItem(
            properties("motley_grill").food((new FoodProperties.Builder()).nutrition(9).saturationModifier(0.7F)
                    .build(),
            Consumable.builder().onConsume(
                    new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(Holder.direct(ModEffects.NOURISHMENT.value()), 180 * 20), 1.0F)).build())
                    .craftRemainder(Items.BOWL).stacksTo(16), true));

    private static Item.Properties properties(String s) {
        return new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MOD_ID, s)));
    }

    public static List<Supplier<Item>> FOOD = List.of(ONION_SLICE, ONION_RINGS, MOTLEY_GRILL, MOTLEY_GRILL_BLOCK);
}
