package org.papiricoh.townyfederations.object;

import com.palmergames.bukkit.towny.object.Nation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Federation {
    private UUID uuid;
    private Nation president;
    private Map<UUID, Nation> members;

    public Federation(Nation president) {
        this.uuid = UUID.randomUUID();
        this.president = president;
        this.members = new HashMap<>();
    }

    public Federation(UUID uuid, Nation president, List<Nation> nations) {
        this.uuid = uuid;
        this.president = president;
        this.members = toMembers(nations);
    }

    private Map<UUID, Nation> toMembers(List<Nation> nations) {
        Map<UUID, Nation> returnedMembers = new HashMap<>();
        for (Nation n : nations) {
            returnedMembers.put(n.getUUID(), n);
        }
        return returnedMembers;
    }

    private boolean isPresident(Nation nation) {
        return nation.equals(this.president);
    }
}
