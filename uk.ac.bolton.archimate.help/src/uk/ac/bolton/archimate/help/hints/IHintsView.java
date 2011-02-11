/*******************************************************************************
 * Copyright (c) 2010 Bolton University, UK.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 *******************************************************************************/
package uk.ac.bolton.archimate.help.hints;

import org.eclipse.ui.IViewPart;

import uk.ac.bolton.archimate.help.ArchimateEditorHelpPlugin;

/**
 * Interface for Hints View
 * 
 * @author Phillip Beauvoir
 */
public interface IHintsView extends IViewPart {

    String ID = ArchimateEditorHelpPlugin.PLUGIN_ID + ".hintsView"; //$NON-NLS-1$
    String HELP_ID = ArchimateEditorHelpPlugin.PLUGIN_ID + ".hintsViewHelp"; //$NON-NLS-1$
    String NAME = "Hints";
    
    String EXTENSION_POINT_ID = "uk.ac.bolton.archimate.help.hints";
}