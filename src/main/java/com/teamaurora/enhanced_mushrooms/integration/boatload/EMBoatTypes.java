package com.teamaurora.enhanced_mushrooms.integration.boatload;

import com.teamabnormals.boatload.common.item.FurnaceBoatItem;
import com.teamabnormals.boatload.common.item.LargeBoatItem;
import com.teamabnormals.boatload.core.api.BoatloadBoatType;
import com.teamaurora.enhanced_mushrooms.core.EnhancedMushrooms;
import com.teamaurora.enhanced_mushrooms.core.registry.EMBlocks;
import com.teamaurora.enhanced_mushrooms.core.registry.EMItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class EMBoatTypes {
    public static final BoatloadBoatType MUSHROOM = BoatloadBoatType.register(BoatloadBoatType.create(ResourceLocation.fromNamespaceAndPath(EnhancedMushrooms.MOD_ID, "mushroom"), () -> EMBlocks.MUSHROOM_PLANKS.get().asItem(), () -> EMItems.MUSHROOM_BOAT.getFirst().get(), () -> EMItems.MUSHROOM_BOAT.getSecond().get(), () -> EMItems.MUSHROOM_FURNACE_BOAT.get(), () -> EMItems.LARGE_MUSHROOM_BOAT.get()));

    public static final Supplier<Item> MUSHROOM_FURNACE_BOAT = () -> new FurnaceBoatItem(MUSHROOM);
    public static final Supplier<Item> LARGE_MUSHROOM_BOAT = () -> new LargeBoatItem(MUSHROOM);
}
