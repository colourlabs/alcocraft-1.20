package net.hadrus.alcocraft.events;

import net.hadrus.alcocraft.AlcoCraft;
import net.hadrus.alcocraft.recipes.KegRecipe;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = AlcoCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AlcoEventBusEvents {
    @SubscribeEvent
    public static void registerRecipeTypes(final RegisterEvent event) {
        event.register(ForgeRegistries.Keys.RECIPE_TYPES,
                helper -> helper.register(KegRecipe.Type.ID, KegRecipe.Type.INSTANCE));
    }
}