

package com.erv.fungsi;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;




abstract class CommonDialog extends JDialog {
	private JComponent contents;
	private String closeString = "Close";
	private JFrame parent;
	private JPanel mainPanel;
	private JButton button;
	private ActionListener listener;
	private JComponent buttonPanel;
	
	CommonDialog (JFrame parent, String title ) {
		super( parent, title, true );
		this.parent = parent;
//		setSize( 600, 400 );
		init();
	}	

	abstract JComponent getContents();
	abstract JComponent getButtonPanel();

	void centerDialogOnParent() {
		if ( parent != null ) {
			Dimension pSize = parent.getSize();
			Dimension mySize = getSize();
			Point loc = ( parent.getLocation() );
//			Point loc = new Point();
			loc.x = loc.x +  ( pSize.width - mySize.width ) /2;
		    loc.y =  loc.y + ( pSize.height - mySize.height ) /2;	
			setLocation( loc );
		}	

	}	


	
	public void setVisible( boolean isVisible ) {
		if ( contents == null ) {
			contents = getContents();	
			mainPanel.add( contents, BorderLayout.CENTER );
            validate();
		}
		if ( buttonPanel == null ) {
			buttonPanel = getButtonPanel();
			mainPanel.add( buttonPanel, BorderLayout.SOUTH );
            validate();
		}
        //Dimension size = getSize();
        //pack();
        //setSize( size );    
		centerDialogOnParent();
		super.setVisible( isVisible );		
	}	
	
	private void init() {
	//	setResizable( false );
		getContentPane().setLayout( new BorderLayout() );
		mainPanel = new JPanel( new BorderLayout() );
		getContentPane().add( mainPanel, BorderLayout.CENTER );
	}	

	public void dispose() {
//		buttonPanel.dispose();
		super.dispose();
	}	




}	
