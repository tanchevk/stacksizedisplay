package tanchevk.stacksizedisplay;

import eu.midnightdust.lib.config.MidnightConfig;
import org.jetbrains.annotations.NotNull;
import net.minecraft.util.Formatting;

import static tanchevk.stacksizedisplay.StackSizeDisplayConfig.Colors.*;

public class StackSizeDisplayConfig extends MidnightConfig {
	@Entry(category = "behavior", name = "Whether to show errors in tooltip")
	public static boolean showWhenError = true;

	@Entry(category = "colors", name = "Bytes color")
	public static Colors lowColor = GRAY;

	@Entry(category = "colors", name = "Kilobytes color")
	public static Colors midColor = YELLOW;

	@Entry(category = "colors", name = "Megabytes size color")
	public static Colors highColor = LIGHT_PURPLE;

	public enum Colors {
		BLACK,
		DARK_BLUE,
		DARK_GREEN,
		DARK_AQUA,
		DARK_RED,
		DARK_PURPLE,
		GOLD,
		GRAY,
		DARK_GRAY,
		BLUE,
		GREEN,
		AQUA,
		RED,
		LIGHT_PURPLE,
		YELLOW,
		WHITE
	}

	@NotNull
	public static Formatting toFormatting(@NotNull Colors c) {
		return switch (c) {
			case BLACK -> Formatting.BLACK;
			case DARK_BLUE -> Formatting.DARK_BLUE;
			case DARK_GREEN -> Formatting.DARK_GREEN;
			case DARK_AQUA -> Formatting.DARK_AQUA;
			case DARK_RED -> Formatting.DARK_RED;
			case DARK_PURPLE -> Formatting.DARK_PURPLE;
			case GOLD -> Formatting.GOLD;
			case GRAY -> Formatting.GRAY;
			case DARK_GRAY -> Formatting.DARK_GRAY;
			case BLUE -> Formatting.BLUE;
			case GREEN -> Formatting.GREEN;
			case AQUA -> Formatting.AQUA;
			case RED -> Formatting.RED;
			case LIGHT_PURPLE -> Formatting.LIGHT_PURPLE;
			case YELLOW -> Formatting.YELLOW;
			case WHITE -> Formatting.WHITE;
		};
	}
}
