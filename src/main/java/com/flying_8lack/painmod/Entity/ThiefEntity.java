package com.flying_8lack.painmod.Entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.entity.PartEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.function.Predicate;

public class ThiefEntity extends Monster implements IAnimatable {

    public static final EntityDataAccessor<ItemStack> HELD_ITEM =
            SynchedEntityData.defineId(ThiefEntity.class, EntityDataSerializers.ITEM_STACK);

    //public static CompoundTag StolenItem = new Comp



    private static final Predicate<Difficulty> DOOR_BREAKING_PREDICATE = (b) -> {

        return true;
    };

    public AnimationFactory factory = GeckoLibUtil.createFactory(this);// AnimationFactory(this);
    public ThiefEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier setAttributes(){
        return Monster.createMonsterAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.15f)
                .add(Attributes.MAX_HEALTH, 30.0f)
                .add(Attributes.FOLLOW_RANGE, 128.0f)
                .add(Attributes.ATTACK_DAMAGE, 2.0f)
                .add(Attributes.ATTACK_SPEED, 1.0f)
                .build();
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        if (HELD_ITEM.equals(pKey)) {
            this.refreshDimensions();
        }

        super.onSyncedDataUpdated(pKey);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {

        super.addAdditionalSaveData(pCompound);
    }


    @Override
    protected void dropCustomDeathLoot(DamageSource pSource, int pLooting, boolean pRecentlyHit) {
        if(!pSource.getEntity().getLevel().isClientSide()) {
            ItemStack item = this.entityData.get(this.HELD_ITEM);
            ItemEntity en = new ItemEntity(pSource.getEntity().getLevel(),
                    pSource.getEntity().getX(), pSource.getEntity().getY() + 1,
                    pSource.getEntity().getZ(), item);
            pSource.getEntity().getLevel().addFreshEntity(en);
        }
        super.dropCustomDeathLoot(pSource, pLooting, pRecentlyHit);
    }

    @Override
    public boolean doHurtTarget(Entity pEntity) {
        boolean flag = super.doHurtTarget(pEntity);

        if(flag){
            if(pEntity instanceof Player && !this.getEntityData().get(this.HELD_ITEM).isEmpty()){

                this.entityData.define(this.HELD_ITEM,
                        ((Player) pEntity).getItemInHand(InteractionHand.MAIN_HAND));

                ((Player) pEntity).getItemInHand(InteractionHand.MAIN_HAND).setCount(0);
                ((Player) pEntity).sendMessage(new TextComponent("I took your item"),
                        ((Player) pEntity).getUUID());



            }
        }
        return flag;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.getEntityData().define(HELD_ITEM, ItemStack.EMPTY);
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        return super.hurt(pSource, pAmount);
    }




    @Override
    public boolean canAttack(LivingEntity pTarget) {

        return super.canAttack(pTarget);
    }

    public <E extends IAnimatable>PlayState predicate(AnimationEvent<E> event){
        if(event.isMoving()){
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.thief.walk", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        } else {
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.thief.idle", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
    }
    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
    }



    protected void registerGoals(){

        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this,
                IronGolem.class,20,1.5f,2.0f));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.1f, true));
        this.goalSelector.addGoal(4,new LookAtPlayerGoal(this, Player.class, 6, 1.0f));
        this.goalSelector.addGoal(5, new BreakDoorGoal(this,20,DOOR_BREAKING_PREDICATE));



        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        //this.goalSelector.addGoal(2,new NearestAttackableTargetGoal<>(this, Player.class, 6));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this,1.0D));


        this.targetSelector.addGoal(1,
                new NearestAttackableTargetGoal<>(this, Player.class, false));

        this.targetSelector.addGoal(2,
                new NearestAttackableTargetGoal<>(this, Villager.class, false));

    }

    public boolean canBreakDoors() {

        return true;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public boolean alwaysAccepts() {
        return super.alwaysAccepts();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        super.deserializeNBT(nbt);
    }

    @Override
    public CompoundTag serializeNBT() {
        return super.serializeNBT();
    }

    @Override
    public boolean shouldRiderSit() {
        return super.shouldRiderSit();
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return super.getPickedResult(target);
    }

    @Override
    public boolean canRiderInteract() {
        return super.canRiderInteract();
    }

    @Override
    public boolean canBeRiddenInWater(Entity rider) {
        return super.canBeRiddenInWater(rider);
    }

    @Override
    public MobCategory getClassification(boolean forSpawnCount) {
        return super.getClassification(forSpawnCount);
    }

    @Override
    public boolean isMultipartEntity() {
        return super.isMultipartEntity();
    }

    @Nullable
    @Override
    public PartEntity<?>[] getParts() {
        return super.getParts();
    }

    @Override
    public float getStepHeight() {
        return super.getStepHeight();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.RAVAGER_DEATH;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        return super.getCapability(cap);
    }
}
