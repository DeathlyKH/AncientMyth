package com.gyc.ancientmyth.loot.function;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.codec.RegistryCodecs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.PotDecorations;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.Optional;

public class RandomPotDecorationsFunction
        extends LootItemConditionalFunction {

    public static final MapCodec<RandomPotDecorationsFunction> MAP_CODEC =
            RecordCodecBuilder.mapCodec(instance ->
                    commonFields(instance)
                            .and(
                                    RegistryCodecs.holderSet(Registries.ITEM)
                                            .fieldOf("items")
                                            .forGetter(function -> function.items)
                            )
                            .apply(
                                    instance,
                                    RandomPotDecorationsFunction::new
                            )
            );

    private final HolderSet<Item> items;

    public RandomPotDecorationsFunction(
            Optional<Holder<LootItemCondition>> condition,
            HolderSet<Item> items
    ) {
        super(condition);
        this.items = items;
    }

    @Override
    public MapCodec<RandomPotDecorationsFunction> codec() {
        return MAP_CODEC;
    }

    @Override
    protected ItemStack run(
            ItemStack stack,
            LootContext context
    ) {
        if (stack.getItem() != Items.DECORATED_POT
                || items.size() == 0) {
            return stack;
        }

        PotDecorations decorations = new PotDecorations(
                randomDecoration(context),
                randomDecoration(context),
                randomDecoration(context),
                randomDecoration(context)
        );

        stack.set(
                DataComponents.POT_DECORATIONS,
                decorations
        );

        return stack;
    }

    private Optional<ItemStackTemplate> randomDecoration(
            LootContext context
    ) {
        return items.getRandomElement(context.getRandom())
                .map(holder ->
                        new ItemStackTemplate(holder.value())
                );
    }
}
