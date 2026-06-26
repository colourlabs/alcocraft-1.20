package net.hadrus.alcocraft.events;

import net.hadrus.alcocraft.AlcoCraft;
import net.hadrus.alcocraft.particles.AlcoParticles;
import net.hadrus.alcocraft.particles.YellowBubbleParticles;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AlcoCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class AlcoClientEventBusEvents {
    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(AlcoParticles.YELLOW_BUBBLES.get(),
                YellowBubbleParticles.Provider::new);
    }
}