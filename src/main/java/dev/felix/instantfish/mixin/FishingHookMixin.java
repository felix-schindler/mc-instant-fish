package dev.felix.instantfish.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FishingHook.class)
abstract class FishingHookMixin {
	@Shadow private int nibble; // > 0 == "a fish is on the line right now"

	// Server-side only, every tick the bobber is in water. No extra guards needed:
	// catchingFish() only runs for a real owner, and retrieve() re-checks the rod.
	@Inject(method = "catchingFish", at = @At("HEAD"), cancellable = true)
	private void instantCatch(BlockPos pos, CallbackInfo ci) {
		FishingHook self = (FishingHook) (Object) this;
		Player owner = self.getPlayerOwner();
		InteractionHand hand = owner.getMainHandItem().is(Items.FISHING_ROD)
				? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
		ItemStack rod = owner.getItemInHand(hand);
		if (!rod.is(Items.FISHING_ROD)) return;

		// retrieve() only rolls loot while nibble > 0, so fake a live bite. openWater is still
		// true on the first water tick, so treasure can roll even in a 1x1 puddle.
		// ponytail: delay the catch N ticks if you ever want strict vanilla treasure gating.
		nibble = 20;
		rod.hurtAndBreak(self.retrieve(rod), owner, hand.asEquipmentSlot()); // same 2 lines vanilla uses
		ci.cancel();
	}
}