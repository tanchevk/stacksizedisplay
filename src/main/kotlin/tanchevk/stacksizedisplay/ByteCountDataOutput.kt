package tanchevk.stacksizedisplay

import java.io.DataOutput

object ByteCountDataOutput : DataOutput {
	var count: UInt = 0u
		private set

	fun reset() {
		count = 0u
	}

	override fun write(b: Int) {
		count++
	}

	override fun write(b: ByteArray?) {
		count += b!!.size.toUInt()
	}

	override fun write(b: ByteArray?, off: Int, len: Int) {
		count += len.toUInt()
	}

	override fun writeBoolean(v: Boolean) {
		count++
	}

	override fun writeByte(v: Int) {
		count++
	}

	override fun writeShort(v: Int) {
		count += 2u
	}

	override fun writeChar(v: Int) {
		count += 2u
	}

	override fun writeInt(v: Int) {
		count += 4u
	}

	override fun writeLong(v: Long) {
		count += 8u
	}

	override fun writeFloat(v: Float) {
		count += 4u
	}

	override fun writeDouble(v: Double) {
		count += 8u
	}

	override fun writeBytes(s: String) {
		count += s.length.toUInt()
	}

	override fun writeChars(s: String) {
		count += s.length.toUInt() * 2u
	}

	override fun writeUTF(s: String) {
		count += 2u + getUTFLength(s).toUInt()
	}

	fun getUTFLength(string: String): Long {
		var utfLength = 0L

		for (cPos in 0..<string.length) {
			val char = string[cPos]

			if (char >= 0x0001.toChar() && char <= 0x007F.toChar()) {
				utfLength++
			} else if (char > 0x07FF.toChar()) {
				utfLength += 3
			} else {
				utfLength += 2
			}
		}

		return utfLength
	}
}
