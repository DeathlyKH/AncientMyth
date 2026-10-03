package com.gyc.ancientmyth.registry;

import com.gyc.ancientmyth.AncientMyth;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;

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

    public static void initialize() {
    }
}
