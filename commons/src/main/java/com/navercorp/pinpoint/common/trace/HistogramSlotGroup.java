package com.navercorp.pinpoint.common.trace;

import java.util.Objects;

public class HistogramSlotGroup {
    private final HistogramSlot fast;
    private final HistogramSlot normal;
    private final HistogramSlot slow;
    private final HistogramSlot verySlow;


    public static HistogramSlot entry(int slotTime, String slotName, SlotType slotType) {
        return new HistogramSlot((short)slotTime, slotType, slotName);
    }

    public HistogramSlotGroup(HistogramSlot fast,
                              HistogramSlot normal,
                              HistogramSlot slow,
                              HistogramSlot verySlow) {
        this.fast = Objects.requireNonNull(fast, "fast");
        this.normal = Objects.requireNonNull(normal, "normal");
        this.slow = Objects.requireNonNull(slow, "slow");
        this.verySlow = Objects.requireNonNull(verySlow, "verySlow");
    }

    public HistogramSlot getFastSlot() {
        return this.fast;
    }

    public HistogramSlot getNormalSlot() {
        return this.normal;
    }

    public HistogramSlot getSlowSlot() {
        return this.slow;
    }

    public HistogramSlot getVerySlowSlot() {
        return this.verySlow;
    }

    @Override
    public String toString() {
        return "SlotGroup{" +
                "fast=" + fast +
                ", normal=" + normal +
                ", slow=" + slow +
                ", verySlow=" + verySlow +
                '}';
    }
}
