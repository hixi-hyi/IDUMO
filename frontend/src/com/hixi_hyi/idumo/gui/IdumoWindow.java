package com.hixi_hyi.idumo.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import com.hixi_hyi.idumo.gui.data.IdumoContainer;
import com.hixi_hyi.idumo.gui.data.IdumoItem;
import com.hixi_hyi.idumo.gui.element.ExportDialog;
import com.hixi_hyi.idumo.gui.element.ItemCreateDialog;

public class IdumoWindow {

	private JFrame				frame;
	private IdumoCanvas			canvas;
	private IdumoContainer		container;
	private ItemCreateDialog	itemDialog;
	private ExportDialog		exportDialog;

	public IdumoWindow(IdumoContainer container) {
		this.container = container;
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				makeGUI();
			}
		});
	}

	public void makeGUI() {
		frame = new JFrame();
		frame.setTitle("IDUMO");
		frame.setSize(1024, 768);
		frame.setJMenuBar(getMenuBar());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canvas = new IdumoCanvas(this, container);
		itemDialog = new ItemCreateDialog(this, container);
		exportDialog = new ExportDialog(this, container);
		frame.getContentPane().add(canvas);
		frame.setVisible(true);
		canvas.repaint();
	}

	public JMenuItem createJMenuItem(String label, ActionListener listner) {
		JMenuItem item = new JMenuItem(label);
		item.addActionListener(listner);
		return item;
	}

	public JMenuItem getJMenuItem(String label, boolean flag) {
		JMenuItem item = new JMenuItem(label);
		item.setEnabled(flag);
		return item;
	}

	public JMenuBar getMenuBar() {
		JMenuBar bar = new JMenuBar();
		{
			JMenu menu = new JMenu("File");
			menu.add(getJMenuItem("Import", false));
			menu.add(createJMenuItem("Export", new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					exportDialog.open();
				}
			}));
			menu.addSeparator();
			menu.add(createJMenuItem("Quit", new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}
			}));
			bar.add(menu);
		}
		{
			JMenu menu = new JMenu("Edit");
			menu.add(createJMenuItem("Add Item", new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					itemDialog.open();
				}
			}));
			menu.add(createJMenuItem("Delete", new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					IdumoItem selectedItem = canvas.selectedItem();
					container.removeItem(selectedItem);
					container.removeEdge(selectedItem);
					canvas.repaint();
				}
			}));
			menu.add(getJMenuItem("Copy", false));
			menu.add(getJMenuItem("Container", false));
			bar.add(menu);
		}
		return bar;
	}

	public void repaint() {
		frame.repaint();
	}

	public static void main(String args[]) {
		IdumoContainer container = new IdumoContainer();
		new IdumoWindow(container);
	}

	/**
	 * @return frame
	 */
	public JFrame getFrame() {
		return frame;
	}

}