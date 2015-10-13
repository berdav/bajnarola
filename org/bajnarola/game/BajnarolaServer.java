/*****************************************************************************/
/* Server package for Bajnarola distributed game                             */
/*                                                                           */
/* Copyright (C) 2015                                                        */
/* Marco Melletti, Davide Berardi, Matteo Martelli                           */
/*                                                                           */
/* This program is free software; you can redistribute it and/or             */
/* modify it under the terms of the GNU General Public License               */
/* as published by the Free Software Foundation; either version 2            */
/* of the License, or any later version.                                     */
/*                                                                           */
/* This program is distributed in the hope that it will be useful,           */
/* but WITHOUT ANY WARRANTY; without even the implied warranty of            */
/* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the             */
/* GNU General Public License for more details.                              */
/*                                                                           */
/* You should have received a copy of the GNU General Public License         */
/* along with this program; if not, write to the Free Software               */
/* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,*/
/* USA.                                                                      */
/*****************************************************************************/

package org.bajnarola.game;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

import org.bajnarola.game.model.Board;
import org.bajnarola.networking.NetPlayer;
import org.bajnarola.utils.RandomString;

import java.util.Hashtable;

public class BajnarolaServer {

	NetPlayer player = null;
	Map<String,NetPlayer> players;
	
	public NetPlayer getPlayer() {
		return this.player;
	}
	
	private void setRebind(String path, Remote o) {
		String npath = path + "/" + o.getClass().getName();
		try {
			Naming.rebind(npath, o);
		} catch (RemoteException | MalformedURLException e) {
			e.printStackTrace();
		}
		
		System.out.print("\n\tListening on '" + npath + "' ...");
	}
	
	private void CommonConstruct(String server, String basepath, Board myBoard) {
		String path = server + "/" + basepath;
		this.player = new NetPlayer(basepath, path);
		this.players = new Hashtable<String,NetPlayer>();
				
		this.setRebind(path, myBoard);
	}

	public BajnarolaServer(String server, String basepath, Board myBoard) {
		this.CommonConstruct(server, basepath, myBoard);
	}
	public BajnarolaServer(String server, Board myBoard) {
		String s = RandomString.generateAsciiString();
		this.CommonConstruct(server, s, myBoard);
	}
}