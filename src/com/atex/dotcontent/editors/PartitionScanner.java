package com.atex.dotcontent.editors;

import org.eclipse.jface.text.rules.*;

public class PartitionScanner extends RuleBasedPartitionScanner {
    public final static String CONTENT_COMMENT = "__content_comment";
    public final static String CONTENT_OP_VALUE = "__content_op_value";
    public final static String CONTENT_OP_ID = "__content_op_id";
    public final static String CONTENT_OP_OTHER = "__content_op_other";

    public PartitionScanner() {

        IToken comment = new Token(CONTENT_COMMENT);
        IToken op_value = new Token(CONTENT_OP_VALUE);
        IToken op_id    = new Token(CONTENT_OP_ID);
        IToken op_other = new Token(CONTENT_OP_OTHER);

        IPredicateRule[] rules = new IPredicateRule[] {
                new EndOfLineRule("#", comment, '\\'),
                new EndOfLineRule("id:", op_id),
                new EndOfLineRule("major:", op_value),
                new EndOfLineRule("inputtemplate:", op_id),
                new EndOfLineRule("name:", op_value),
                new EndOfLineRule("securityparent:", op_id),
                new EndOfLineRule("component:", op_other),
                new EndOfLineRule("ref:", op_other),
                new EndOfLineRule("file:", op_other),
                new EndOfLineRule("list:", op_other),
                new EndOfLineRule("publish:", op_other),
                new EndOfLineRule("template:", op_id),
                new EndOfLineRule("action:", op_value),
        };

        setPredicateRules(rules);
    }
}
