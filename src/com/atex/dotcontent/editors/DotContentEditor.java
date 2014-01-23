package com.atex.dotcontent.editors;

import org.eclipse.ui.editors.text.TextEditor;

public class DotContentEditor extends TextEditor {

    private ColorManager colorManager;

    public DotContentEditor() {
        super();
        colorManager = new ColorManager();
        setSourceViewerConfiguration(new Configuration(colorManager));
        setDocumentProvider(new DocumentProvider());
    }
    public void dispose() {
        colorManager.dispose();
        super.dispose();
    }

}
