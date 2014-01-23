package com.atex.dotcontent.editors;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.*;
import org.eclipse.swt.SWT;

public class TokenScanner extends RuleBasedScanner {
    protected IRule getKeywordRule(ColorManager manager) {
        IToken keyword = new Token(new TextAttribute(manager.getColor(ColorConstants.OP), null, SWT.BOLD));

        WordRule wordRule = new WordRule(new IWordDetector() {
            public boolean isWordStart(char c) {
                return Character.isLetter(c);
            }

            public boolean isWordPart(char c) {
                return Character.isLetter(c);
            }
        }, keyword);

        wordRule.setColumnConstraint(0);

        return wordRule;
    }

    protected IRule getWhitespaceRule() {
        return new WhitespaceRule(new IWhitespaceDetector() {
            public boolean isWhitespace(char c) {
                return Character.isWhitespace(c);
            }
        });
    }

    protected IRule getSeparatorRule(ColorManager manager) {
        IRule separatorRule = new WordRule(new IWordDetector() {
            public boolean isWordStart(char c) {
                return c == ':';
            }

            public boolean isWordPart(char c) {
                return c == ':';
            }
        }, new Token(new TextAttribute(manager.getColor(ColorConstants.SEPARATOR), manager.getColor(ColorConstants.SEPARATOR_BG), SWT.BOLD)));
        return separatorRule;
    }
}
