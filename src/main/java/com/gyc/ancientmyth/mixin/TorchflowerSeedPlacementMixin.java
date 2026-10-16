package com.gyc.ancientmyth.mixin;

import com.gyc.ancientmyth.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(BlockItem.class)
public abstract class TorchflowerSeedPlacementMixin {


    @Inject(
            method = "getPlacementState",
            at = @At("HEAD"),
            cancellable = true
    )
    private void ancientmyth$replaceTorchflowerCrop(
            BlockPlaceContext context,
            CallbackInfoReturnable<BlockState> cir
    ) {

        BlockItem item = (BlockItem)(Object)this;

        if (item.getBlock() != Blocks.TORCHFLOWER_CROP) {
            return;
        }


        BlockPos farmlandPos =
                context.getClickedPos().below();


        BlockState farmland =
                context.getLevel()
                        .getBlockState(farmlandPos);


        if (farmland.is(ModBlocks.ANCIENT_FARMLAND)) {

            cir.setReturnValue(
                    ModBlocks.COMPLETE_TORCHFLOWER_CROP
                            .defaultBlockState()
            );
        }
    }
}