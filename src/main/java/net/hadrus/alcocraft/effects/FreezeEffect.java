package net.hadrus.alcocraft.effects;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;

public class FreezeEffect extends MobEffect {
    protected FreezeEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {

        if (pLivingEntity.onGround()) {
            BlockState blockstate = Blocks.FROSTED_ICE.defaultBlockState();
            BlockPos pPos = pLivingEntity.blockPosition();

            int pLevelConflicting = 1;

            float f = (float)Math.min(16, 2 + pLevelConflicting);

            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for (BlockPos blockpos : BlockPos.betweenClosed(pPos.offset((int)(-f), -1, (int)(-f)), pPos.offset((int)f, -1, (int)f))) {
                if (blockpos.closerToCenterThan(pLivingEntity.position(), (double)f)) {
                    blockpos$mutableblockpos.set(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
                    BlockState blockstate1 = pLivingEntity.level().getBlockState(blockpos$mutableblockpos);
                    if (blockstate1.isAir()) {
                        BlockState blockstate2 = pLivingEntity.level().getBlockState(blockpos);
                        boolean isFull = blockstate2.getBlock() == Blocks.WATER && blockstate2.getValue(LiquidBlock.LEVEL) == 0;
                        Level level = pLivingEntity.level();
                        if (blockstate2.getFluidState().is(FluidTags.WATER) && blockstate2.getFluidState().isSource() && isFull
                                && blockstate.canSurvive(level, blockpos)
                                && level.isUnobstructed(blockstate, blockpos, CollisionContext.empty())
                                && !net.minecraftforge.event.ForgeEventFactory.onBlockPlace(pLivingEntity, net.minecraftforge.common.util.BlockSnapshot.create(level.dimension(), level, blockpos), net.minecraft.core.Direction.UP)) {
                            level.setBlockAndUpdate(blockpos, blockstate);
                            level.scheduleTick(blockpos, Blocks.FROSTED_ICE, Mth.nextInt(pLivingEntity.getRandom(), 60, 120));
                        }
                    }
                }
            }
        }

        Level level = pLivingEntity.level();

        for (Entity e : level.getEntities(pLivingEntity, new AABB(pLivingEntity.blockPosition()).inflate(5))) {
            if (e instanceof LivingEntity entity) {
                entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 2));
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}