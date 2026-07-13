package com.teamaurora.enhanced_mushrooms.core.data.server;

import com.google.common.collect.ImmutableList;
import com.teamaurora.enhanced_mushrooms.core.EnhancedMushrooms;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.teamaurora.enhanced_mushrooms.core.registry.EMBlocks.*;

public class EMLootTableProvider extends LootTableProvider {

    public EMLootTableProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, Set.of(), ImmutableList.of(
                new LootTableProvider.SubProviderEntry(EMBlockLoot::new, LootContextParamSets.BLOCK)
        ), provider);
    }

    private static class EMBlockLoot extends BlockLootSubProvider {
        private static final Set<Item> EXPLOSION_RESISTANT = Stream.of(Blocks.DRAGON_EGG, Blocks.BEACON, Blocks.CONDUIT, Blocks.SKELETON_SKULL, Blocks.WITHER_SKELETON_SKULL, Blocks.PLAYER_HEAD, Blocks.ZOMBIE_HEAD, Blocks.CREEPER_HEAD, Blocks.DRAGON_HEAD, Blocks.PIGLIN_HEAD, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX).map(ItemLike::asItem).collect(Collectors.toSet());

        protected EMBlockLoot(HolderLookup.Provider provider) {
            super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags(), provider);
        }

        @Override
        public void generate() {
            this.dropSelf(MUSHROOM_PLANKS.get());
            this.dropSelf(MUSHROOM_STEM.get());
            this.dropSelf(MUSHROOM_HYPHAE.get());
            this.dropSelf(STRIPPED_MUSHROOM_STEM.get());
            this.dropSelf(STRIPPED_MUSHROOM_HYPHAE.get());
            this.dropSelf(MUSHROOM_SIGNS.getFirst().get());
            this.dropSelf(MUSHROOM_HANGING_SIGNS.getFirst().get());
            this.dropSelf(MUSHROOM_PRESSURE_PLATE.get());
            this.dropSelf(MUSHROOM_TRAPDOOR.get());
            this.dropSelf(MUSHROOM_BUTTON.get());
            this.dropSelf(MUSHROOM_STAIRS.get());
            this.dropSelf(MUSHROOM_FENCE.get());
            this.dropSelf(MUSHROOM_FENCE_GATE.get());
            this.dropSelf(MUSHROOM_BOARDS.get());

            this.dropSelf(MUSHROOM_LADDER.get());
            this.add(MUSHROOM_SLAB.get(), this::createSlabItemTable);
            this.add(MUSHROOM_DOOR.get(), this::createDoorTable);
            this.add(MUSHROOM_BEEHIVE.get(), this::createBeeHiveDrop);
            this.add(MUSHROOM_CHEST.get(), this::createNameableBlockEntityTable);
            this.add(TRAPPED_MUSHROOM_CHEST.get(), this::createNameableBlockEntityTable);
            this.add(MUSHROOM_BOOKSHELF.get(), (block) -> createSingleItemTableWithSilkTouch(block, Items.BOOK, ConstantValue.exactly(3.0f)));
            this.dropWhenSilkTouch(CHISELED_MUSHROOM_BOOKSHELF.get());
            this.add(MUSHROOM_CABINET.get(), this::createNameableBlockEntityTable);
        }

        @Override
        public Iterable<Block> getKnownBlocks() {
            return BuiltInRegistries.BLOCK.stream().filter(block -> BuiltInRegistries.BLOCK.getKey(block).getNamespace().equals(EnhancedMushrooms.MOD_ID)).collect(Collectors.toSet());
        }
    }
}
