package controller;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.scene.paint.Color;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import simulationObjects.Cell;

/**
 *
 * @param filename
 *            String input for the xml file name
 *
 *            xml parser for the input grid
 *
 *            return a list of cell nodes needs first process to grid
 *
 */
public class Parser {

    /**
     *
     *
     *
     *
     * @param cellList
     *            return a list of strings with rows in the grid
     * @param node
     *            the input node in XML
     * @param flag
     *            magic number
     *
     */
    private static void attributeParse(List<GridRows> cellList, Node node,
	    boolean flag) {
	{

	    GridRows cellBlock = new GridRows();
	    if (flag) {

		cellBlock.id = Integer.parseInt(node.getAttributes()
			.getNamedItem("id").getNodeValue());
	    }
	    NodeList childNodes = node.getChildNodes();

	    for (int j = 0; j < childNodes.getLength(); j++) {
		Node cNode = childNodes.item(j);

		// Identifying the child tag of cells encountered.
		if (cNode instanceof Element) {
		    String content = cNode.getLastChild().getTextContent()
			    .trim();
		    switch (cNode.getNodeName()) {
		    case "states":
			cellBlock.states = content;
			break;
		    case "width":
			infoSheet.setWidth(Integer.parseInt(content));
			break;
		    case "height":
			infoSheet.setHeight(Integer.parseInt(content));
			break;
		    case "patchType":
			infoSheet.setPatchType(content);
			break;
		    case "type":
			infoSheet.setType(content);
			break;
		    case "adjacentType":
			infoSheet.setAdjacent(Integer.parseInt(content));
			break;
		    case "maxCellState":
			infoSheet.setMaxCellState(Integer.parseInt(content));
			break;
		    case "parameter":
			infoSheet.setParameter(Double.parseDouble(content));
			break;
		    }
		}
	    }
	    if (flag) {
		cellList.add(cellBlock);
	    }
	}
    }

    private static void inputValidion() {
	if (infoSheet.getCellType() == null) {
	    JOptionPane.showMessageDialog(null,
		    messages.getString("missing_sim_type"));
	    System.exit(0);
	}

    }

    public static List parserXml(String fileName) throws Exception {
	// check for xml data
	String extension = fileName.substring(fileName.lastIndexOf(".") + 1,
		fileName.length());
	if (!extension.equals("xml")) {
	    JOptionPane.showMessageDialog(null, "Not Valid XML File");
	    System.exit(0);
	    throw new UnsupportedOperationException();
	}

	messages = ResourceBundle.getBundle("messages", Locale.US);

	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

	DocumentBuilder builder = factory.newDocumentBuilder();

	FileInputStream is = new FileInputStream(fileName);

	Document document = builder.parse(is);

	List<GridRows> cellList = new ArrayList<>();

	NodeList nodeList = document.getElementsByTagName("gridInfo");

	attributeParse(cellList, nodeList.item(0), false);

	inputValidion();

	// Iterating through the nodes and extracting the data.
	// NodeList nodeList = document.getDocumentElement().getChildNodes();

	nodeList = document.getElementsByTagName("row");

	for (int i = 0; i < nodeList.getLength(); i++) {

	    // We have encountered an <cell> tag.
	    Node node = nodeList.item(i);
	    if (node instanceof Element) {
		attributeParse(cellList, node, true);
	    }

	}

	setCellStates();
	return cellList;
    }

    private static void setCellStates() {

	String classPathAndName = messages.getString("cell_bundle") + "."
		+ infoSheet.getCellType();
	try {
	    Class<?> cellClass = Class.forName(classPathAndName);
	    Cell cell = (Cell) cellClass.newInstance();
	    List<String> myStateTypes = cell.getStateTypes();
	    infoSheet.setStateTypes(myStateTypes);
	    List<Color> myColors = cell.getInitialColors();
	    for (int index = 0; index < cell.getStateTypes().size(); index++) {
		infoSheet
			.setColor(myStateTypes.get(index), myColors.get(index));
	    }
	} catch (ClassNotFoundException e) {
	    System.out.println(messages.getString("class_not_found_error"));
	} catch (InstantiationException e) {
	    System.out.println(messages.getString("instantiation_error"));
	} catch (IllegalAccessException e) {
	    System.out.println(messages.getString("illegal_access_error"));
	}
    }

    static GridInfo infoSheet = new GridInfo();

    private static ResourceBundle messages;

}
