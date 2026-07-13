package com.teamaurora.enhanced_mushrooms.core.data.client;

import com.teamabnormals.blueprint.core.data.client.BlueprintBlockStateProvider;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.teamaurora.enhanced_mushrooms.core.EnhancedMushrooms;
import com.teamaurora.enhanced_mushrooms.core.other.EMBlockFamilies;
import com.teamaurora.enhanced_mushrooms.integration.farmers_delight.EMFDCompat;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import static com.teamaurora.enhanced_mushrooms.core.registry.EMBlocks.*;

public class EMBlockStateProvider extends BlueprintBlockStateProvider {

    public EMBlockStateProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, EnhancedMushrooms.MOD_ID, helper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.blockFamily(EMBlockFamilies.MUSHROOM_PLANKS_FAMILY);

        this.logBlocks(MUSHROOM_STEM, MUSHROOM_HYPHAE);
        this.logBlocks(STRIPPED_MUSHROOM_STEM, STRIPPED_MUSHROOM_HYPHAE);
        this.hangingSignBlocks(STRIPPED_MUSHROOM_STEM, MUSHROOM_HANGING_SIGNS);

        this.woodworksBlocks(MUSHROOM_PLANKS, MUSHROOM_BOARDS, MUSHROOM_LADDER, MUSHROOM_BOOKSHELF, MUSHROOM_BEEHIVE, MUSHROOM_CHEST, TRAPPED_MUSHROOM_CHEST);
        this.chiseledBookshelfBlock(CHISELED_MUSHROOM_BOOKSHELF, DEFAULT_BOOKSHELF_POSITIONS);

        this.cabinet(MUSHROOM_CABINET.get());
    }

    private void cabinet(Block cabinet) {
        ResourceLocation registryName = BuiltInRegistries.BLOCK.getKey(cabinet);
        ResourceLocation name = prefix("block/", registryName);

        ModelFile cabinetModel = models().orientable(name(cabinet), suffix(name, "_side"), suffix(name, "_front"), suffix(name, "_top"));
        ModelFile cabinetOpenModel = models().orientable(name(cabinet) + "_open", suffix(name, "_side"), suffix(name, "_front_open"), suffix(name, "_top"));

        if (BlockSubRegistryHelper.areModsLoaded("farmersdelight")) {
            this.cabinetBlock(cabinet, cabinetModel, cabinetOpenModel);
            this.item(cabinet);
        }
    }

    public void cabinetBlock(Block block, ModelFile cabinet, ModelFile cabinetOpen) {
        this.getVariantBuilder(block)
                .forAllStates(state -> ConfiguredModel.builder()
                        .modelFile(state.getValue(EMFDCompat.cabinetOpenSupplier.get()) ? cabinetOpen : cabinet)
                        .rotationY(((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360)
                        .build()
                );
    }

    public void item(Block block) {
        this.simpleBlockItem(block, new ModelFile.ExistingModelFile(blockTexture(block), this.models().existingFileHelper));
    }
}
