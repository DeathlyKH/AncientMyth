package com.gyc.ancientmyth.registry;

import com.gyc.ancientmyth.AncientMyth;
import com.gyc.ancientmyth.menu.PlantResearchTableMenu;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

public final class ModMenus {

    public static final MenuType<PlantResearchTableMenu> PLANT_RESEARCH_TABLE =
            register(
                    "plant_research_table",
                    PlantResearchTableMenu::new
            );

    private ModMenus() {
    }

    private static <T extends AbstractContainerMenu> MenuType<T> register(
            String name,
            MenuType.MenuSupplier<T> factory
    ) {
        return Registry.register(
                BuiltInRegistries.MENU,
                AncientMyth.id(name),
                new MenuType<>(factory, FeatureFlagSet.of())
        );
    }

    public static void initialize() {
    }
}
