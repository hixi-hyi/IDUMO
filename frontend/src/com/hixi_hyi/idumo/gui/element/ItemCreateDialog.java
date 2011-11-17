package com.hixi_hyi.idumo.gui.element;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

import com.hixi_hyi.idumo.gui.IdumoWindow;
import com.hixi_hyi.idumo.gui.data.IdumoContainer;
import com.hixi_hyi.idumo.gui.data.IdumoItem;

public class ItemCreateDialog implements ActionListener {
	
	private IdumoContainer	container;
	private IdumoWindow		win;
	private JDialog			dialog;
	
	private JTextField		classField;
	private JTextField		nameField;
	private JTextField		optionField;
	private JTextField		initField;
	private JButton			btn;
	
	public ItemCreateDialog(IdumoWindow win, IdumoContainer container) {
		this.win = win;
		this.container = container;
		dialog = new JDialog(win.frame);
		dialog.setTitle("Add new item");
		dialog.setLayout(new GridLayout(5, 0));
		dialog.getContentPane().add(classField = new JTextField("class", 40));
		dialog.getContentPane().add(nameField = new JTextField("name", 40));
		dialog.getContentPane().add(optionField = new JTextField("option", 40));
		dialog.getContentPane().add(initField = new JTextField("init", 40));
		dialog.getContentPane().add(btn = new JButton("OK"));
		btn.addActionListener(this);
		dialog.pack();
		dialog.setVisible(false);
	}
	
	public void open() {
		dialog.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn) {
			dialog.setVisible(false);
			String klass = classField.getText();
			String name = nameField.getText();
			if ((klass != null) && (name != null) && !klass.equals("") && !name.equals("")) {
				IdumoItem item = new IdumoItem(klass, name, 100, 100);
				item.addOption(optionField.getText());
				item.addInit(initField.getText());
				container.addItem(item);
				win.repaint();
			}
		}
	}
	
}
