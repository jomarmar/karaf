/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.karaf.main.lock;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import org.apache.karaf.util.properties.Properties;

import static org.junit.Assert.assertTrue;


@Ignore
public class PostgreSQLJDBCLockIntegrationTest extends BaseJDBCLockIntegrationTest {

    @Before
    @Override
    public void setUp() throws Exception {
        password = "secret";
        driver = "org.postgresql.Driver";
        url = "jdbc:postgresql://127.0.0.1:5432/test";
        
        super.setUp();
    }
    
    @Override
    PostgreSQLJDBCLock createLock(Properties props) {
        return new PostgreSQLJDBCLock(props);
    }
    
    @Test
    public void initShouldCreateTheDatabaseIfItNotExists() throws Exception {
        String database = "test" + System.currentTimeMillis();
        url = "jdbc:postgresql://127.0.0.1:5432/" + database;
        props.put("karaf.lock.jdbc.url", url);
        lock = createLock(props);
        lock.lock();
        
        assertTrue(lock.lockConnection.getMetaData().getURL().contains(database));
    }
}
