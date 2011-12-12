import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Davor
 */
public class DictionaryTableModel extends AbstractTableModel
{
    List<String> list = new LinkedList<String>();

    public int getRowCount()
    {
        return list.size();
    }

    public int getColumnCount()
    {
        return 1;
    }

    public String getValueAt(int rowIndex, int columnIndex)
    {
        return list.get(rowIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return String.class;
    }

    public void appendValue(String value)
    {
        list.add(value);
        fireTableDataChanged();
    }

    public void removeAll()
    {
        list.clear();
    }

    @Override
    public String getColumnName(int column)
    {
        return null;
    }
}
