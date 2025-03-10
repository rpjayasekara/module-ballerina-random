/*
 * Copyright (c) 2021, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.ballerinalang.stdlib.random.nativeimpl;

import io.ballerina.runtime.api.creators.ErrorCreator;
import io.ballerina.runtime.api.utils.StringUtils;
import io.ballerina.runtime.api.values.BError;

import java.util.concurrent.ThreadLocalRandom;

/**
 * External functions for ballerina/random library.
 *
 * @since 1.1.0
 */
public class ExternMethods {

    private ExternMethods() {}

    public static Object randomInRange(long start, long end) {
        try {
            return ThreadLocalRandom.current().nextLong(start, end);
        } catch (IllegalArgumentException ex) {
            return createRandomError(Constant.ILLEGAL_ARGUMENT_ERROR_MSG);
        }
    }

    private static BError createRandomError(String errMsg) {
        return ErrorCreator.createError(ModuleUtils.getModule(), Constant.RANDOM_ERROR, StringUtils.fromString(errMsg),
                null, null);
    }
}
