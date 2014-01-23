package com.atex.dotcontent.editors;

import org.eclipse.jface.text.rules.*;

public class TokenScannerValue extends TokenScanner {
    public TokenScannerValue(ColorManager manager) {
        IRule[] rules = new IRule[] {
            getKeywordRule(manager),
            getSeparatorRule(manager),
            getWhitespaceRule()
        };

        setRules(rules);
    }
}
