/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.esec.gui;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TextLabel
extends JPanel {
    private static final long serialVersionUID = 1L;
    public static final int DEFAULT_COLUMN_SIZE = 60;
    private GridLayout thisLayout;
    private int columnSize = 60;
    private String msg;

    public TextLabel(String msg, int columnSize) {
        super(new GridLayout());
        this.thisLayout = (GridLayout)super.getLayout();
        this.columnSize = columnSize;
        this.setText(msg);
    }

    public TextLabel(String msg) {
        this(msg, 60);
    }

    public TextLabel() {
        super(new GridLayout());
        this.thisLayout = (GridLayout)super.getLayout();
    }

    public void setText(String msg) {
        Vector<String> v = new Vector<String>();
        int i = 0;
        while (msg.length() > i + this.columnSize) {
            int j = msg.lastIndexOf(32, i + this.columnSize);
            if (j < i) {
                j = i + this.columnSize;
            }
            v.add(msg.substring(i, j));
            i = j + 1;
        }
        if (i == 0) {
            v.add(msg);
        } else {
            v.add(msg.substring(i, msg.length()));
        }
        this.thisLayout.setRows(v.size());
        for (i = 0; i < v.size(); ++i) {
            this.add(new JLabel((String)v.get(i)));
        }
        this.msg = msg;
    }

    public String getText() {
        return this.msg;
    }

    public void setColumns(int columns) {
        this.columnSize = columns;
    }

    public int getColumns() {
        return this.columnSize;
    }
}

