package tanchevk.stacksizedisplay

import eu.midnightdust.lib.config.MidnightConfig
import net.fabricmc.api.ModInitializer

object StackSizeDisplayMain : ModInitializer {
	override fun onInitialize() {
		MidnightConfig.init("stacksizedisplay", StackSizeDisplayConfig::class.java)
	}
}
