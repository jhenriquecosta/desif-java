/*
 * 	Copyright (c) 2010, Custom Swing Components
 * 	All rights reserved.
 * 
 * 	Redistribution and use in source and binary forms, with or without
 * 	modification, are permitted provided that the following conditions are met:
 * 		* Redistributions of source code must retain the above copyright
 *   	  notice, this list of conditions and the following disclaimer.
 * 		* Redistributions in binary form must reproduce the above copyright
 *   	  notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
 * 		* Neither the name of Custom Swing Components nor the
 *        names of its contributors may be used to endorse or promote products
 *        derived from this software without specific prior written permission.
 *      * Redistributions must also adhere to the terms and conditions of the 
 *        specific distribution licenses purchased from Custom Swing Components. 
 * 	      (be it developer, enterprise or source code). 
 * 
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 *  ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL CUSTOM SWING COMPONENTS BE LIABLE FOR ANY
 *  DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * Additional Terms And Conditions.
 *
 * 1. Conditions
 * The purchase of a license for a component from Custom Swing Components entitles you to the following: 
 *   1.1. You may use the component on as many projects as you desire. There are no restrictions regarding the number of deployments, and you may bundle the component as part of your software, be it commercial or personal.
 *   1.2. Once you have purchased a component, there is no limit on how many times you may download the Java archive or its supporting documentation.
 *   1.3. 3 months free email support for the purchased component. Additional support will require the purchase of an extended support contract.
 *   1.4. Receive bug fixes and updates for the version of the component purchased. Note that this only includes increments of the component's minor version. To illustrate this, if you purchase version 1.0 of a specific component, you are entitled to all future minor updates (i.e. 1.1, 1.2 ... 1.n).
 *   1.5. In the event that a major version is released within 3 months of you purchasing the previous version, you will be automatically entitled to the new version. To illustrate this, if you purchase version 1.0 of a specific component, you are entitled to version 2.0 for free, if version 2.0 is released within 3 months of your purchase of version 1.0.
 *
 * 2. Restrictions
 * Restrictions apply to all types of licenses:
 *   2.1. You may not directly resell licensed component to other individuals or entities. To illustrate this, you may not sell the Java archive to third parties. Please note that this does not restrict you from including the component in commercial software; it prevents you directly selling the archive to other third parties.
 *   2.2. If the deployment of your software directly exposes the API of the component to third party developers, there may be an additional deployment fee. To illustrate this, if you sell a product whose primary target is developers, they will gain access to the licensed component and be able to use it in their own software. Please note that this does not restrict you from including the component in commercial software; it is intended to prevent other third party developers from making use of components that they have not purchased.
 *   2.3. A license may not be automatically transferred. An enterprise license is granted to a named enterprise and may not be transferred to another enterprise. A developer license is granted to a named developer and may not be transferred to another developer. Custom Swing Components does not support a floating license. Please contact us directly if you need to transfer a license.
 *
 * 3. License Types
 * At present there are 3 types of licenses available: developer, enterprise and source code.
 *   3.1. A developer license entitles a single named developer to make use of the licensed components.
 *   3.2. An enterprise license entitles all developers within the enterprise to make use of the licensed components.
 *   3.3. A source code license is available and applies on an enterprise scale; please contact us for more details.
 *
 * If you have additional questions regarding the licensing, please feel free to contact us directly.
 */
 


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;

import com.javaswingcomponents.accordion.JSCAccordion;
import com.javaswingcomponents.accordion.TabOrientation;
import com.javaswingcomponents.accordion.listener.AccordionEvent;
import com.javaswingcomponents.accordion.listener.AccordionListener;
import com.javaswingcomponents.accordion.plaf.AccordionUI;
import com.javaswingcomponents.accordion.plaf.basic.BasicHorizontalTabRenderer;
import com.javaswingcomponents.accordion.plaf.darksteel.DarkSteelAccordionUI;
import com.javaswingcomponents.framework.painters.configurationbound.GradientColorPainter;

public class CodeExample extends JPanel{
static JFrame frame;

