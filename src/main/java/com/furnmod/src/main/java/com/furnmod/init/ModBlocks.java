package com.furnmod.init;

import com.furnmod.FurnMod;
import com.furnmod.blocks.ChairBlock;
import com.furnmod.blocks.TableBlock;
import com.furnmod.blocks.SofaBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, FurnMod.MOD_ID);

    public static final RegistryObject<Block> WOOD_CHAIR = BLOCKS.register("wood_chair",
            () -> new ChairBlock(AbstractBlock.Properties.of(Material.WOOD).strength(1.5f).noOcclusion()));

    public static final RegistryObject<Block> OAK_CHAIR = BLOCKS.register("oak_chair",
            () -> new ChairBlock(AbstractBlock.Properties.of(Material.WOOD).strength(1.5f).noOcclusion()));

    public static final RegistryObject<Block> WOOD_TABLE = BLOCKS.register("wood_table",
            () -> new TableBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2.0f).noOcclusion()));

    public static final RegistryObject<Block> OAK_TABLE = BLOCKS.register("oak_table",
            () -> new TableBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2.0f).noOcclusion()));

    public static final RegistryObject<Block> RED_SOFA = BLOCKS.register("red_sofa",
            () -> new SofaBlock(AbstractBlock.Properties.of(Material.WOOL).strength(1.0f).noOcclusion()));

    public static final RegistryObject<Block> BLUE_SOFA = BLOCKS.register("blue_sofa",
            () -> new SofaBlock(AbstractBlock.Properties.of(Material.WOOL).strength(1.0f).noOcclusion()));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
