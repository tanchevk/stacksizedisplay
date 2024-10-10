package tanchevk.stacksizedisplay

import net.fabricmc.api.ClientModInitializer
import net.minecraft.client.MinecraftClient
import net.minecraft.util.Formatting
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
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

			val LOW = 0
			val MID = 1
			val HIGH = 2

			var amountColor: Int

			var count = if (byteCount >= 1_048_576) {
				amountColor = HIGH
				String.format("%.2f MB", byteCount / 1_048_576f)
			} else if (byteCount >= 1024) {
				amountColor = MID
				String.format("%.2f KB", byteCount / 1024f)
			} else {
				amountColor = LOW
				String.format("%d bytes", byteCount)
			}

			require(amountColor < 3) {
				"Unexpected value found for amountColor: found $amountColor when max expected value was $HIGH"
			}

			tooltip.add(
				Text.literal(count)
					.formatted(when (amountColor) {
						LOW -> StackSizeDisplayConfig.toFormatting(StackSizeDisplayConfig.lowColor)
						MID -> StackSizeDisplayConfig.toFormatting(StackSizeDisplayConfig.midColor)
						HIGH -> StackSizeDisplayConfig.toFormatting(StackSizeDisplayConfig.highColor)
						else -> {
							logger.error("Invalid State! (amountColor: $amountColor)")
							throw IllegalStateException("Invalid State")
						}
					})
			)
		} catch (e: Exception) {
			logger.error("Failed to get item stack bytes: ", e)
			if (StackSizeDisplayConfig.showWhenError) {
				tooltip.add(
					Text.literal("Error getting bytes")
						.formatted(Formatting.RED)
				)
			}
		}

		return tooltip
	}
}
