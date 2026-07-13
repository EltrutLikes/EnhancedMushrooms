package com.teamaurora.enhanced_mushrooms.core.data.server;

import com.teamabnormals.boatload.core.data.server.BoatloadRecipeProvider;
import com.teamabnormals.woodworks.core.data.server.WoodworksRecipeProvider;
import com.teamaurora.enhanced_mushrooms.core.EnhancedMushrooms;
import com.teamaurora.enhanced_mushrooms.core.other.EMBlockFamilies;
import com.teamaurora.enhanced_mushrooms.core.other.tags.EMItemTags;
import com.teamaurora.enhanced_mushrooms.integration.boatload.EMBoatTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.flag.FeatureFlags;

import java.util.concurrent.CompletableFuture;

import static com.teamaurora.enhanced_mushrooms.core.registry.EMBlocks.*;

public class EMRecipeProvider extends RecipeProvider {
    public EMRecipeProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(packOutput, completableFuture);
    }

    @Override
    protected void buildRecipes(RecipeOutput consumer) {
        generateRecipes(consumer, EMBlockFamilies.MUSHROOM_PLANKS_FAMILY, FeatureFlags.REGISTRY.allFlags());
        planksFromLogs(consumer, MUSHROOM_PLANKS.get(), EMItemTags.MUSHROOM_STEMS, 4);
        woodFromLogs(consumer, MUSHROOM_HYPHAE.get(), MUSHROOM_STEM.get());
        woodFromLogs(consumer, STRIPPED_MUSHROOM_HYPHAE.get(), STRIPPED_MUSHROOM_STEM.get());
        hangingSign(consumer, MUSHROOM_HANGING_SIGNS.getFirst().get(), STRIPPED_MUSHROOM_STEM.get());

        BoatloadRecipeProvider.boatRecipes(consumer, EMBoatTypes.MUSHROOM);
        WoodworksRecipeProvider.baseRecipes(consumer, MUSHROOM_PLANKS.get(), MUSHROOM_SLAB.get(), MUSHROOM_BOARDS.get(), MUSHROOM_BOOKSHELF.get(), CHISELED_MUSHROOM_BOOKSHELF.get(), MUSHROOM_LADDER.get(), MUSHROOM_BEEHIVE.get(), MUSHROOM_CHEST.get(), TRAPPED_MUSHROOM_CHEST.get(), EnhancedMushrooms.MOD_ID);
        WoodworksRecipeProvider.sawmillRecipes(consumer, EMBlockFamilies.MUSHROOM_PLANKS_FAMILY, EMItemTags.MUSHROOM_STEMS, MUSHROOM_BOARDS.get(), MUSHROOM_LADDER.get(), EnhancedMushrooms.MOD_ID);
    }
}
