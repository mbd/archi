/**
 * This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 */
package com.archimatetool.editor.diagram.figures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.junit.Before;
import org.junit.Test;


public abstract class AbstractContainerFigureTests extends AbstractDiagramModelObjectFigureTests {
    
    protected AbstractContainerFigure containerFigure;
    
    @Before
    public void runBeforeEachTest() {
        containerFigure = (AbstractContainerFigure)abstractFigure;
    }

    
    @Test
    public void testGetMainFigure() {
        assertNotNull(containerFigure.getMainFigure());
    }
    
    @Test
    public void testGetContentPane() {
        assertNotNull(containerFigure.getContentPane());
    }
    
    @Test
    public void testTranslateMousePointToRelative() {
        containerFigure.setLocation(new Point(100, 100));
        
        Point pt = new Point(300, 400);
        containerFigure.translateMousePointToRelative(pt);
        assertEquals(new Point(200, 300), pt);
    }
    
    @Test
    public void testUseLocalCoordinates() {
        assertTrue(containerFigure.useLocalCoordinates());
    }

    @Test
    public void testGetPreferredSize() {
        Dimension d = containerFigure.getPreferredSize();
        assertEquals(containerFigure.getDefaultSize(), d);
        
        IFigure childFigure1 = new Figure();
        childFigure1.setBounds(new Rectangle(0, 0, 200, 200));
        containerFigure.getContentPane().add(childFigure1);
        
        d = containerFigure.getPreferredSize();
        assertEquals(new Dimension(210, 210), d);

        IFigure childFigure2 = new Figure();
        childFigure2.setBounds(new Rectangle(-12, -12, 300, 256));
        containerFigure.getContentPane().add(childFigure2);

        d = containerFigure.getPreferredSize();
        assertEquals(new Dimension(298, 254), d);

        IFigure childFigure3 = new Figure();
        childFigure3.setBounds(new Rectangle(20, 20, 300, 256));
        containerFigure.getContentPane().add(childFigure3);

        d = containerFigure.getPreferredSize();
        assertEquals(new Dimension(330, 286), d);
    }
}
