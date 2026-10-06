package com.gyc.ancientmyth.mixin;

import com.gyc.ancientmyth.registry.ModRarities;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Inject(
            method = "getStyledHoverName",
            at = @At("RETURN"),
            cancellable = true
    )
    private void ancientMyth$applyCustomRarityColor(
            CallbackInfoReturnable<Component> cir
    ) {
        ItemStack stack = (ItemStack) (Object) this;

        Rarity rarity = stack.getRarity();
        int color = ModRarities.getCustomColor(rarity);

        if (color == -1) {
            return;
        }

        cir.setReturnValue(
                cir.getReturnValue()
                        .copy()
                        .withStyle(style -> style.withColor(color))
        );
    }
}
