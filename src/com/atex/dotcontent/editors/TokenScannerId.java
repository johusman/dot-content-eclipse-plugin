package com.atex.dotcontent.editors;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.*;

public class TokenScannerId extends TokenScanner {
    public TokenScannerId(ColorManager manager) {
        IRule idRule = new WordRule(new IWordDetector() {
            public boolean isWordStart(char c) {
                return c != ':';
            }

            public boolean isWordPart(char c) {
                return true;
            }
        }, new Token(new TextAttribute(manager.getColor(ColorConstants.ID))));

        IRule[] rules = new IRule[] {
            getKeywordRule(manager),
            getSeparatorRule(manager),
            idRule,
            getWhitespaceRule()
        };

        setRules(rules);
    }
}
