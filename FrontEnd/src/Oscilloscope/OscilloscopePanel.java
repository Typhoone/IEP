package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class OscilloscopePanel extends JPanel{

	private boolean channelOneOn = false;
	private boolean channelTwoOn = false;
	
	private OscilloscopeDisplay display;
	
    public OscilloscopePanel(){
    	Border border = LineBorder.createGrayLineBorder();
        setBorder(border);
        setLayout(new GridBagLayout());
        
        //add the oscilloscope display and buttons
        display = new OscilloscopeDisplay();
        
        addComp(this, display , 0, 0, 1, 1, 1, 0.9);
        addComp(this, getButtonPanel(), 1, 0, 1, 1, 1, 0.1);
    }
    
    public JPanel getButtonPanel(){
    	JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        JButton saveButton = new JButton("Save");	//screenshot
        saveButton.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e){
        		System.out.println("Save button pressed");
        		System.out.println("width: " + display.getWidth() + "\nheight: " + display.getHeight());
        		channelOneOn = false;
        		channelTwoOn = false;
        	}
        });
        
        JButton pause = new JButton("Pause");
        pause.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e){
        		System.out.println("Pause button pressed");
        	}
        });

        JPanel inner = new JPanel();
        inner.setLayout(new GridBagLayout());
        JComponent emptyBox = (JComponent) Box.createRigidArea(new Dimension(0,20));
        addComp(inner, pause, 0, 0, 1, 1, 1, 0.1);
        addComp(inner, emptyBox, 0, 1, 1, 1, 1, 0.1);
        addComp(inner, saveButton, 0, 2, 1, 1, 1, 0.1);
        addComp(inner, emptyBox, 0, 3, 1, 1, 1, 0.1);
        
        //inner.add(pause);
        //inner.add(Box.createRigidArea(new Dimension(0,20)));
        //inner.add(saveButton);
        //inner.add(Box.createRigidArea(new Dimension(0,20)));
        buttonPanel.add(inner, BorderLayout.SOUTH);
        return buttonPanel;
    }
    
    /**
     * This is a helper method which is only used by this class to add
     * components to a panel
     * @param panel The panel that the component is being added to
     * @param component The component to be added
     * @param x Specifies the GridBagConstraints.gridx
     * @param y Specifies the GridBagConstraints.gridy
     * @param width Specifies the GridBagConstraints.gridwidth
     * @param height Specifies the GridBagConstraints.gridheight
     * @param weightX specifies the GridBagConstraints.weightx
     * @param weightY specifies the GridBagConstraints.weighty
     */
    private void addComp(JPanel panel, JComponent component,
    		int x, int y, int width, int height, double weightX, double weightY){
    	GridBagConstraints gbc = new GridBagConstraints();
    	gbc.gridx = x;
    	gbc.gridy = y;
    	gbc.gridheight = height;
    	gbc.gridwidth = width;
    	gbc.fill = GridBagConstraints.BOTH;
    	gbc.weightx = weightX;
    	gbc.weighty = weightY;
    	panel.add(component, gbc);	
    }
    
}