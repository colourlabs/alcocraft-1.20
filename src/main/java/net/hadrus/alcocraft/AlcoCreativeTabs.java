package net.hadrus.alcocraft;

import net.hadrus.alcocraft.blocks.AlcoBlocks;
import net.hadrus.alcocraft.items.AlcoItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class AlcoCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AlcoCraft.MOD_ID);

    public static final RegistryObject<CreativeModeTab> ALCOCRAFT_TAB = CREATIVE_MODE_TABS.register("alcocraft_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("creativetab.alcocraft"))
                    .icon(() -> new ItemStack(AlcoBlocks.SPRUCE_KEG.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(AlcoBlocks.SPRUCE_KEG.get());

                        output.accept(AlcoItems.HOP.get());
                        output.accept(AlcoItems.HOP_SEEDS.get());
                        output.accept(AlcoItems.DRY_SEEDS.get());
                        output.accept(AlcoItems.SPRUCE_EMPTY_MUG.get());

                        output.accept(AlcoItems.SPRUCE_MUG_SUN_PALE_ALE.get());
                        output.accept(AlcoItems.SPRUCE_MUG_DIGGER_BITTER.get());
                        output.accept(AlcoItems.SPRUCE_MUG_NETHER_PORTER.get());
                        output.accept(AlcoItems.SPRUCE_MUG_WITHER_STOUT.get());
                        output.accept(AlcoItems.SPRUCE_MUG_MAGNET_PILSNER.get());
                        output.accept(AlcoItems.SPRUCE_MUG_DROWNED_ALE.get());
                        output.accept(AlcoItems.SPRUCE_MUG_NIGHT_RAUCH.get());
                        output.accept(AlcoItems.SPRUCE_MUG_ICE_BEER.get());
                        output.accept(AlcoItems.SPRUCE_MUG_KVASS.get());
                        output.accept(AlcoItems.SPRUCE_MUG_LEPRECHAUN_CIDER.get());
                        output.accept(AlcoItems.SPRUCE_MUG_CHORUS_ALE.get());
                        output.accept(AlcoItems.SPRUCE_MUG_NETHER_STAR_LAGER.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}