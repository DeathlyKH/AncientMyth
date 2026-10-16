
package com.gyc.ancientmyth.recipe;

import com.gyc.ancientmyth.potion.PotionEnhancementHelper;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.BrewingInput;
import net.minecraft.world.item.crafting.BrewingRecipe;
import net.minecraft.world.item.crafting.PotionIngredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class PotionEnhancementBrewingRecipe extends BrewingRecipe {

    public static final MapCodec<BrewingRecipe> CODEC =
            RecordCodecBuilder.mapCodec(instance -> instance.group(
                    PotionIngredient.MAP_CODEC.fieldOf("input")
                            .forGetter(BrewingRecipe::getInput),
                    PotionIngredient.MAP_CODEC.fieldOf("reagent")
                            .forGetter(BrewingRecipe::getReagent),
                    ItemStackTemplate.CODEC.fieldOf("output")
                            .forGetter(BrewingRecipe::getOutput)
            ).apply(instance, PotionEnhancementBrewingRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, BrewingRecipe>
            STREAM_CODEC = StreamCodec.composite(
            PotionIngredient.STREAM_CODEC,
            BrewingRecipe::getInput,
            PotionIngredient.STREAM_CODEC,
            BrewingRecipe::getReagent,
            ItemStackTemplate.STREAM_CODEC,
            BrewingRecipe::getOutput,
            PotionEnhancementBrewingRecipe::new
    );

    public static final RecipeSerializer<BrewingRecipe> SERIALIZER =
            new RecipeSerializer<>(CODEC, STREAM_CODEC);

    public PotionEnhancementBrewingRecipe(
            PotionIngredient input,
            PotionIngredient reagent,
            ItemStackTemplate output
    ) {
        super(input, reagent, output);
    }

    @Override
    public boolean matches(BrewingInput input) {
        return super.matches(input)
                && PotionEnhancementHelper.canEnhance(input.input());
    }

    @Override
    public ItemStack assemble(BrewingInput input) {
        return PotionEnhancementHelper.enhance(input.input());
    }

    @Override
    public RecipeSerializer<BrewingRecipe> getSerializer() {
        return SERIALIZER;
    }

}
