package net.hadrus.alcocraft.loot;

import com.mojang.serialization.Codec;
import net.hadrus.alcocraft.AlcoCraft;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AlcoLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, AlcoCraft.MOD_ID);

    public static final RegistryObject<Codec<DungeonChests>> DUNGEON_CHESTS =
            LOOT_MODIFIERS.register("dungeon_chests", () -> DungeonChests.CODEC);

    public static final RegistryObject<Codec<HopSeedsFromGrass>> HOP_SEEDS_FROM_GRASS =
            LOOT_MODIFIERS.register("hop_seeds_from_grass", () -> HopSeedsFromGrass.CODEC);

    public static void register(IEventBus eventBus) {
        LOOT_MODIFIERS.register(eventBus);
    }
}