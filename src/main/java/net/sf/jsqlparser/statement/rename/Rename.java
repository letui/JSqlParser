/*-
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2020 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
package net.sf.jsqlparser.statement.rename;

import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.StatementVisitor;

import java.util.ArrayList;
import java.util.List;

public class Rename implements Statement {

    private List<Changes> changes = new ArrayList<>();
    String type;

    public List<Changes> getChanges() {
        return changes;
    }

    public void setChanges(List<Changes> changes) {
        this.changes = changes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void accept(StatementVisitor statementVisitor) {
        statementVisitor.visit(this);

    }

    public void setRestTokens(List<String> tokens) {
        Changes temp = new Changes();
        for (String item : tokens) {
            if (item.equals("TO")||item.equals(",")||item.equals(";")) {
                continue;
            }
            if (temp.getOldName() == null) {
                temp.setOldName(item);
            }else if (temp.getNewName() == null) {
                temp.setNewName(item);
            }
            if (temp.getOldName() != null && temp.getNewName() != null) {
                this.changes.add(temp);
                temp = new Changes();
            }
        }
    }

    public List<Changes> getAllChanges() {
        return changes;
    }

    public Changes getBy(int index) {
        if (index < changes.size() && index > -1) {
            return changes.get(index);
        }
        throw new RuntimeException("param [index] is not valid");
    }

    public int getChangesSize() {
        return changes.size();
    }

    @Override
    public String toString() {
        return "RENAME TABLE" +changes.toString().substring(1,changes.toString().length()-2)+";";

    }
}
