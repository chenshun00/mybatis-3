/**
 *    Copyright 2009-2018 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.submitted.bringrags;

import java.util.ArrayList;
import java.util.List;

public class SimpleObject {

    private String id;
    private List<SimpleChildObject> simpleChildObjects;

    public SimpleObject(String id) {
        this.id = id;
        this.simpleChildObjects = new ArrayList<SimpleChildObject>();
    }

    public String getId() {
        return id;
    }

    public List<SimpleChildObject> getSimpleChildObjects() {
        return simpleChildObjects;
    }

}
