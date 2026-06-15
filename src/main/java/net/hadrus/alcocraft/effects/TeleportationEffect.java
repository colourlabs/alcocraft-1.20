package net.hadrus.alcocraft.effects;

import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class TeleportationEffect extends MobEffect {

    protected TeleportationEffect(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        RandomSource random = pLivingEntity.getRandom();

        double d0 = pLivingEntity.getX();
        double d1 = pLivingEntity.getY();
        double d2 = pLivingEntity.getZ();

        pLivingEntity.teleportTo(d0 + random.nextDouble(), d1 + random.nextDouble(), d2 + random.nextDouble());
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}