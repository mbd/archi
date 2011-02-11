/*******************************************************************************
 * Copyright (c) 2010 Bolton University, UK.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 *******************************************************************************/
package uk.ac.bolton.archimate.editor.diagram.commands;

import uk.ac.bolton.archimate.editor.model.commands.EObjectFeatureCommand;
import uk.ac.bolton.archimate.model.IArchimatePackage;
import uk.ac.bolton.archimate.model.IDiagramModelConnection;


/**
 * Connection Line Width Command
 *
 * @author Phillip Beauvoir
 */
public class ConnectionLineWidthCommand extends EObjectFeatureCommand {
    
    public ConnectionLineWidthCommand(IDiagramModelConnection connection, int lineWidth) {
        super("Connection line width", connection, IArchimatePackage.Literals.DIAGRAM_MODEL_CONNECTION__LINE_WIDTH, lineWidth);
    }
    
    @Override
    public boolean canExecute() {
        return fNewValue != fOldValue;
    }
    
}