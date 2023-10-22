package org.papiricoh.townyfederations.invite;

import com.palmergames.bukkit.towny.object.Nation;
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
}
