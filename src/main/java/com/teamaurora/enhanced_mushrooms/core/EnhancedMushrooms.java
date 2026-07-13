package com.teamaurora.enhanced_mushrooms.core;

import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import com.teamaurora.enhanced_mushrooms.core.data.client.EMBlockStateProvider;
import com.teamaurora.enhanced_mushrooms.core.data.client.EMItemModelProvider;
import com.teamaurora.enhanced_mushrooms.core.data.server.EMLootTableProvider;
import com.teamaurora.enhanced_mushrooms.core.data.server.EMRecipeProvider;
import com.teamaurora.enhanced_mushrooms.core.data.server.tags.EMBlockTagsProvider;
import com.teamaurora.enhanced_mushrooms.core.data.server.tags.EMItemTagsProvider;
import com.teamaurora.enhanced_mushrooms.core.other.EMClientCompat;
import com.teamaurora.enhanced_mushrooms.core.other.EMCompat;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

@Mod(EnhancedMushrooms.MOD_ID)
public class EnhancedMushrooms
{
    public static final String MOD_ID = "enhanced_mushrooms";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);
    public static final RegistryHelper VANILLA_HELPER = new RegistryHelper("minecraft");

    public EnhancedMushrooms(IEventBus bus, ModContainer modContainer) {

        REGISTRY_HELPER.register(bus);
        VANILLA_HELPER.register(bus);

        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);
        bus.addListener(this::dataSetup);
        bus.addListener(EventPriority.LOWEST, this::buildCreativeModeTabContents);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(EMCompat::registerCompat);
    }

    private void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(EMClientCompat::registerRenderLayers);
    }

    private void dataSetup(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();

        boolean includeServer = event.includeServer();
        EMBlockTagsProvider blockTags = new EMBlockTagsProvider(output, provider, helper);
        generator.addProvider(includeServer, blockTags);
        generator.addProvider(includeServer, new EMItemTagsProvider(output, provider, blockTags.contentsGetter(), helper));
        generator.addProvider(includeServer, new EMRecipeProvider(output, provider));
        generator.addProvider(includeServer, new EMLootTableProvider(output, provider));

        boolean includeClient = event.includeClient();
        generator.addProvider(includeClient, new EMBlockStateProvider(output, helper));
        generator.addProvider(includeClient, new EMItemModelProvider(output, helper));
    }

    @SubscribeEvent
    public void buildCreativeModeTabContents(@NotNull BuildCreativeModeTabContentsEvent event)
    {
//        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
//            MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries = event.getEntries();
//            ArrayList<ItemStack> mushroom_stems = new ArrayList<>();
//            for (Map.Entry<ItemStack, CreativeModeTab.TabVisibility> entry : entries)
//            {
//                if (entry.getKey().is(Items.MUSHROOM_STEM)) {
//                    //mushroom_stems.add(entry.getKey());
//                    LOGGER.info(entry.getKey());
//                }
//            }
//            mushroom_stems.forEach(entries::remove);
//        }
    }
}
