
package com.rayzr522.bglib.commands.preset;

import java.util.Arrays;
import java.util.List;

import com.rayzr522.bitzapi.commands.BitzCommand;

@SuppressWarnings("unchecked")
public class PresetCommands {

    public static class Setup {

        public static class Region {

            public static final Class<MGPCSetupRegionArena>        Arena = MGPCSetupRegionArena.class;
            public static final Class<MGPCSetupRegionLobby>        Lobby = MGPCSetupRegionLobby.class;

            public static final List<Class<? extends BitzCommand>> All   = Arrays.asList(Arena, Lobby);

        }

        public static class Spawn {

            public static final Class<MGPCSetupSpawnPlayers>       Players = MGPCSetupSpawnPlayers.class;
            public static final Class<MGPCSetupSpawnLobby>         Lobby   = MGPCSetupSpawnLobby.class;

            public static final List<Class<? extends BitzCommand>> All     = Arrays.asList(Players, Lobby);

        }

        public static class Arena {

            public static final Class<MGPCSetupArenaCreate>        Create = MGPCSetupArenaCreate.class;
            public static final Class<MGPCSetupArenaRemove>        Remove = MGPCSetupArenaRemove.class;
            public static final Class<MGPCSetupArenaRename>        Rename = MGPCSetupArenaRename.class;
            public static final Class<MGPCSetupArenaSelect>        Select = MGPCSetupArenaSelect.class;

            public static final List<Class<? extends BitzCommand>> All    = Arrays.asList(Create, Remove, Rename, Select);

        }

        public static final List<Class<? extends BitzCommand>> All = Arrays.asList(Region.Arena, Region.Lobby, Spawn.Players, Spawn.Lobby, Arena.Create, Arena.Remove, Arena.Rename, Arena.Select);

    }

    public static class Game {

        public static class Player {

            public static final Class<MGPCGamePlayerJoin>  Join  = MGPCGamePlayerJoin.class;
            public static final Class<MGPCGamePlayerLeave> Leave = MGPCGamePlayerLeave.class;

        }

        public static final List<Class<? extends BitzCommand>> All = Arrays.asList(Player.Join, Player.Leave);

    }

}
