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
package top.huzhurong.cmdb.bean.query;

import lombok.Getter;
import lombok.Setter;

/**
*
*@author chenshun
*@since 2018-08-06 09:53:58
*/
public abstract class BaseQuery {

    @Getter
    private Integer pageSize;

    public void setPageSize(Integer pageSize) {
        if (pageSize <= 0) {
            pageSize = 20;
        }
        this.pageSize = pageSize;
        if (this.pageNo != null) {
            this.startRow = (this.pageNo - 1) * this.pageSize;
        }
    }

    @Getter
    private Integer pageNo;

    /**
    * 偏移量 =  pageNo * pageSize
    * limit 20 , 5  =  pageNo * pageSize  = 1 * 20
    */
    @Getter
    @Setter
    private Integer startRow;

    public void setPageNo(Integer pageNo) {
        if (pageNo < 0) {
            pageNo = 1;
        }
        this.pageNo = pageNo;
        if (this.pageSize != null) {
            this.startRow = (this.pageNo - 1) * this.pageSize;
        }
    }
}
