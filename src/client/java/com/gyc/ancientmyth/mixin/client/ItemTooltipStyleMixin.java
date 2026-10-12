package com.gyc.ancientmyth.mixin.client;

import com.gyc.ancientmyth.client.ui.RarityTooltipStyles;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(GuiGraphicsExtractor.class)
public abstract class ItemTooltipStyleMixin {

    @ModifyArgs(
            method = "setTooltipForNextFrame(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;II)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;setTooltipForNextFrame(Lnet/minecraft/client/gui/Font;Ljava/util/List;Ljava/util/Optional;IILnet/minecraft/resources/Identifier;Z)V"
            )
    )
    private void ancientmyth$rarityStyle(
            Args args,
            Font font,
            ItemStack stack,
            int x,
            int y
    ) {
        Identifier existing = args.get(5);

        args.set(
                5,
                RarityTooltipStyles.resolve(stack, existing)
        );
    }
}
