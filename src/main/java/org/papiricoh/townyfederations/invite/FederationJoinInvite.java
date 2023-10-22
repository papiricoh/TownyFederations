package org.papiricoh.townyfederations.invite;

import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;
import org.bukkit.OfflinePlayer;
import org.papiricoh.townyfederations.TownyFederation;
import org.papiricoh.townyfederations.object.Federation;

public class FederationJoinInvite {
    private Nation sender;
    private Nation receiver;
    private Federation federation;

    public FederationJoinInvite(Nation sender, Nation receiver, Federation federation) {
        this.sender = sender;
        this.receiver = receiver;
        this.federation = federation;
    }

    public Nation getSender() {
        return sender;
    }

    public Nation getReceiver() {
        return receiver;
    }

    public Federation getFederation() {
        return federation;
    }

    public boolean accept(OfflinePlayer player) {
        Resident res = TownyUniverse.getInstance().getResident(player.getUniqueId());
        if (this.receiver.isKing(res)) {
            TownyFederation.getInstance().getFederation(this.federation.getUuid()).addMember(this.receiver);
            return true;
        }
        return false;
    }

    public boolean deny(OfflinePlayer player) {
        Resident res = TownyUniverse.getInstance().getResident(player.getUniqueId());
        if (this.receiver.isKing(res)) {
            return true;
        }
        return false;
    }
}
