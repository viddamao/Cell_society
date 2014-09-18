package controller;

import simulationObjects.*;
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
 *            String input for the xml file name
 * 
 *            xml parser for the input grid
 * 
 *            return a list of cell nodes needs first process to grid
 * 
 */
public class Parser {

    static GridInfo infoSheet = new GridInfo();

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

	NodeList nodeList = document.getElementsByTagName("gridInfo");

	attributeParse(cellList, nodeList.item(0), false);

	// Iterating through the nodes and extracting the data.
	// NodeList nodeList = document.getDocumentElement().getChildNodes();

	nodeList = document.getElementsByTagName("row");

	for (int i = 0; i < nodeList.getLength(); i++) {

	    // We have encountered an <cell> tag.
	    Node node = nodeList.item(i);
	    if (node instanceof Element)
		attributeParse(cellList, node, true);

	}

	return cellList;
    }

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
    private static void attributeParse(List<TestCell> cellList, Node node,
	    boolean flag) {
	{

	    TestCell cellBlock = new TestCell();
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
		    case "type":
			infoSheet.setType(content);
			break;
		    case "sub1":
                        infoSheet.addSubType1(content);
                        break;
		    case "sub2":
                        infoSheet.addSubType2(content);
                        break;
		    case "adjacentType":
			infoSheet.setAdjacent(Integer.parseInt(content));
			break;
		    case "parameter":
			infoSheet.setParameter(Integer.parseInt(content));
			break;

		    }
		}
	    }
	    if (flag)
		cellList.add(cellBlock);
	}
    }

}
