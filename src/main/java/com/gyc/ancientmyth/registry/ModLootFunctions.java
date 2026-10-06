package com.gyc.ancientmyth.registry;

import com.gyc.ancientmyth.AncientMyth;
import com.gyc.ancientmyth.loot.function.RandomItemFromTagFunction;
import com.gyc.ancientmyth.loot.function.RandomPotDecorationsFunction;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;

public final class ModLootFunctions {

    public static final MapCodec<RandomItemFromTagFunction>
            RANDOM_ITEM_FROM_TAG =
            register(
                    "random_item_from_tag",
                    RandomItemFromTagFunction.MAP_CODEC
            );

    public static final MapCodec<RandomPotDecorationsFunction>
            RANDOM_POT_DECORATIONS =
            register(
                    "random_pot_decorations",
                    RandomPotDecorationsFunction.MAP_CODEC
            );

    private ModLootFunctions() {
    }

    private static <T extends LootItemFunction> MapCodec<T> register(
            String name,
            MapCodec<T> codec
    ) {
        Registry.register(
                BuiltInRegistries.LOOT_FUNCTION_TYPE,
                AncientMyth.id(name),
                codec
        );

        return codec;
    }

    public static void initialize() {
    }
}
