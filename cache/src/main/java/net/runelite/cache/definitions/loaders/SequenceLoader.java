/*
 * Copyright (c) 2016, Adam <Adam@sigterm.info>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. All advertising materials mentioning features or use of this software
 *    must display the following acknowledgement:
 *    This product includes software developed by Adam <Adam@sigterm.info>
 * 4. Neither the name of the Adam <Adam@sigterm.info> nor the
 *    names of its contributors may be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY Adam <Adam@sigterm.info> ''AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Adam <Adam@sigterm.info> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.cache.definitions.loaders;

import net.runelite.cache.definitions.SequenceDefinition;
import net.runelite.cache.io.InputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SequenceLoader
{
	private static final Logger logger = LoggerFactory.getLogger(SequenceLoader.class);

	public SequenceDefinition load(int id, InputStream stream)
	{
		SequenceDefinition def = new SequenceDefinition(id);

		while (true)
		{
			int opcode = stream.readUnsignedByte();
			if (opcode == 0)
			{
				break;
			}

			this.decodeValues(opcode, def, stream);
		}

		return def;
	}

	private void decodeValues(int opcode, SequenceDefinition def, InputStream stream)
	{
		int var3;
		int var4;
		if (opcode == 1)
		{
			var3 = stream.readUnsignedShort();
			def.frameLenghts = new int[var3];

			for (var4 = 0; var4 < var3; ++var4)
			{
				def.frameLenghts[var4] = stream.readUnsignedShort();
			}

			def.frameIDs = new int[var3];

			for (var4 = 0; var4 < var3; ++var4)
			{
				def.frameIDs[var4] = stream.readUnsignedShort();
			}

			for (var4 = 0; var4 < var3; ++var4)
			{
				def.frameIDs[var4] += stream.readUnsignedShort() << 16;
			}
		}
		else if (opcode == 2)
		{
			def.frameStep = stream.readUnsignedShort();
		}
		else if (opcode == 3)
		{
			var3 = stream.readUnsignedByte();
			def.interleaveLeave = new int[1 + var3];

			for (var4 = 0; var4 < var3; ++var4)
			{
				def.interleaveLeave[var4] = stream.readUnsignedByte();
			}

			def.interleaveLeave[var3] = 9999999;
		}
		else if (opcode == 4)
		{
			def.stretches = true;
		}
		else if (opcode == 5)
		{
			def.forcedPriority = stream.readUnsignedByte();
		}
		else if (opcode == 6)
		{
			def.leftHandItem = stream.readUnsignedShort();
		}
		else if (opcode == 7)
		{
			def.rightHandItem = stream.readUnsignedShort();
		}
		else if (opcode == 8)
		{
			def.maxLoops = stream.readUnsignedByte();
		}
		else if (opcode == 9)
		{
			def.precedenceAnimating = stream.readUnsignedByte();
		}
		else if (opcode == 10)
		{
			def.priority = stream.readUnsignedByte();
		}
		else if (opcode == 11)
		{
			def.replyMode = stream.readUnsignedByte();
		}
		else if (opcode == 12)
		{
			var3 = stream.readUnsignedByte();
			def.field3048 = new int[var3];

			for (var4 = 0; var4 < var3; ++var4)
			{
				def.field3048[var4] = stream.readUnsignedShort();
			}

			for (var4 = 0; var4 < var3; ++var4)
			{
				def.field3048[var4] += stream.readUnsignedShort() << 16;
			}
		}
		else if (opcode == 13)
		{
			var3 = stream.readUnsignedByte();
			def.field3056 = new int[var3];

			for (var4 = 0; var4 < var3; ++var4)
			{
				def.field3056[var4] = stream.read24BitInt();
			}
		}

	}
}