	/**
	 * This is the correct way to launch a swing application.
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() { 
			@Override
			public void run() {
				CodeExample codeExample = new CodeExample();
				frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Container panel = frame.getContentPane();
				panel.setLayout(new BorderLayout());
				panel.add(codeExample, BorderLayout.CENTER);
				frame.pack();
				frame.setSize(500,300);
				frame.setVisible(true);
			}
		});
	}
	
	public CodeExample() {
		JSCAccordion accordion = new JSCAccordion();
		howToAddTabs(accordion);
		howToListenForChanges(accordion);
		howToChangeTabOrientation(accordion);
		howToChangeTheLookAndFeel(accordion);
		howToCustomizeALookAndFeel(accordion);
		setLayout(new GridLayout(1,1,30,30));
		add(accordion);
	}

	/**
	 * When adding a tab to the accordion, you must supply text for the tab
	 * as well as a component that will be used as the content contained for tab.
	 * The example below will add five tabs
	 * The first tab will contain the text "Tab 1" and a JButton
	 * The second tab will contain the text "Tab 2" and a JLabel
	 * The third tab will contain the text "Tab 3" and a JTree wrapped in a JScrollpane
	 * The fourth tab will contain the text "Tab 4" and an empty JPanel, with opaque = true
	 * The fifth tab will contain the text "Tab 5" and an empty JPanel with opaque = false
	 * 
	 * The key thing to note is the effect of adding an opaque or non opaque component to
	 * the accordion.
	 * @param accordion
	 */
	private void howToAddTabs(JSCAccordion accordion) {
		JPanel transparentPanel = new JPanel();
		transparentPanel.setOpaque(false);
		transparentPanel.setBackground(Color.GRAY);
		
		JPanel opaquePanel = new JPanel();
		opaquePanel.setOpaque(true);
		opaquePanel.setBackground(Color.GRAY);
		
		accordion.addTab("Tab 1", new JButton("Button"));
		accordion.addTab("Tab 2", new JLabel("Label"));
		accordion.addTab("Tab 3", new JScrollPane(new JTree()));
		accordion.addTab("Tab 4", opaquePanel);
		accordion.addTab("Tab 5", transparentPanel);
	}
	
	/**
	 * It can be useful to be notified when changes occur on the accordion. 
	 * The accordion can notify a listener when a tab is added, selected or removed.
	 * @param accordion
	 */
	private void howToListenForChanges(JSCAccordion accordion) {
		accordion.addAccordionListener(new AccordionListener() {
			
			@Override
			public void accordionChanged(AccordionEvent accordionEvent) {
				//available fields on accordionEvent.
				
				switch (accordionEvent.getEventType()) {
				case TAB_ADDED: {
					//add your logic here to react to a tab being added.
					break;
				}
				case TAB_REMOVED: {
					//add your logic here to react to a tab being removed.
					break;					
				}
				case TAB_SELECTED: {
					//add your logic here to react to a tab being selected.
					break;					
				}
				}
			}
		});
	}
	
	/**
	 * You can change the tab orientation to slide either vertically or horizontally.
	 * @param accordion
	 */
	private void howToChangeTabOrientation(JSCAccordion accordion) {
		//will make the accordion slide from top to bottom
		accordion.setTabOrientation(TabOrientation.VERTICAL);
		
		//will make the accordion slide from left ro right
		//accordion.setTabOrientation(TabOrientation.HORITZONTAL);
	}
	
	/**
	 * You can change the look and feel of the component by changing its ui.
	 * In this example we will change the UI to the DarkSteelUI
	 * @param accordion
	 */
	private void howToChangeTheLookAndFeel(JSCAccordion accordion) {
		//We create a new instance of the UI
		AccordionUI newUI = DarkSteelAccordionUI.createUI(accordion);
		//We set the UI
		accordion.setUI(newUI);
	}
	
	/**
	 * The easiest way to customize a AccordionUI is to change the 
	 * default Background Painter, AccordionTabRenderers or tweak values
	 * on the currently installed Background Painter, AccordionTabRenderers and UI.
	 * @param accordion
	 */
	private void howToCustomizeALookAndFeel(JSCAccordion accordion) {
		//example of changing a value on the ui.
		DarkSteelAccordionUI ui = (DarkSteelAccordionUI) accordion.getUI();
		ui.setHorizontalBackgroundPadding(10);
		
		//example of changing the AccordionTabRenderer
		BasicHorizontalTabRenderer tabRenderer = new BasicHorizontalTabRenderer(accordion);
                tabRenderer.setFontColor(Color.RED);
		accordion.setHorizontalAccordionTabRenderer(tabRenderer);
                
                
		
		//example of changing the background painter.
		GradientColorPainter backgroundPainter = (GradientColorPainter) accordion.getBackgroundPainter();
		backgroundPainter = (GradientColorPainter) accordion.getBackgroundPainter();
		backgroundPainter.setStartColor(Color.BLACK);
		backgroundPainter.setEndColor(Color.WHITE);
		
		//the outcome of this customization is not the most visually appealing result
		//but it just serves to illustrate how to customize the accordion's look and feel.
		//The UI is darkSteel.
		//The backgroundPainter is a gradient running from Black to White
		//The accordionTabRenderer belongs to the BasicAccordionUI
		//And finally the text of the tab is red!
	}
}
