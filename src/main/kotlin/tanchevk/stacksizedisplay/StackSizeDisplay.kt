package tanchevk.stacksizedisplay

import net.fabricmc.api.ClientModInitializer
import net.minecraft.client.MinecraftClient
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import org.slf4j.LoggerFactory

object StackSizeDisplay : ClientModInitializer {
    private val logger = LoggerFactory.getLogger("Stack Size Display")

	override fun onInitializeClient() {
		logger.info("Initialized")
	}

	@JvmStatic
	fun itemStackTooltipHandler(
		original: MutableList<Text>,
		itemStack: ItemStack
	): MutableList<Text> {
		var tooltip = original

		try {
			itemStack
				.encode(MinecraftClient.getInstance().player!!.registryManager)
				.write(ByteCountDataOutput)

			val byteCount = ByteCountDataOutput.count.toInt()

			ByteCountDataOutput.reset()

			var count = if (byteCount >= 1_048_576)
				String.format("%.2f MB", byteCount / 1_048_576f)
			else if (byteCount >= 1024)
				String.format("%.2f KB", byteCount / 1024f)
			else
				String.format("%d bytes", byteCount)

			tooltip.add(
				Text.literal(count)
					.formatted(Formatting.LIGHT_PURPLE)
			)
		} catch (e: Exception) {
			logger.error("Failed to get item stack bytes: ", e)
			tooltip.add(
				Text.literal("Error getting bytes")
					.formatted(Formatting.RED)
			)
		}

		return tooltip
	}
}
