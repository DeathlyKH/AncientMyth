package com.gyc.ancientmyth.menu;

import com.gyc.ancientmyth.block.entity.PlantResearchTableBlockEntity;
import com.gyc.ancientmyth.registry.ModMenus;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class PlantResearchTableMenu extends AbstractContainerMenu {

    private static final int CONTAINER_SIZE = 1;

    private static final int CONTAINER_START = 0;
    private static final int CONTAINER_END = 1;

    private static final int INVENTORY_START = CONTAINER_END;
    private static final int INVENTORY_END =
            INVENTORY_START + Inventory.INVENTORY_SIZE;

    private final Container container;

    private static final int RESEARCH_SLOT = 0;

    private static final int PLAYER_INVENTORY_START = 1;
    private static final int PLAYER_INVENTORY_END = 28;

    private static final int HOTBAR_START = 28;
    private static final int HOTBAR_END = 37;

    // 客户端构造
    public PlantResearchTableMenu(
            int containerId,
            Inventory inventory
    ) {
        this(
                containerId,
                inventory,
                createClientContainer()
        );
    }

    private static Container createClientContainer() {
        return new SimpleContainer(CONTAINER_SIZE) {

            @Override
            public int getMaxStackSize() {
                return 1;
            }

            @Override
            public boolean canPlaceItem(int slot, ItemStack stack) {
                return slot == 0
                        && PlantResearchTableBlockEntity.isValidSample(stack);
            }
        };
    }

    // 服务端构造
    public PlantResearchTableMenu(
            int containerId,
            Inventory inventory,
            Container container
    ) {
        super(ModMenus.PLANT_RESEARCH_TABLE, containerId);

        checkContainerSize(container, CONTAINER_SIZE);

        this.container = container;

        container.startOpen(inventory.player);

        // 研究样本槽
        this.addSlot(new Slot(container, 0, 80, 20) {

            @Override
            public boolean mayPlace(ItemStack stack) {
                return container.canPlaceItem(0, stack);
            }
        });

        // 玩家背包
        this.addStandardInventorySlots(
                inventory,
                8,
                84
        );
    }

    @Override
    public ItemStack quickMoveStack(
            Player player,
            int slotIndex
    ) {
        if (slotIndex < 0 || slotIndex >= this.slots.size()) {
            return ItemStack.EMPTY;
        }

        Slot slot = this.slots.get(slotIndex);

        if (!slot.hasItem()) {
            return ItemStack.EMPTY;
        }

        ItemStack stack = slot.getItem();
        ItemStack original = stack.copy();

        if (slotIndex == RESEARCH_SLOT) {

            // 研究台 → 玩家
            if (!this.moveItemStackTo(
                    stack,
                    PLAYER_INVENTORY_START,
                    HOTBAR_END,
                    false
            )) {
                return ItemStack.EMPTY;
            }

        } else {

            // 合法研究样本：
            // Shift-click 只允许尝试进入研究槽
            if (PlantResearchTableBlockEntity.isValidSample(stack)) {

                if (!this.moveItemStackTo(
                        stack,
                        RESEARCH_SLOT,
                        RESEARCH_SLOT + 1,
                        false
                )) {
                    return ItemStack.EMPTY;
                }

            } else {

                // 普通物品才执行 主背包 ↔ Hotbar
                if (slotIndex >= PLAYER_INVENTORY_START
                        && slotIndex < PLAYER_INVENTORY_END) {

                    if (!this.moveItemStackTo(
                            stack,
                            HOTBAR_START,
                            HOTBAR_END,
                            false
                    )) {
                        return ItemStack.EMPTY;
                    }

                } else if (slotIndex >= HOTBAR_START
                        && slotIndex < HOTBAR_END) {

                    if (!this.moveItemStackTo(
                            stack,
                            PLAYER_INVENTORY_START,
                            PLAYER_INVENTORY_END,
                            false
                    )) {
                        return ItemStack.EMPTY;
                    }
                }
            }
        }

        if (stack.isEmpty()) {
            slot.setByPlayer(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }

        if (stack.getCount() == original.getCount()) {
            return ItemStack.EMPTY;
        }

        slot.onTake(player, stack);

        return original;
    }

    @Override
    public boolean stillValid(Player player) {
        return this.container.stillValid(player);
    }

    @Override
    public void removed(Player player) {
        super.removed(player);

        this.container.stopOpen(player);
    }
}
