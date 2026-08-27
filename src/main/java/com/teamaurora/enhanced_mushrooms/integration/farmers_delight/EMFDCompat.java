package com.teamaurora.enhanced_mushrooms.integration.farmers_delight;

import com.teamaurora.enhanced_mushrooms.core.registry.EMBlocks;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;
import vectorwing.farmersdelight.common.registry.ModCreativeTabs;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.function.Supplier;

public final class EMFDCompat {
    public static final Supplier<Block> CABINET_SUPPLIER = () -> new CabinetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL));

    public static boolean fdGroupPredicate(BuildCreativeModeTabContentsEvent event) {
        return event.getTab() == ModCreativeTabs.TAB_FARMERS_DELIGHT.get();
    }

    public static Supplier<Item> fdCabinetSupplier = ModItems.BAMBOO_CABINET;
    public static Supplier<BooleanProperty> cabinetOpenSupplier = () -> CabinetBlock.OPEN;
    public static Supplier<TagKey<Block>> cabinetBlockTagSupplier = () -> ModTags.Blocks.CABINETS_WOODEN;
    public static Supplier<TagKey<Item>> cabinetItemTagSupplier = () -> ModTags.Items.CABINETS_WOODEN;

    public static void addToCabinetBlockEntity(BlockEntityTypeAddBlocksEvent event) {
        event.modify(ModBlockEntityTypes.CABINET.get(), EMBlocks.MUSHROOM_CABINET.get());
    }
}
