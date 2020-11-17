/*-
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2019 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
package net.sf.jsqlparser.statement.rename;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.drop.Drop;
import org.junit.Test;

import java.io.StringReader;

import static net.sf.jsqlparser.test.TestUtils.*;
import static org.junit.Assert.assertEquals;

public class RenameTest {

    private final CCJSqlParserManager parserManager = new CCJSqlParserManager();

    @Test
    public void testRename() throws JSQLParserException {
        String statement = "RENAME TABLE mytab TO mytab2;";
        Rename parsed = (Rename) parserManager.parse(new StringReader(statement));
        assertEquals("TABLE", parsed.getType());
        assertEquals("mytab", parsed.getBy(0).getOldName());
        assertEquals("mytab2", parsed.getBy(0).getNewName());
        assertEquals(statement, "" + parsed);

    }

    @Test
    public void testRenameMulti() throws JSQLParserException {
        String statement = "RENAME TABLE old_table1 TO new_table1,\n" +
                "             old_table2 TO new_table2,\n" +
                "             old_table3 TO new_table3;";
        Rename parsed = (Rename) parserManager.parse(new StringReader(statement));
        assertEquals("TABLE", parsed.getType());
        assertEquals(3, parsed.getChangesSize());

    }

}
