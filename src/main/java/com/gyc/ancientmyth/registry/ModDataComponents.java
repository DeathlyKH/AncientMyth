package com.gyc.ancientmyth.registry;

import com.gyc.ancientmyth.AncientMyth;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;

public final class ModDataComponents {

    private ModDataComponents() {
    }

    public static <T> DataComponentType<T> register(
            String name,
            DataComponentType<T> componentType
    ) {
        return Registry.register(
                BuiltInRegistries.DATA_COMPONENT_TYPE,
                AncientMyth.id(name),
                componentType
        );
    }

    public static final DataComponentType<Integer> TORCHFLOWER_ENHANCEMENTS =
            register(
                    "torchflower_enhancements",
                    DataComponentType.<Integer>builder()
                            .persistent(Codec.intRange(0, 1))
                            .networkSynchronized(ByteBufCodecs.VAR_INT)
                            .build()
            );

    public static void initialize() {
    }
}
