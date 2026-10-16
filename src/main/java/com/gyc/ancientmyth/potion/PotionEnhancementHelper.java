
package com.gyc.ancientmyth.potion;

import com.gyc.ancientmyth.registry.ModDataComponents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public final class PotionEnhancementHelper {

    private static final int DURATION_COST = 60 * 20;

    private PotionEnhancementHelper() {
    }

    private static MobEffectInstance getEligibleEffect(ItemStack stack) {
        if (!stack.is(Items.POTION)) {
            return null;
        }

        if (stack.getOrDefault(
                ModDataComponents.TORCHFLOWER_ENHANCEMENTS, 0) >= 1) {
            return null;
        }

        PotionContents contents =
                stack.get(DataComponents.POTION_CONTENTS);

        if (contents == null) {
            return null;
        }

        Iterator<MobEffectInstance> iterator =
                contents.getAllEffects().iterator();

        if (!iterator.hasNext()) {
            return null;
        }

        MobEffectInstance effect = iterator.next();

        if (iterator.hasNext()) {
            return null;
        }

        if (effect.getEffect().value().isInstantaneous()
                || effect.isInfiniteDuration()
                || effect.getDuration() < DURATION_COST
                || effect.getAmplifier() >= 255) {
            return null;
        }

        return effect;
    }

    public static boolean canEnhance(ItemStack stack) {
        return getEligibleEffect(stack) != null;
    }

    public static ItemStack enhance(ItemStack original) {
        MobEffectInstance effect = getEligibleEffect(original);

        if (effect == null) {
            return ItemStack.EMPTY;
        }

        PotionContents oldContents =
                original.get(DataComponents.POTION_CONTENTS);

        if (oldContents == null) {
            return ItemStack.EMPTY;
        }

        MobEffectInstance enhancedEffect = new MobEffectInstance(
                effect.getEffect(),
                effect.getDuration() - DURATION_COST,
                effect.getAmplifier() + 1,
                effect.isAmbient(),
                effect.isVisible(),
                effect.showIcon()
        );

        String originalName = oldContents.customName()
                .orElseGet(() -> oldContents.potion()
                        .map(holder -> holder.value().name())
                        .orElse("empty"));

        PotionContents newContents = new PotionContents(
                Optional.empty(),
                oldContents.customColor(),
                List.of(enhancedEffect),
                Optional.of(originalName)
        );

        ItemStack result = original.copyWithCount(1);

        result.set(DataComponents.POTION_CONTENTS, newContents);
        result.set(ModDataComponents.TORCHFLOWER_ENHANCEMENTS, 1);

        return result;
    }

    public static boolean canConvertForm(ItemStack stack) {
        return (stack.is(Items.POTION) || stack.is(Items.SPLASH_POTION))
                && stack.getOrDefault(
                ModDataComponents.TORCHFLOWER_ENHANCEMENTS,
                0
        ) > 0;
    }

    public static ItemStack convertForm(
            ItemStack original,
            Item target
    ) {
        if (!canConvertForm(original)) {
            return ItemStack.EMPTY;
        }

        return original.transmuteCopy(target);
    }

}
