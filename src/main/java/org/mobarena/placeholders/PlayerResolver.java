package org.mobarena.placeholders;

import com.garbagemule.MobArena.ArenaPlayerStatistics;
import com.garbagemule.MobArena.MobArena;
import com.garbagemule.MobArena.framework.Arena;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;

public class PlayerResolver {

    private final MobArena mobarena;

    PlayerResolver(Plugin mobarena) {
        this.mobarena = (MobArena) mobarena;
    }

    String resolve(OfflinePlayer target, String tail) {
        // mobarena_player is invalid
        if (tail == null) {
            return null;
        }

        if (target == null || !target.isOnline()) {
            return null;
        }

        OfflinePlayer player = target.getPlayer();
        Arena arena = mobarena.getArenaMaster().getArenaWithPlayer(player.getPlayer());
        if (arena == null || !arena.inArena(player.getPlayer())) {
            return "";
        }

        if (player.getPlayer() != null) {
            ArenaPlayerStatistics currentStats = arena.getArenaPlayer(player.getPlayer()).getStats();
            switch (tail) {
                case "class": {
                    return currentStats.getClassName();
                }
                case "kills": {
                    return String.valueOf(currentStats.getInt("kills"));
                }
                case "damage-done": {
                    return String.valueOf(currentStats.getInt("dmgDone"));
                }
                case "damage-taken": {
                    return String.valueOf(currentStats.getInt("dmgTaken"));
                }
                case "swings": {
                    return String.valueOf(currentStats.getInt("swings"));
                }
                case "hits": {
                    return String.valueOf(currentStats.getInt("hits"));
                }
                case "last-wave": {
                    return String.valueOf(currentStats.getInt("lastWave"));
                }
                default: {
                    return null;
                }
            }
        }
        return null;
    }
}
