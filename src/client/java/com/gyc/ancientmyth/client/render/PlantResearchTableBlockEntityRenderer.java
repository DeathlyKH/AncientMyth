package com.gyc.ancientmyth.client.render;

import com.gyc.ancientmyth.block.entity.PlantResearchTableBlockEntity;
import com.gyc.ancientmyth.client.render.state.PlantResearchTableRenderState;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

import org.jspecify.annotations.Nullable;

public class PlantResearchTableBlockEntityRenderer
        implements BlockEntityRenderer<
        PlantResearchTableBlockEntity,
        PlantResearchTableRenderState
        > {

    private static final BlockDisplayContext DISPLAY_CONTEXT =
            BlockDisplayContext.create();

    private static final float DISPLAY_SCALE = 0.58F;

    private final BlockModelResolver blockModelResolver;

    public PlantResearchTableBlockEntityRenderer(
            BlockEntityRendererProvider.Context context
    ) {
        this.blockModelResolver = context.blockModelResolver();
    }

    @Override
    public PlantResearchTableRenderState createRenderState() {
        return new PlantResearchTableRenderState();
    }

    @Override
    public void extractRenderState(
            PlantResearchTableBlockEntity blockEntity,
            PlantResearchTableRenderState state,
            float partialTicks,
            Vec3 cameraPosition,
            ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress
    ) {
        BlockEntityRenderer.super.extractRenderState(
                blockEntity,
                state,
                partialTicks,
                cameraPosition,
                breakProgress
        );

        ItemStack sample = blockEntity.getItem(0);

        if (sample.getItem() instanceof BlockItem blockItem) {
            this.blockModelResolver.update(
                    state.sampleModel,
                    blockItem.getBlock().defaultBlockState(),
                    DISPLAY_CONTEXT
            );

            state.hasSample = !state.sampleModel.isEmpty();
        } else {
            state.sampleModel.clear();
            state.hasSample = false;
        }
    }

    @Override
    public void submit(
            PlantResearchTableRenderState state,
            PoseStack poseStack,
            SubmitNodeCollector submitNodeCollector,
            CameraRenderState camera
    ) {
        if (!state.hasSample) {
            return;
        }

        poseStack.pushPose();

        // 陶罐口正中央，y = 6 / 16
        poseStack.translate(
                0.5F,
                6.0F / 16.0F,
                0.5F
        );

        // 等比例缩小
        poseStack.scale(
                DISPLAY_SCALE,
                DISPLAY_SCALE,
                DISPLAY_SCALE
        );

        // Block Model 原本占据 0~1，
        // 把它的 X/Z 中心移到原点
        poseStack.translate(
                -0.5F,
                0.0F,
                -0.5F
        );

        state.sampleModel.submit(
                poseStack,
                submitNodeCollector,
                state.lightCoords,
                OverlayTexture.NO_OVERLAY,
                0
        );

        poseStack.popPose();
    }
}
