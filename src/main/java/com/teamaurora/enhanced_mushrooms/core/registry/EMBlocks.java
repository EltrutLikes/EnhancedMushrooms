package com.teamaurora.enhanced_mushrooms.core.registry;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.blueprint.common.block.BlueprintBeehiveBlock;
import com.teamabnormals.blueprint.common.block.LogBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintChestBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintTrappedChestBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintCeilingHangingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintStandingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallHangingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallSignBlock;
import com.teamabnormals.blueprint.core.api.BlockSetTypeRegistryHelper;
import com.teamabnormals.blueprint.core.api.WoodTypeRegistryHelper;
import com.teamabnormals.blueprint.core.events.LoadThisClassEvent;
import com.teamabnormals.blueprint.core.util.PropertyUtil;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import com.teamaurora.enhanced_mushrooms.common.block.MushroomStemBlock;
import com.teamaurora.enhanced_mushrooms.common.block.MushroomStemReplacerBlock;
import com.teamaurora.enhanced_mushrooms.core.EnhancedMushrooms;
import com.teamaurora.enhanced_mushrooms.core.other.EMConstants;
import com.teamaurora.enhanced_mushrooms.integration.farmers_delight.EMFDCompat;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Predicate;

import static net.minecraft.world.item.CreativeModeTabs.*;
import static net.minecraft.world.item.crafting.Ingredient.of;

@EventBusSubscriber(modid = EnhancedMushrooms.MOD_ID)
public class EMBlocks {
    @SubscribeEvent
    public static void load(LoadThisClassEvent event) {} // loads the class
    
    public static final BlockSubRegistryHelper HELPER = EnhancedMushrooms.REGISTRY_HELPER.getBlockSubHelper();
    public static final BlockSubRegistryHelper VANILLA_HELPER = EnhancedMushrooms.VANILLA_HELPER.getBlockSubHelper();

    // this line causes a null-pointer exception
//    public static final DeferredBlock<Block> MUSHROOM_STEM_REPLACER = VANILLA_HELPER.createBlockNoItem("mushroom_stem", ()->new MushroomStemReplacerBlock(Block.Properties.ofFullCopy(Blocks.MUSHROOM_STEM)));

