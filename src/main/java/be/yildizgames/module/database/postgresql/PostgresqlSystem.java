/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2019 Grégory Van den Borre
 *
 *  More infos available: https://engine.yildiz-games.be
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 *  documentation files (the "Software"), to deal in the Software without restriction, including without
 *  limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 *  of the Software, and to permit persons to whom the Software is furnished to do so,
 *  subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all copies or substantial
 *  portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 *  WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 *  OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 *  DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 *
 */

package be.yildizgames.module.database.postgresql;

import be.yildizgames.module.database.BaseDatabaseSystem;
import be.yildizgames.module.database.DatabaseConnectionProviderFactory;
import be.yildizgames.module.database.DriverProvider;
import be.yildizgames.module.database.QueryBuilder;
import org.jooq.SQLDialect;
import org.postgresql.Driver;

/**
 * @author Grégory Van den Borre
 */
public class PostgresqlSystem extends BaseDatabaseSystem {

    public static final String KEY = "postgres";

    private final DriverProvider driverProvider = Driver::new;

    private PostgresqlSystem() {
        super("jdbc:postgresql://${1}:${2}/${0}");
    }

    public static void support() {
        DatabaseConnectionProviderFactory.getInstance().addSystem(KEY, new PostgresqlSystem());
    }

    @Override
    public SQLDialect getDialect() {
        return SQLDialect.POSTGRES;
    }

    @Override
    public String getDriver() {
        return "org.postgresql.Driver";
    }

    @Override
    public DriverProvider getDriverProvider() {
        return this.driverProvider;
    }

    @Override
    public QueryBuilder createBuilder() {
        return new PostgresqlQueryBuilder();
    }

    @Override
    public final boolean requirePool() {
        return true;
    }
}
