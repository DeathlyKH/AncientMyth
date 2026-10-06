package com.gyc.ancientmyth.client.render.state;

import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;

public class PlantResearchTableRenderState
        extends BlockEntityRenderState {

    public final BlockModelRenderState sampleModel =
            new BlockModelRenderState();

    public boolean hasSample;
}