    public static final DeferredBlock<Block> STRIPPED_MUSHROOM_STEM = HELPER.createBlock("stripped_mushroom_stem", ()->new RotatedPillarBlock(EMProperties.MUSHROOM.log()));
    public static final DeferredBlock<Block> STRIPPED_MUSHROOM_HYPHAE = HELPER.createBlock("stripped_mushroom_hyphae", ()->new RotatedPillarBlock(EMProperties.MUSHROOM.log()));
    public static final DeferredBlock<Block> MUSHROOM_STEM = HELPER.createBlock("mushroom_stem", ()->new MushroomStemBlock(STRIPPED_MUSHROOM_STEM, EMProperties.MUSHROOM.log()));
    public static final DeferredBlock<Block> MUSHROOM_HYPHAE = HELPER.createBlock("mushroom_hyphae", ()->new LogBlock(STRIPPED_MUSHROOM_HYPHAE, EMProperties.MUSHROOM.log()));
    public static final DeferredBlock<Block> MUSHROOM_PLANKS = HELPER.createBlock("mushroom_planks", ()->new Block(EMProperties.MUSHROOM.planks()));
    public static final DeferredBlock<Block> MUSHROOM_STAIRS = HELPER.createBlock("mushroom_stairs", ()->new StairBlock(MUSHROOM_PLANKS.get().defaultBlockState(), EMProperties.MUSHROOM.planks()));
    public static final DeferredBlock<Block> MUSHROOM_SLAB = HELPER.createBlock("mushroom_slab", ()->new SlabBlock(EMProperties.MUSHROOM.planks()));
    public static final DeferredBlock<Block> MUSHROOM_PRESSURE_PLATE = HELPER.createBlock("mushroom_pressure_plate", ()->new PressurePlateBlock(EMProperties.MUSHROOM_BLOCK_SET, EMProperties.MUSHROOM.pressurePlate()));
    public static final DeferredBlock<Block> MUSHROOM_BUTTON = HELPER.createBlock("mushroom_button", ()->new ButtonBlock(EMProperties.MUSHROOM_BLOCK_SET, 30, EMProperties.MUSHROOM.button()));
    // TODO: register fences as fuel
    public static final DeferredBlock<Block> MUSHROOM_FENCE = HELPER.createBlock("mushroom_fence", ()->new FenceBlock(EMProperties.MUSHROOM.planks()));
    public static final DeferredBlock<Block> MUSHROOM_FENCE_GATE = HELPER.createBlock("mushroom_fence_gate", ()->new FenceGateBlock(EMProperties.MUSHROOM_WOOD_TYPE, EMProperties.MUSHROOM.planks()));
    public static final DeferredBlock<Block> MUSHROOM_DOOR = HELPER.createBlock("mushroom_door", ()->new DoorBlock(EMProperties.MUSHROOM_BLOCK_SET, EMProperties.MUSHROOM.door()));
    public static final DeferredBlock<Block> MUSHROOM_TRAPDOOR = HELPER.createBlock("mushroom_trapdoor", ()->new TrapDoorBlock(EMProperties.MUSHROOM_BLOCK_SET, EMProperties.MUSHROOM.trapdoor()));
    public static final Pair<DeferredBlock<BlueprintStandingSignBlock>, DeferredBlock<BlueprintWallSignBlock>> MUSHROOM_SIGNS = HELPER.createSignBlock("mushroom", EMProperties.MUSHROOM_WOOD_TYPE, EMProperties.MUSHROOM.sign());
    public static final Pair<DeferredBlock<BlueprintCeilingHangingSignBlock>, DeferredBlock<BlueprintWallHangingSignBlock>> MUSHROOM_HANGING_SIGNS = HELPER.createHangingSignBlock("mushroom", EMProperties.MUSHROOM_WOOD_TYPE, EMProperties.MUSHROOM.hangingSign());

