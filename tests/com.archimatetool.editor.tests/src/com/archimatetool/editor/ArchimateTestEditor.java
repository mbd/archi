/**
 * This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 */
package com.archimatetool.editor;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.archimatetool.editor.diagram.editparts.ArchimateDiagramEditPartFactory;
import com.archimatetool.model.IArchimateDiagramModel;
import com.archimatetool.model.IDiagramModelObject;


/**
 * A very basic Archimate Editor for testing consisting of a basic GraphicalViewer and Root Edit Part
 * with no dependency on the Workbench, Edit Parts and so on
 * 
 * @author Phillip Beauvoir
 */
@SuppressWarnings("nls")
public class ArchimateTestEditor {
    
    private GraphicalViewer graphicalViewer;
    private IArchimateDiagramModel dm;
    
    public ArchimateTestEditor() {
        // Actually, we do need this in cases where Display.getDefault() has not yet been called yet.
        // Creating a new Shell() at some point in the sequence of tests will call Display.getDefault() but let's be explicit here.
        if(Display.getCurrent() == null) {
            Display.getDefault();
        }
        
        graphicalViewer = new ScrollingGraphicalViewer();
        graphicalViewer.createControl(new Shell());
        
        graphicalViewer.setEditPartFactory(new ArchimateDiagramEditPartFactory());
        graphicalViewer.setRootEditPart(new ScalableFreeformRootEditPart());
    }

    public void setDiagramModel(IArchimateDiagramModel dm) {
        this.dm = dm;
        graphicalViewer.setContents(dm);
    }
    
    public IArchimateDiagramModel getDiagramModel() {
        return dm;
    }
    
    public GraphicalViewer getGraphicalViewer() {
        return graphicalViewer;
    }
    
    /**
     * Set the desired bounds of the Viewer *after* setting the model
     */
    public void setBounds(int x, int y, int width, int height) {
        if(dm == null) {
            throw new RuntimeException("Set bounds *after* calling setDiagramModel(IArchimateDiagramModel)");
        }
        
        graphicalViewer.getControl().setBounds(x, y, width, height);
    }
    
    /**
     * Flush and lay out all pending updates to the Viewer
     */
    public void layoutPendingUpdates() {
        graphicalViewer.flush();
    }
    
    /**
     * Find a GraphicalEditPart in the Editor's Viewer from the model object
     * @param modelObject The diagram model object
     * @return The editpart or null if not found
     */
    public GraphicalEditPart findEditPart(IDiagramModelObject modelObject) {
        return (GraphicalEditPart)graphicalViewer.getEditPartRegistry().get(modelObject);
    }

    /**
     * Find a Figure in an EditPart in the Editor's Viewer from the model object
     * @param modelObject The diagram model object
     * @return The figure or null if not found
     */
    public IFigure findFigure(IDiagramModelObject modelObject) {
        GraphicalEditPart editPart = findEditPart(modelObject);
        return editPart == null ? null : editPart.getFigure();
    }

}
