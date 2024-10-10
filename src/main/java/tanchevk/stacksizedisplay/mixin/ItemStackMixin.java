package tanchevk.stacksizedisplay.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import tanchevk.stacksizedisplay.StackSizeDisplay;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.util.List;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
	@ModifyReturnValue(method = "getTooltip", at = @At("RETURN"))
	private List<Text> onGetTooltip(List<Text> original) {
		return StackSizeDisplay
				.itemStackTooltipHandler(original, (ItemStack) (Object) this);
	}
}
