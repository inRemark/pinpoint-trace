/*
 * Copyright 2017 NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.navercorp.pinpoint.common.server.bo.event;

import com.navercorp.pinpoint.common.server.util.AgentEventType;
import com.navercorp.pinpoint.common.server.util.ByteUtils;
import com.navercorp.pinpoint.common.server.util.StringPrecondition;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author HyunGil Jeong
 * @author jaehong.kim - Update version & Remove hashcode, equals
 */
public class AgentEventBo {
    @Deprecated
    public static final int LEGACY_VERSION = 0;
    public static final int CURRENT_VERSION = 1;

    private final byte version;
    @NotBlank private final String agentId;
    @PositiveOrZero private final long startTimestamp;
    @PositiveOrZero private final long eventTimestamp;
    private final AgentEventType eventType;
    private byte[] eventBody;

    public AgentEventBo(String agentId, long startTimestamp, long eventTimestamp, AgentEventType eventType) {
        this(CURRENT_VERSION, agentId, startTimestamp, eventTimestamp, eventType);
    }

    public AgentEventBo(int version, String agentId, long startTimestamp, long eventTimestamp, AgentEventType eventType) {
        this.version = ByteUtils.toUnsignedByte(version);
        this.agentId = StringPrecondition.requireHasLength(agentId, "agentId");
        if (startTimestamp < 0) {
            throw new IllegalArgumentException("startTimestamp cannot be less than 0");
        }
        if (eventTimestamp < 0) {
            throw new IllegalArgumentException("eventTimestamp cannot be less than 0");
        }
        this.startTimestamp = startTimestamp;
        this.eventTimestamp = eventTimestamp;
        this.eventType = Objects.requireNonNull(eventType, "eventType");
    }

    public int getVersion() {
        return Byte.toUnsignedInt(this.version);
    }

    public String getAgentId() {
        return agentId;
    }

    public long getStartTimestamp() {
        return startTimestamp;
    }

    public long getEventTimestamp() {
        return eventTimestamp;
    }

    public AgentEventType getEventType() {
        return eventType;
    }

    public byte[] getEventBody() {
        return eventBody;
    }

    public void setEventBody(byte[] eventBody) {
        this.eventBody = eventBody;
    }

    @Override
    public String toString() {
        return "AgentEventBo{" +
                "version=" + version +
                ", agentId='" + agentId + '\'' +
                ", startTimestamp=" + startTimestamp +
                ", eventTimestamp=" + eventTimestamp +
                ", eventType=" + eventType +
                ", eventBody=" + Arrays.toString(eventBody) +
                '}';
    }
}
