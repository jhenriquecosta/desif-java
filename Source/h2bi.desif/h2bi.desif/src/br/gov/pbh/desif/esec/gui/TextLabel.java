

package br.gov.pbh.desif.esec.gui;

import java.awt.GridLayout;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TextLabel extends JPanel
{

    private static final long serialVersionUID = 1L;
    public static final int DEFAULT_COLUMN_SIZE = 60;
    private GridLayout thisLayout;
    private int columnSize;
    private String msg;

    public TextLabel(String msg, int columnSize)
    {
        super(new GridLayout());
        this.columnSize = 60;
        thisLayout = (GridLayout)super.getLayout();
        this.columnSize = columnSize;
        setText(msg);
    }

    public TextLabel(String msg)
    {
        this(msg, 60);
    }

    public TextLabel()
    {
        super(new GridLayout());
        columnSize = 60;
        thisLayout = (GridLayout)super.getLayout();
    }

    public void setText(String msg)
    {
        Vector v = new Vector();
        int i;
        int j;
        for(i = 0; msg.length() > i + columnSize; i = j + 1)
        {
            j = msg.lastIndexOf(' ', i + columnSize);
            if(j < i)
                j = i + columnSize;
            v.add(msg.substring(i, j));
        }

        if(i == 0)
            v.add(msg);
        else
            v.add(msg.substring(i, msg.length()));
        thisLayout.setRows(v.size());
        for(i = 0; i < v.size(); i++)
            add(new JLabel((String)v.get(i)));

        this.msg = msg;
    }

    public String getText()
    {
        return msg;
    }

    public void setColumns(int columns)
    {
        columnSize = columns;
    }

    public int getColumns()
    {
        return columnSize;
    }
}