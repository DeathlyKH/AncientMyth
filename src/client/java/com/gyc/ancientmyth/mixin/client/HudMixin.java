package com.gyc.ancientmyth.mixin.client;

import com.gyc.ancientmyth.client.ui.MythGradient;
import com.gyc.ancientmyth.registry.ModRarities;
import net.minecraft.client.gui.Hud;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Hud.class)
public abstract class HudMixin {

    @Shadow
    private ItemStack lastToolHighlight;

    @ModifyVariable(
            method = "extractSelectedItemName",
            at = @At("STORE"),
            ordinal = 0
    )
    private MutableComponent ancientmyth$styleSelectedName(
            MutableComponent original
    ) {
        Rarity rarity = lastToolHighlight.getRarity();

        if (rarity == ModRarities.MYTH) {
            return Component.empty()
                    .append(MythGradient.animate(original));
        }

        int color = ModRarities.getCustomColor(rarity);

        if (color >= 0) {
            return original.withStyle(
                    style -> style.withColor(color)
            );
        }

        return original;
    }
}
