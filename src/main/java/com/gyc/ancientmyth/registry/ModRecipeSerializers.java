package com.gyc.ancientmyth.registry;

import com.gyc.ancientmyth.AncientMyth;
import com.gyc.ancientmyth.recipe.PotionEnhancementBrewingRecipe;
import com.gyc.ancientmyth.recipe.PotionFormConversionBrewingRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

public final class ModRecipeSerializers {

    private ModRecipeSerializers() {
    }

    public static void initialize() {
        Registry.register(
                BuiltInRegistries.RECIPE_SERIALIZER,
                AncientMyth.id("potion_enhancement"),
                PotionEnhancementBrewingRecipe.SERIALIZER
        );

        Registry.register(
                BuiltInRegistries.RECIPE_SERIALIZER,
                AncientMyth.id("potion_form_conversion"),
                PotionFormConversionBrewingRecipe.SERIALIZER
        );
    }
}