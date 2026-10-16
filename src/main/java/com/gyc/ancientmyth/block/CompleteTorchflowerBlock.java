
package com.gyc.ancientmyth.block;

import com.gyc.ancientmyth.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CompleteTorchflowerBlock extends VegetationBlock {

    private static final VoxelShape SHAPE =
            Block.box(3, 0, 3, 13, 15, 13);

    public CompleteTorchflowerBlock(
            BlockBehaviour.Properties properties
    ) {
        super(properties);
    }

    @Override
    protected boolean mayPlaceOn(
            BlockState state,
            BlockGetter level,
            BlockPos pos
    ) {
        return state.is(ModBlocks.ANCIENT_DIRT)
                || state.is(ModBlocks.ANCIENT_FARMLAND);
    }

    @Override
    protected VoxelShape getShape(
            BlockState state,
            BlockGetter level,
            BlockPos pos,
            CollisionContext context
    ) {
        return SHAPE;
    }
}
