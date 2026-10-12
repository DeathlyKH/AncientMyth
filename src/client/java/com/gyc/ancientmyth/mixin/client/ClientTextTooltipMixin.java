package com.gyc.ancientmyth.mixin.client;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTextTooltip;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.network.chat.Style;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(ClientTextTooltip.class)
public abstract class ClientTextTooltipMixin {

    private static final float SCALE = 0.85F;

    @Shadow
    private FormattedCharSequence text;


    private boolean ancientmyth$isRaritySubtitle() {

        final boolean[] result = {false};

        this.text.accept(
                (index, style, codePoint) -> {

                    if (style != null && style.isItalic()) {
                        result[0] = true;
                    }

                    return true;
                }
        );

        return result[0];
    }


    @Inject(
            method = "getHeight",
            at = @At("RETURN"),
            cancellable = true
    )
    private void ancientmyth$scaleHeight(
            Font font,
            CallbackInfoReturnable<Integer> cir
    ) {

        if (ancientmyth$isRaritySubtitle()) {

            cir.setReturnValue(8);
        }
    }


    @Inject(
            method = "extractText",
            at = @At("HEAD")
    )
    private void ancientmyth$beforeExtract(
            GuiGraphicsExtractor graphics,
            Font font,
            int x,
            int y,
            CallbackInfo ci
    ) {

        if (ancientmyth$isRaritySubtitle()) {

            graphics.pose().pushMatrix();

            graphics.pose().translate(
                    x,
                    y - 9
            );

            graphics.pose().scale(
                    SCALE,
                    SCALE
            );

            graphics.pose().translate(
                    -x,
                    -(y - 9)
            );
        }
    }


    @Inject(
            method = "extractText",
            at = @At("RETURN")
    )
    private void ancientmyth$afterExtract(
            GuiGraphicsExtractor graphics,
            Font font,
            int x,
            int y,
            CallbackInfo ci
    ) {

        if (ancientmyth$isRaritySubtitle()) {

            graphics.pose().popMatrix();
        }
    }
}