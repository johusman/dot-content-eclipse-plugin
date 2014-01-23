package com.atex.dotcontent.editors;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

public class Configuration extends SourceViewerConfiguration {
    private Map<String, ITokenScanner> tokenScanners;
    private ColorManager colorManager;

    public Configuration(ColorManager colorManager) {
        this.colorManager = colorManager;
    }

    public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
        return new String[] {
                IDocument.DEFAULT_CONTENT_TYPE,
                PartitionScanner.CONTENT_COMMENT,
                PartitionScanner.CONTENT_OP_ID,
                PartitionScanner.CONTENT_OP_VALUE,
                PartitionScanner.CONTENT_OP_OTHER
                };
    }

    protected ITokenScanner getTokenScanner(String type) {
        if (tokenScanners == null) {
            tokenScanners = new HashMap<String, ITokenScanner>();

            tokenScanners.put("id", new TokenScannerId(colorManager));
            tokenScanners.put("value", new TokenScannerValue(colorManager));
            tokenScanners.put("other", new TokenScannerDefault(colorManager));

            for (ITokenScanner tokenScanner : tokenScanners.values()) {
                ((RuleBasedScanner) tokenScanner).setDefaultReturnToken(new Token(new TextAttribute(
                    colorManager.getColor(ColorConstants.DEFAULT))));
            }
        }
        return tokenScanners.get(type);
    }

    public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
        PresentationReconciler reconciler = new PresentationReconciler();

        setUpDamagerRepairer(reconciler, IDocument.DEFAULT_CONTENT_TYPE, new TextAttribute(colorManager.getColor(ColorConstants.DEFAULT)));
        setUpDamagerRepairer(reconciler, PartitionScanner.CONTENT_OP_ID, getTokenScanner("id"));
        setUpDamagerRepairer(reconciler, PartitionScanner.CONTENT_OP_VALUE, getTokenScanner("value"));
        setUpDamagerRepairer(reconciler, PartitionScanner.CONTENT_OP_OTHER, getTokenScanner("other"));
        setUpDamagerRepairer(reconciler, PartitionScanner.CONTENT_COMMENT, new TextAttribute(colorManager.getColor(ColorConstants.COMMENT)));

        return reconciler;
    }

    private void setUpDamagerRepairer(PresentationReconciler reconciler, String token, ITokenScanner scanner) {
        DefaultDamagerRepairer dr = new DefaultDamagerRepairer(scanner);
        reconciler.setDamager(dr, token);
        reconciler.setRepairer(dr, token);
    }

    private void setUpDamagerRepairer(PresentationReconciler reconciler, String token, TextAttribute textAttribute) {
        NonRuleBasedDamagerRepairer ndr = new NonRuleBasedDamagerRepairer(textAttribute);
        reconciler.setDamager(ndr, token);
        reconciler.setRepairer(ndr, token);
    }

}