package com.teamaurora.enhanced_mushrooms.core.data.client;

import com.teamabnormals.blueprint.core.data.client.BlueprintItemModelProvider;
import com.teamaurora.enhanced_mushrooms.core.EnhancedMushrooms;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import static com.teamaurora.enhanced_mushrooms.core.registry.EMItems.*;

public class EMItemModelProvider extends BlueprintItemModelProvider {

    public EMItemModelProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, EnhancedMushrooms.MOD_ID, helper);
    }

    @Override
    protected void registerModels() {
        this.generatedItem(MUSHROOM_BOAT.getFirst(), MUSHROOM_BOAT.getSecond(), MUSHROOM_FURNACE_BOAT, LARGE_MUSHROOM_BOAT);
    }
}
