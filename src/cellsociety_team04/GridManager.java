package cellsociety_team04;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

public class GridManager {
	private Cell[][] grid;

	public GridManager(int size)
	{

	}

	public void fillGrid(int CellType)
	{
		for(Cell[] row : grid){
			Arrays.fill(row, new PredatorCell());
		}
	}

	public void update()
	{
		for(Cell[] row :grid){
			for(Cell unit : row){
				unit.update();
			}
		}
	}
	
	public void addCellAtPoint()
	{
		
	}


	public ArrayList<Cell> getEmptyCellsAroundPoint(Point p)
	{
		return null;
	}

	
	public ArrayList<Cell> getCellsAroundPoint(Point p)
	{
		return null;
	}

}