    // TODO: register boards, bookshelves and ladder as fuel
    public static final DeferredBlock<Block> MUSHROOM_BOARDS = HELPER.createBlock("mushroom_boards", () -> new RotatedPillarBlock(EMProperties.MUSHROOM.planks()));
    public static final DeferredBlock<Block> MUSHROOM_BOOKSHELF = HELPER.createBlock("mushroom_bookshelf", ()->new Block(EMProperties.MUSHROOM.bookshelf()));
    public static final DeferredBlock<Block> CHISELED_MUSHROOM_BOOKSHELF = HELPER.createBlock("chiseled_mushroom_bookshelf", ()->new ChiseledBookShelfBlock(EMProperties.MUSHROOM.chiseledBookshelf()));
    public static final DeferredBlock<Block> MUSHROOM_LADDER = HELPER.createBlock("mushroom_ladder", ()->new LadderBlock(EMProperties.MUSHROOM.ladder()));
    public static final DeferredBlock<Block> MUSHROOM_BEEHIVE = HELPER.createBlock("mushroom_beehive", ()->new BlueprintBeehiveBlock(EMProperties.MUSHROOM.beehive()));
    public static final DeferredBlock<BlueprintChestBlock> MUSHROOM_CHEST = HELPER.createChestBlock("mushroom", EMProperties.MUSHROOM.chest());
    public static final DeferredBlock<BlueprintTrappedChestBlock> TRAPPED_MUSHROOM_CHEST = HELPER.createTrappedChestBlock("mushroom", EMProperties.MUSHROOM.chest());
    // TODO: register cabinet as fuel
    public static final DeferredBlock<Block> MUSHROOM_CABINET = HELPER.createBlock("mushroom_cabinet", ItemSubRegistryHelper.areModsLoaded("farmersdelight") ? EMFDCompat.CABINET_SUPPLIER : () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL)));

    public static void setupTabEditors() {
        CreativeModeTabContentsPopulator.mod(EnhancedMushrooms.MOD_ID)
                .tab(BUILDING_BLOCKS)
                .addItemsBefore(of(Blocks.BAMBOO_BLOCK), MUSHROOM_STEM, MUSHROOM_HYPHAE, STRIPPED_MUSHROOM_STEM, STRIPPED_MUSHROOM_HYPHAE, MUSHROOM_PLANKS)
                .addItemsBefore(modLoaded(Blocks.BAMBOO_BLOCK, "woodworks"), MUSHROOM_BOARDS)
                .addItemsBefore(of(Blocks.BAMBOO_BLOCK), MUSHROOM_STAIRS, MUSHROOM_SLAB, MUSHROOM_FENCE, MUSHROOM_FENCE_GATE, MUSHROOM_DOOR, MUSHROOM_TRAPDOOR, MUSHROOM_PRESSURE_PLATE, MUSHROOM_BUTTON)
                .tab(NATURAL_BLOCKS)
                .addItemsBefore(of(Blocks.CRIMSON_STEM), MUSHROOM_STEM)
                .tab(FUNCTIONAL_BLOCKS)
                .addItemsBefore(of(Blocks.BAMBOO_SIGN), MUSHROOM_SIGNS.getFirst(), MUSHROOM_HANGING_SIGNS.getFirst());

        if (ItemSubRegistryHelper.areModsLoaded("farmersdelight")) {
            CreativeModeTabContentsPopulator.mod(EnhancedMushrooms.MOD_ID)
                    .predicate(EMFDCompat::fdGroupPredicate)
                    .addItemsBefore(ofID(EMConstants.BAMBOO_CABINET), MUSHROOM_CABINET);
        }

        CreativeModeTabContentsPopulator.mod("woodworks_1")
                .tab(FUNCTIONAL_BLOCKS)
                .addItemsBefore(ofID(EMConstants.BAMBOO_LADDER), MUSHROOM_LADDER)
                .addItemsBefore(ofID(EMConstants.BAMBOO_BEEHIVE), MUSHROOM_BEEHIVE)
                .addItemsBefore(ofID(EMConstants.BAMBOO_BOOKSHELF), MUSHROOM_BOOKSHELF, CHISELED_MUSHROOM_BOOKSHELF)
                .addItemsBefore(ofID(EMConstants.BAMBOO_CLOSET), MUSHROOM_CHEST)
                .tab(REDSTONE_BLOCKS)
                .addItemsBefore(ofID(EMConstants.TRAPPED_BAMBOO_CLOSET), TRAPPED_MUSHROOM_CHEST);
    }

    public static Predicate<ItemStack> modLoaded(ItemLike item, String... modids) {
        return stack -> of(item).test(stack) && BlockSubRegistryHelper.areModsLoaded(modids);
    }

    public static Predicate<ItemStack> ofID(ResourceLocation location, ItemLike fallback, String... modids) {
        return stack -> (BlockSubRegistryHelper.areModsLoaded(modids) ? of(BuiltInRegistries.ITEM.get(location)) : of(fallback)).test(stack);
    }

    public static Predicate<ItemStack> ofID(ResourceLocation location, String... modids) {
        return stack -> (BlockSubRegistryHelper.areModsLoaded(modids) && of(BuiltInRegistries.ITEM.get(location)).test(stack));
    }

    public static class EMProperties {
        public static final BlockSetType MUSHROOM_BLOCK_SET = BlockSetTypeRegistryHelper.register(new BlockSetType(EnhancedMushrooms.MOD_ID + ":mushroom"));
        public static final WoodType MUSHROOM_WOOD_TYPE = WoodTypeRegistryHelper.registerWoodType(new WoodType(EnhancedMushrooms.MOD_ID + ":mushroom", MUSHROOM_BLOCK_SET));
        public static final PropertyUtil.WoodSetProperties MUSHROOM = PropertyUtil.WoodSetProperties.builder(MapColor.TERRACOTTA_WHITE).build();
    }
}
