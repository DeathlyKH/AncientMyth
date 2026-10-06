package com.gyc.ancientmyth.loot.function;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.codec.RegistryCodecs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.Optional;

public class RandomItemFromTagFunction
        extends LootItemConditionalFunction {

    public static final MapCodec<RandomItemFromTagFunction> MAP_CODEC =
            RecordCodecBuilder.mapCodec(instance ->
                    commonFields(instance)
                            .and(
                                    RegistryCodecs.holderSet(Registries.ITEM)
                                            .fieldOf("items")
                                            .forGetter(function -> function.items)
                            )
                            .apply(
                                    instance,
                                    RandomItemFromTagFunction::new
                            )
            );

    private final HolderSet<Item> items;

    public RandomItemFromTagFunction(
            Optional<Holder<LootItemCondition>> condition,
            HolderSet<Item> items
    ) {
        super(condition);
        this.items = items;
    }

    @Override
    public MapCodec<RandomItemFromTagFunction> codec() {
        return MAP_CODEC;
    }

    @Override
    protected ItemStack run(
            ItemStack stack,
            LootContext context
    ) {
        return items.getRandomElement(context.getRandom())
                .map(holder ->
                        stack.transmuteCopy(
                                holder.value(),
                                stack.getCount()
                        )
                )
                .orElse(stack);
    }
}
