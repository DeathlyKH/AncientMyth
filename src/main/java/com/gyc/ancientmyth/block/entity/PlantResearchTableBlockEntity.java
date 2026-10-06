package com.gyc.ancientmyth.block.entity;

import com.gyc.ancientmyth.registry.ModBlockEntities;
import com.gyc.ancientmyth.registry.ModItemTags;
import com.gyc.ancientmyth.util.ImplementedContainer;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import com.gyc.ancientmyth.menu.PlantResearchTableMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;

public class PlantResearchTableBlockEntity
        extends BlockEntity
        implements ImplementedContainer, MenuProvider {

    private final NonNullList<ItemStack> items =
            NonNullList.withSize(1, ItemStack.EMPTY);

    public PlantResearchTableBlockEntity(
            BlockPos pos,
            BlockState state
    ) {
        super(ModBlockEntities.PLANT_RESEARCH_TABLE, pos, state);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registryLookup) {
        return saveWithoutMetadata(registryLookup);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void setChanged() {
        super.setChanged();

        if (level == null || level.isClientSide()) {
            return;
        }

        BlockState state = getBlockState();

        level.sendBlockUpdated(
                worldPosition,
                state,
                state,
                Block.UPDATE_ALL
        );
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {
        return slot == 0 && isValidSample(stack);
    }

    @Override
    public boolean stillValid(net.minecraft.world.entity.player.Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);

        items.clear();
        ContainerHelper.loadAllItems(input, items);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        ContainerHelper.saveAllItems(output, items);

        super.saveAdditional(output);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable(
                "block.ancient-myth.plant_research_table"
        );
    }

    @Override
    public AbstractContainerMenu createMenu(
            int containerId,
            Inventory inventory,
            Player player
    ) {
        return new PlantResearchTableMenu(
                containerId,
                inventory,
                this
        );
    }

    public static boolean isValidSample(ItemStack stack) {
        return stack.getItem() instanceof BlockItem
                && stack.is(ModItemTags.RESEARCHABLE);
    }

}
