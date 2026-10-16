package com.gyc.ancientmyth.block;

import com.gyc.ancientmyth.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;


public class CompleteTorchflowerCropBlock extends CropBlock {


    public CompleteTorchflowerCropBlock(
            BlockBehaviour.Properties properties
    ) {
        super(properties);
    }


    @Override
    protected ItemLike getBaseSeedId() {
        return Items.TORCHFLOWER_SEEDS;
    }


    @Override
    public int getMaxAge() {
        return 4;
    }


    @Override
    public BlockState getStateForAge(int age) {
        return age >= getMaxAge()
                ? ModBlocks.COMPLETE_TORCHFLOWER.defaultBlockState()
                : super.getStateForAge(age);
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
    protected int getBonemealAgeIncrease(Level level) {
        return 1;
    }

}