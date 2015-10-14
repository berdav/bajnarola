package org.bajnarola.networking;

import java.io.Serializable;

import org.bajnarola.game.controller.GameBoard;

public class NetPlayer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String username;
	public String rmiUriMain;
	public String rmiUriBoard;
	public String rmiUriLobby;
	
	public NetPlayer(String uname, String uri) {
		this.username = uname;
		this.rmiUriBoard = uri + "/" + GameBoard.class.getName();
	}
}
