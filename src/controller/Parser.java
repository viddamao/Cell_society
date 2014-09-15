package controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



/**
 * 
 * @param filename
 *    String input for the xml file name
 * 
 * xml parser for the input grid 
 * 
 * return a list of cell nodes needs first process to grid
 * 
 */
public class Parser {

    static GridManager object=new GridManager(0, 0);
    
    public static List parserXml(String fileName) throws Exception {
	// check for xml data
	String extension = fileName.substring(fileName.lastIndexOf(".") + 1,
		fileName.length());
	if (!extension.equals("xml")) {
	    throw new UnsupportedOperationException();
	}

	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

	DocumentBuilder builder = factory.newDocumentBuilder();

	FileInputStream is = new FileInputStream(fileName);

	Document document = builder.parse(is);
	
	
	List<TestCell> cellList = new ArrayList<>();

	// Iterating through the nodes and extracting the data.
	//NodeList nodeList = document.getDocumentElement().getChildNodes();
	
	
	NodeList nodeList = document.getElementsByTagName("cell");
	
	for (int i = 0; i < nodeList.getLength(); i++) {

	    // We have encountered an <cell> tag.
	    Node node = nodeList.item(i);
	    if (node instanceof Element)
		attributeParse(cellList, node,true);
	   
	}
	
	nodeList = document.getElementsByTagName("gridInfo");
	attributeParse(cellList, nodeList.item(0),false);
	       
	// Printing the cell list input.
	TestCell currentBlock=new TestCell();
	for (TestCell cellBlock : cellList) {
	    System.out.println(cellBlock);
	    currentBlock=cellBlock;
	}
	
	return cellList;
    }

    private static void attributeParse(List<TestCell> cellList, Node node,boolean flag) {
	{
	    
	TestCell cellBlock = new TestCell();
	if (flag)
	{
	
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
		case "xLoc":
		    cellBlock.xLoc = Integer.parseInt(content);
		    break;
		case "yLoc":
		    cellBlock.yLoc = Integer.parseInt(content);
		    break;
		case "currentState":
		    cellBlock.currentState = Integer.parseInt(content);
		    break;
		case "cellType":
		    cellBlock.cellType =content;
		    break;
		case "width":
		    object.setWidth(Integer.parseInt(content));
		    break;
		case "height":
		    object.setHeight(Integer.parseInt(content));
		    break;
		case "type":
		    object.setType(content);
		    break;
		case "adjacentType":    
		    object.setAdjacent(Integer.parseInt(content));
		    break;    
		}
	    }
	}
	if (flag) cellList.add(cellBlock);
	}
    }

}
