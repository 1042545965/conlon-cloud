/**
 * Copyright 2019 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package conlon.cloud.common.utils.serializer;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

/**
 * @Author conlon
 * @Description 序列化
 * @Date 2021/3/29 11:30
 **/
public class ProtoBufSerializer {

    public static <T> byte[] serialize(T object) {
        Schema<T> schema = (Schema<T>) RuntimeSchema.getSchema(object.getClass());
        return ProtobufIOUtil.toByteArray(object, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
    }

    public static  <T> T deSerialize(byte[] bytes, Class<T> clazz) {
        RuntimeSchema<T> runtimeSchema = RuntimeSchema.createFrom(clazz);
        T object = runtimeSchema.newMessage();
        ProtobufIOUtil.mergeFrom(bytes, object, runtimeSchema);
        return object;
    }
}
