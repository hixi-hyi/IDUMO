package com.hixi_hyi.idumo.gui.element;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

import com.hixi_hyi.idumo.gui.IdumoWindow;
import com.hixi_hyi.idumo.gui.data.IdumoContainer;
import com.hixi_hyi.idumo.gui.data.IdumoEdge;
import com.hixi_hyi.idumo.gui.data.IdumoItem;

public class ExportDialog implements ActionListener {

	private IdumoContainer	container;

	private JDialog			dialog;

	private JTextField		typeField;
	private JTextField		nameField;
	private JButton			btn;

	public ExportDialog(IdumoWindow win, IdumoContainer container) {
		this.container = container;
		this.dialog = new JDialog(win.frame);
		this.dialog.setTitle("Add new item");
		this.dialog.setLayout(new GridLayout(3, 0));
		dialog.getContentPane().add(typeField = new JTextField("type", 40));
		dialog.getContentPane().add(nameField = new JTextField("name", 40));
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
			String type = typeField.getText();
			String name = nameField.getText();
			if ((name != null) && !name.equals("") && (type != null) && !type.equals("")) {
				output(System.out, name, type);
			}
		}
	}

	public void output(PrintStream out, String type, String name) {
		out.printf("<?xml version='1.0' encoding='UTF-8'>\n");
		out.printf("<idumo ver='1.0' type='%s' name='%s' package='com.hixi_hyi.idumo.android.auto.app'>\n", type, name);
		out.printf("<items>\n");
		for (IdumoItem item : container.getItems()) {
			out.printf("<item class='%s' name='%s' option='%s' init='%s'/>\n", item.getKlass(), item.getName(), item.getOption(), item.getInit());
		}
		out.printf("</items>\n");
		out.printf("<connections>\n");
		for (IdumoEdge edge : container.getEdges()) {
			out.printf("<connection src='%s' sink='%s'/>\n", edge.getSrcItem().getName(), edge.getDestItem().getName());
		}
		out.printf("</connections>\n");
		out.printf("<executions>\n");
		out.printf("<loop count = '-1'>\n");
		out.printf("<sleep time = '1000'>\n");
		out.printf("</executions>\n");
		out.printf("</idumo>\n");
	}

}
