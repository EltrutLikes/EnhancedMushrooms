package com.teamaurora.enhanced_mushrooms.core.data.server.tags;

import com.teamabnormals.blueprint.core.data.server.tags.BlueprintItemTagsProvider;
import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import com.teamaurora.enhanced_mushrooms.core.EnhancedMushrooms;
import com.teamaurora.enhanced_mushrooms.core.other.tags.EMItemTags;
import com.teamaurora.enhanced_mushrooms.core.registry.EMItems;
import com.teamaurora.enhanced_mushrooms.integration.farmers_delight.EMFDCompat;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

import static com.teamaurora.enhanced_mushrooms.core.registry.EMBlocks.*;

public class EMItemTagsProvider extends BlueprintItemTagsProvider {

    public EMItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> tagLookup, ExistingFileHelper fileHelper) {
        super(EnhancedMushrooms.MOD_ID, output, lookupProvider, tagLookup, fileHelper);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        this.copy(BlockTags.PLANKS, ItemTags.PLANKS);
        this.tag(ItemTags.LOGS_THAT_BURN).addTag(EMItemTags.MUSHROOM_STEMS);
        this.copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
        this.copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
        this.copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
        this.copy(BlockTags.FENCE_GATES, ItemTags.FENCE_GATES);
        this.copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
        this.copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);
        this.copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
        this.copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
        this.copy(BlockTags.STANDING_SIGNS, ItemTags.SIGNS);
        this.copy(BlockTags.CEILING_HANGING_SIGNS, ItemTags.HANGING_SIGNS);
        this.copyWoodworksTags();

        if (ItemSubRegistryHelper.areModsLoaded("farmersdelight"))
            this.tag(EMFDCompat.cabinetTagSupplier.get()).add(MUSHROOM_CABINET.get().asItem());

        this.tag(ItemTags.BOATS).add(EMItems.MUSHROOM_BOAT.getFirst().get());
        this.tag(ItemTags.CHEST_BOATS).add(EMItems.MUSHROOM_BOAT.getSecond().get());
        this.tag(BlueprintItemTags.FURNACE_BOATS).add(EMItems.MUSHROOM_FURNACE_BOAT.get());
        this.tag(BlueprintItemTags.LARGE_BOATS).add(EMItems.LARGE_MUSHROOM_BOAT.get());

        this.tag(EMItemTags.MUSHROOM_STEMS).add(EMItems.MUSHROOM_STEM.get(), STRIPPED_MUSHROOM_STEM.get().asItem(), MUSHROOM_HYPHAE.get().asItem(), STRIPPED_MUSHROOM_HYPHAE.get().asItem());
    }
}
