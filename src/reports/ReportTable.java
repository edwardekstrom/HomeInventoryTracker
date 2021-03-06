package reports;

import hit_exceptions.InvalidRowException;
import hit_exceptions.NullContainerException;

import java.util.ArrayList;
//import java.util.List;

public class ReportTable {
	private String _tableName;
	private String[] _headerRow;
	private ArrayList<TableRow> _rows;
	private int _numberOfColumns;
	
	public ReportTable(int numberOfColumns, String tableName){
		_numberOfColumns = numberOfColumns;
		_rows = new ArrayList<TableRow>();
		_tableName = tableName;
		setHeaderRow(new String[numberOfColumns]);
	}
	
	public ReportTable(String[] headerRow, String tableName){
		setHeaderRow(headerRow);
		_numberOfColumns = headerRow.length;
		_rows = new ArrayList<TableRow>();
		_tableName = tableName;
	}

	/**
	 * @return the _headerRow
	 */
	public String[] getHeaderRow() {
		return _headerRow;
	}

	/**
	 * @param _headerRow the _headerRow to set
	 */
	public void setHeaderRow(String[] headerRow) {
		_headerRow = headerRow;
	}

	public ArrayList<TableRow> getRows(){
		return _rows;
	}
	
	public void addRow(TableRow tableRow) throws InvalidRowException{
		if(tableRow.getnumberOfColumns() == _numberOfColumns){
			_rows.add(tableRow);
		}else{
			//System.out.println(tableRow.getnumberOfColumns() + ":" + _numberOfColumns);
			throw new InvalidRowException("Incorrect number of columns!!!!");
		}
	}

	/**
	 * @return the _tableName
	 */
	public String getTableName() {
		return _tableName;
	}

	/**
	 * @param _tableName the _tableName to set
	 */
	public void setTableName(String tableName) {
		_tableName = tableName;
	}

}
