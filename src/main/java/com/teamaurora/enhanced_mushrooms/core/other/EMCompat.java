package com.teamaurora.enhanced_mushrooms.core.other;

import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamaurora.enhanced_mushrooms.core.registry.EMBlocks;
import com.teamaurora.enhanced_mushrooms.core.registry.EMItems;

public class EMCompat {

    public static void registerCompat() {
        registerFlammables();
        EMItems.setupTabEditors();
        EMBlocks.setupTabEditors();
    }

    private static void registerFlammables() {
        DataUtil.registerFlammable(EMBlocks.MUSHROOM_STEM.get(), 5, 5);
        DataUtil.registerFlammable(EMBlocks.MUSHROOM_HYPHAE.get(), 5, 5);
        DataUtil.registerFlammable(EMBlocks.STRIPPED_MUSHROOM_STEM.get(), 5, 5);
        DataUtil.registerFlammable(EMBlocks.STRIPPED_MUSHROOM_HYPHAE.get(), 5, 5);

        DataUtil.registerFlammable(EMBlocks.MUSHROOM_PLANKS.get(), 5, 20);
        DataUtil.registerFlammable(EMBlocks.MUSHROOM_STAIRS.get(), 5, 20);
        DataUtil.registerFlammable(EMBlocks.MUSHROOM_SLAB.get(), 5, 20);
        DataUtil.registerFlammable(EMBlocks.MUSHROOM_FENCE.get(), 5, 20);
        DataUtil.registerFlammable(EMBlocks.MUSHROOM_FENCE_GATE.get(), 5, 20);

        DataUtil.registerFlammable(EMBlocks.MUSHROOM_BEEHIVE.get(), 5, 20);
        DataUtil.registerFlammable(EMBlocks.MUSHROOM_BOARDS.get(), 5, 20);
        DataUtil.registerFlammable(EMBlocks.MUSHROOM_BOOKSHELF.get(), 30, 20);
    }
}
