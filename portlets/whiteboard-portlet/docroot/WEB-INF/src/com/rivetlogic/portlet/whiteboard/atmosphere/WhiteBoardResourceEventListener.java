/**
 * Copyright (C) 2005-2014 Rivet Logic Corporation.
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; version 3 of the License.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */

package com.rivetlogic.portlet.whiteboard.atmosphere;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;

import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.atmosphere.cpr.AtmosphereResourceEventListener;

public class WhiteBoardResourceEventListener implements
        AtmosphereResourceEventListener {
    
    /**
     * List of logged users
     * 
     */
    private  ConcurrentMap<String, UserData> loggedUserMap = new ConcurrentSkipListMap<String, UserData>();
    
    /**
     * Relates current connected user with the list of users
     */
    private String sessionId = null;
    
    
    public WhiteBoardResourceEventListener(
            ConcurrentMap<String, UserData> loggedUserMap, String sessionId) {
        super();
        this.loggedUserMap = loggedUserMap;
        this.sessionId = sessionId;
    }

    @Override
    public void onBroadcast(AtmosphereResourceEvent arg0) {

    }

    @Override
    public void onClose(AtmosphereResourceEvent arg0) {

    }

    @Override
    public void onDisconnect(AtmosphereResourceEvent arg0) {
        /* removes user from map and broadcast users list again */
        this.loggedUserMap.remove(sessionId);
        arg0.getResource().getBroadcaster().broadcast(WhiteboardHandlerUtil.generateLoggedUsersJSON(loggedUserMap));
    }

    @Override
    public void onPreSuspend(AtmosphereResourceEvent arg0) {
        
    }

    @Override
    public void onResume(AtmosphereResourceEvent arg0) {

    }

    @Override
    public void onSuspend(AtmosphereResourceEvent arg0) {

    }

    @Override
    public void onThrowable(AtmosphereResourceEvent arg0) {

    }

}
