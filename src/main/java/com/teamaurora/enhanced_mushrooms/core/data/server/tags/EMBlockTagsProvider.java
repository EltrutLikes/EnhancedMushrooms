package com.teamaurora.enhanced_mushrooms.core.data.server.tags;

import com.teamabnormals.blueprint.core.other.tags.BlueprintBlockTags;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import com.teamaurora.enhanced_mushrooms.core.EnhancedMushrooms;
import com.teamaurora.enhanced_mushrooms.core.other.tags.EMBlockTags;
import com.teamaurora.enhanced_mushrooms.integration.farmers_delight.EMFDCompat;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

import static com.teamaurora.enhanced_mushrooms.core.registry.EMBlocks.*;

public class EMBlockTagsProvider extends BlockTagsProvider {

    public EMBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper) {
        super(output, provider, EnhancedMushrooms.MOD_ID, helper);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.PLANKS).add(MUSHROOM_PLANKS.get());
        this.tag(BlockTags.LOGS_THAT_BURN).addTag(EMBlockTags.MUSHROOM_STEMS);
        this.tag(BlockTags.WOODEN_SLABS).add(MUSHROOM_SLAB.get());
        this.tag(BlockTags.WOODEN_STAIRS).add(MUSHROOM_STAIRS.get());
        this.tag(BlockTags.WOODEN_FENCES).add(MUSHROOM_FENCE.get());
        this.tag(BlockTags.FENCE_GATES).add(MUSHROOM_FENCE_GATE.get());
        this.tag(Tags.Blocks.FENCE_GATES_WOODEN).add(MUSHROOM_FENCE_GATE.get());
        this.tag(BlockTags.WOODEN_DOORS).add(MUSHROOM_DOOR.get());
        this.tag(BlockTags.WOODEN_TRAPDOORS).add(MUSHROOM_TRAPDOOR.get());
        this.tag(BlockTags.WOODEN_BUTTONS).add(MUSHROOM_BUTTON.get());
        this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(MUSHROOM_PRESSURE_PLATE.get());
        this.tag(BlockTags.STANDING_SIGNS).add(MUSHROOM_SIGNS.getFirst().get());
        this.tag(BlockTags.WALL_SIGNS).add(MUSHROOM_SIGNS.getSecond().get());
        this.tag(BlockTags.CEILING_HANGING_SIGNS).add(MUSHROOM_HANGING_SIGNS.getFirst().get());
        this.tag(BlockTags.WALL_HANGING_SIGNS).add(MUSHROOM_HANGING_SIGNS.getSecond().get());
        this.tag(BlockTags.OVERWORLD_NATURAL_LOGS).add(MUSHROOM_STEM.get());

        this.tag(EMBlockTags.MUSHROOM_STEMS).add(MUSHROOM_STEM.get(), STRIPPED_MUSHROOM_STEM.get(), MUSHROOM_HYPHAE.get(), STRIPPED_MUSHROOM_HYPHAE.get());

        if (ItemSubRegistryHelper.areModsLoaded("farmersdelight"))
            this.tag(EMFDCompat.cabinetBlockTagSupplier.get()).add(MUSHROOM_CABINET.get());

        this.tag(BlueprintBlockTags.WOODEN_BOARDS).add(MUSHROOM_BOARDS.get());
        this.tag(BlueprintBlockTags.WOODEN_CHESTS).add(MUSHROOM_CHEST.get());
        this.tag(BlueprintBlockTags.WOODEN_TRAPPED_CHESTS).add(TRAPPED_MUSHROOM_CHEST.get());
        this.tag(BlueprintBlockTags.WOODEN_BEEHIVES).add(MUSHROOM_BEEHIVE.get());
        this.tag(BlueprintBlockTags.WOODEN_LADDERS).add(MUSHROOM_LADDER.get());
        this.tag(BlueprintBlockTags.WOODEN_BOOKSHELVES).add(MUSHROOM_BOOKSHELF.get());
        this.tag(BlueprintBlockTags.WOODEN_CHISELED_BOOKSHELVES).add(CHISELED_MUSHROOM_BOOKSHELF.get());

        this.tag(BlockTags.MINEABLE_WITH_AXE).add(MUSHROOM_CABINET.get());
    }
}
