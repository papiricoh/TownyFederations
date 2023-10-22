package org.papiricoh.townyfederations.invite;

import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.object.Nation;
import org.bukkit.OfflinePlayer;
import org.papiricoh.townyfederations.TownyFederation;
import org.papiricoh.townyfederations.object.Federation;

import java.util.HashMap;
import java.util.Map;

public class InvitesManager {
    Map<Nation, FederationJoinInvite> joinInvites; //receiver, invite;

    public InvitesManager() {
        this.joinInvites = new HashMap<>();
    }

    public boolean newInvite(Nation sender, Nation receiver) {
        if(!TownyUniverse.getInstance().hasNation(sender.getUUID()) || !TownyUniverse.getInstance().hasNation(receiver.getUUID())) {
            return false;
        }
        Federation federation = TownyFederation.getInstance().getFederationByMember(sender);
        if(federation == null) {
            return false;
        }
        this.joinInvites.put(receiver, new FederationJoinInvite(sender, receiver, federation));
        return true;
    }

    public boolean acceptInvite(OfflinePlayer player) {
        try {
            Nation nation = TownyUniverse.getInstance().getResident(player.getUniqueId()).getNation();
            FederationJoinInvite invite = getInviteByReceiver(nation);
            if(invite == null) {
                return false;
            }
            if(invite.accept(player)) {
                this.joinInvites.remove(nation);
                return true;
            }
            return false;
        } catch (TownyException e) {
            return false;
        }
    }

    public boolean denyInvite(OfflinePlayer player) {
        try {
            Nation nation = TownyUniverse.getInstance().getResident(player.getUniqueId()).getNation();
            FederationJoinInvite invite = getInviteByReceiver(nation);
            if(invite == null) {
                return false;
            }
            if(invite.deny(player)) {
                this.joinInvites.remove(nation);
                return true;
            }
            return false;
        } catch (TownyException e) {
            return false;
        }
    }

    private FederationJoinInvite getInviteByReceiver(Nation nation) {
        for (Nation n : this.joinInvites.keySet()) {
            if(n.equals(nation)) {
                return this.joinInvites.get(n);
            }
        }
        return null;
    }
}
