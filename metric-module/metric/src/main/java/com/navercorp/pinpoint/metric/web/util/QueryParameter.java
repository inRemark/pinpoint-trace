/*
 * Copyright 2022 NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.navercorp.pinpoint.metric.web.util;

import com.navercorp.pinpoint.common.timeseries.time.Range;
import com.navercorp.pinpoint.common.timeseries.window.TimePrecision;

public abstract class QueryParameter {
    protected static final int TAG_SET_COUNT = 10;

    protected final Range range;
    protected final TimePrecision timePrecision;
    protected final int limit;

    protected QueryParameter(Range range, TimePrecision timePrecision, int limit) {
        this.range = range;
        this.timePrecision = timePrecision;
        this.limit = limit;
    }

    public Range getRange() {
        return range;
    }

    public TimePrecision getTimePrecision() {
        return timePrecision;
    }

    public int getLimit() {
        return limit;
    }

    public static abstract class Builder<S extends Builder<S>> {
        protected Range range;
        protected TimePrecision timePrecision;
        protected long timeSize = 10000;
        protected int limit;

        protected abstract S self();

        public S setRange(Range range) {
            this.range = range;
            return self();
        }

        public S setTimePrecision(TimePrecision timePrecision) {
            this.timePrecision = timePrecision;
            return self();
        }

        public S setTimeSize(long timeSize) {
            this.timeSize = timeSize;
            return self();
        }

        public int estimateLimit() {
            return Math.toIntExact((range.getRange() / timePrecision.getInterval() + 1) * TAG_SET_COUNT);
        }

        public Range getRange() {
            return this.range;
        }

        public TimePrecision getTimePrecision() {
            return this.timePrecision;
        }

        public int getLimit() {
            return this.limit;
        }

        abstract public QueryParameter build();
    }
}
