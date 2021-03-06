package com.rebotted.net.packets.impl;

import com.rebotted.game.players.Player;
import com.rebotted.game.players.PlayerHandler;
import com.rebotted.net.packets.PacketType;

/**
 * Challenge Player
 **/
public class ChallengePlayer implements PacketType {

	@Override
	public void processPacket(Player c, int packetType, int packetSize) {
		switch (packetType) {
		case 128:
			int answerPlayer = c.getInStream().readUnsignedWord();
		    if(PlayerHandler.players[answerPlayer] == null || answerPlayer == c.playerId)
                return;

			if (c.duelingArena() || c.duelStatus == 5) {
				c.getPacketSender().sendMessage("You can't challenge inside the arena!");
				return;
			}
			if (c.inDuelArena()) {
				c.getDueling().requestDuel(answerPlayer);
			}
			break;
		}
	}
}
