/*
 * Copyright 2014 NAVER Corp.
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

package com.navercorp.pinpoint.profiler;

import com.navercorp.pinpoint.common.trace.ServiceType;
import com.navercorp.pinpoint.profiler.name.ObjectName;

import java.util.Objects;

/**
 * @author emeroad
 * @author koo.taejin
 * @author hyungil.jeong
 */
public class DefaultAgentInformation implements AgentInformation {
    private final ObjectName objectName;
    private final boolean isContainer;
    private final long startTime;
    private final int pid;
    private final String machineName;
    private final String hostIp;
    private final ServiceType serverType;
    private final String jvmVersion;
    private final String agentVersion;

    private final String clusterNamespace;

    public DefaultAgentInformation(
            ObjectName objectName,
            boolean isContainer,
            long startTime,
            int pid,
            String machineName,
            String hostIp,
            ServiceType serverType,
            String jvmVersion,
            String agentVersion,
            String clusterNamespace) {
        this.objectName = Objects.requireNonNull(objectName, "objectName");
        this.isContainer = isContainer;
        this.startTime = startTime;
        this.pid = pid;
        this.machineName = Objects.requireNonNull(machineName, "machineName");
        this.hostIp = hostIp;
        this.serverType = serverType;
        this.jvmVersion = jvmVersion;
        this.agentVersion = Objects.requireNonNull(agentVersion, "agentVersion");

        this.clusterNamespace = clusterNamespace;
    }

    @Override
    public String getAgentId() {
        return objectName.getAgentId();
    }

    @Override
    public String getAgentName() {
        return objectName.getAgentName();
    }

    @Override
    public String getApplicationName() {
        return objectName.getApplicationName();
    }

    @Override
    public boolean isContainer() {
        return isContainer;
    }

    @Override
    public long getStartTime() {
        return startTime;
    }

    @Override
    public int getPid() {
        return pid;
    }

    @Override
    public String getMachineName() {
        return machineName;
    }

    @Override
    public String getHostIp() {
        return hostIp;
    }

    @Override
    public ServiceType getServerType() {
        return serverType;
    }
    
    @Override
    public String getJvmVersion() {
        return this.jvmVersion;
    }

    @Override
    public String getAgentVersion() {
        return agentVersion;
    }

    @Override
    public String getClusterNamespace() {
        return clusterNamespace;
    }

    @Override
    public String toString() {
        return "{" + "objectName='" + objectName + '\'' +
                ", isContainer=" + isContainer +
                ", startTime=" + startTime +
                ", pid=" + pid +
                ", machineName='" + machineName + '\'' +
                ", hostIp='" + hostIp + '\'' +
                ", serverType=" + serverType +
                ", jvmVersion='" + jvmVersion + '\'' +
                ", agentVersion='" + agentVersion + '\'' +
                ", clusterNamespace='" + clusterNamespace + '\'' +
                '}';
    }
}
