package com.atex.dotcontent.editors;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.ui.editors.text.FileDocumentProvider;

public class DocumentProvider extends FileDocumentProvider {

    protected IDocument createDocument(Object element) throws CoreException {
        IDocument document = super.createDocument(element);
        if (document != null) {
            IDocumentPartitioner partitioner =
                new FastPartitioner(
                        new PartitionScanner(),
                        new String[] {
                            PartitionScanner.CONTENT_COMMENT,
                            PartitionScanner.CONTENT_OP_ID,
                            PartitionScanner.CONTENT_OP_VALUE,
                            PartitionScanner.CONTENT_OP_OTHER
                        });
            partitioner.connect(document);
            document.setDocumentPartitioner(partitioner);
        }
        return document;
    }
}