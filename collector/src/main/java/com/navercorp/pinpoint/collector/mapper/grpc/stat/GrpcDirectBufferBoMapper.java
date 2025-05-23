/*
 * Copyright 2019 NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.navercorp.pinpoint.collector.mapper.grpc.stat;

import com.navercorp.pinpoint.common.server.bo.stat.AgentStatBo;
import com.navercorp.pinpoint.common.server.bo.stat.DataPoint;
import com.navercorp.pinpoint.common.server.bo.stat.DirectBufferBo;
import com.navercorp.pinpoint.grpc.trace.PAgentStat;
import com.navercorp.pinpoint.grpc.trace.PDirectBuffer;
import org.springframework.stereotype.Component;

/**
 * @author Roy Kim
 */
@Component
public class GrpcDirectBufferBoMapper implements GrpcStatMapper {

    public DirectBufferBo map(DataPoint point, final PDirectBuffer tOpenDirectBuffer) {
        return new DirectBufferBo(point,
                tOpenDirectBuffer.getDirectCount(),
                tOpenDirectBuffer.getDirectMemoryUsed(),
                tOpenDirectBuffer.getMappedCount(),
                tOpenDirectBuffer.getMappedMemoryUsed());
    }

    @Override
    public void map(AgentStatBo.Builder.StatBuilder builder, PAgentStat agentStat) {
        // directBuffer
        if (agentStat.hasDirectBuffer()) {
            final PDirectBuffer directBuffer = agentStat.getDirectBuffer();
            DataPoint point = builder.getDataPoint();
            final DirectBufferBo directBufferBo = this.map(point, directBuffer);
            builder.addPoint(directBufferBo);
        }
    }
}