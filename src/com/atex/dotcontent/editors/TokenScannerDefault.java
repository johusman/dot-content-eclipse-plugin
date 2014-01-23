package com.atex.dotcontent.editors;

import org.eclipse.jface.text.rules.*;

public class TokenScannerDefault extends TokenScanner {
    public TokenScannerDefault(ColorManager manager) {
        IRule[] rules = new IRule[] {
            getKeywordRule(manager),
            getSeparatorRule(manager),
            getWhitespaceRule()
        };

        setRules(rules);
    }


}
