package com.gyc.ancientmyth.block;

import com.gyc.ancientmyth.block.entity.PlantResearchTableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlantResearchTableBlock extends BaseEntityBlock {

    public PlantResearchTableBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    private static final VoxelShape SHAPE = Shapes.or(
            Block.box(2, 0, 2, 14, 4, 14),
            Block.box(4, 4, 4, 12, 5, 12),
            Block.box(3, 5, 3, 13, 6, 13),
            Block.box(1, 6, 1, 15, 16, 15)
    );

    @Override
    protected VoxelShape getShape(
            BlockState state,
            BlockGetter level,
            BlockPos pos,
            CollisionContext context
    ) {
        return SHAPE;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PlantResearchTableBlockEntity(pos, state);
    }

    @Override
    protected InteractionResult useWithoutItem(
            BlockState state,
            Level level,
            BlockPos pos,
            Player player,
            BlockHitResult hit
    ) {
        // Shift + 右键：完全绕过研究台交互
        if (player.isSecondaryUseActive()) {
            return InteractionResult.PASS;
        }

        if (!level.isClientSide()
                && level.getBlockEntity(pos)
                instanceof PlantResearchTableBlockEntity table) {

            player.openMenu(table);
        }

        return InteractionResult.SUCCESS;
    }

}
