package com.flying_8lack.painmod.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.RandomSpreadFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;
import java.util.Set;


public class ModFeature {
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> HEROBRINE_TREE =
            FeatureUtils.register("herobrine_tree",
            Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                            BlockStateProvider.simple(Blocks.OAK_LOG.defaultBlockState()),
                            new StraightTrunkPlacer(2, 5, 1),
                            BlockStateProvider.simple(Blocks.AIR.defaultBlockState()),
                            new RandomSpreadFoliagePlacer(ConstantInt.of(2),
                                    ConstantInt.of(0), ConstantInt.of(4),
                                    4),
                            new TwoLayersFeatureSize(5,2,5)



                    ).build());

    public static final Holder<PlacedFeature> HEROBRINE_TREE_CHECKED =
            PlacementUtils.register("herobrine_tree_checked",
            HEROBRINE_TREE, PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING));


    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> HEROBRINE_TREE_SPAWN =
            FeatureUtils.register("herobrine_tree_spawn",
                    Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(
                            new WeightedPlacedFeature(HEROBRINE_TREE_CHECKED, 0.33F)


                    ), HEROBRINE_TREE_CHECKED));


    public static final Holder<PlacedFeature> HEROBRINE_TREE_PLACED =
            PlacementUtils.register("herobrine_tree_placed",
                    HEROBRINE_TREE_SPAWN,
                    VegetationPlacements.treePlacement(
                            RarityFilter.onAverageOnceEvery(2)
                    )
            );




    public static void generateTrees(final BiomeLoadingEvent event){
        ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());
        Set<BiomeDictionary.Type> type = BiomeDictionary.getTypes(key);

        if(type.contains(BiomeDictionary.Type.PLAINS) ||
                type.contains(BiomeDictionary.Type.FOREST) ||
                type.contains(BiomeDictionary.Type.COLD) ){
            List<Holder<PlacedFeature>> base =
                    event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION);
            base.add(HEROBRINE_TREE_PLACED);
        }

    }



}
