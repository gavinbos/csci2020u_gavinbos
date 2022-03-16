package com.example.midterm;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class ParseXml {
    public VBox generateAboutPage() {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        String name = "";
        String id = "";
        String email = "";
        String description = "";

        // DocumentBuilder allows us to parse XML documents
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("C:\\Users\\gavin\\Desktop\\School\\Software Dev\\csci2020u_gavinbos\\midterm\\src\\main\\resources\\com\\example\\midterm\\AboutPage.xml"));

            document.getDocumentElement().normalize();
            NodeList infoList = document.getElementsByTagName("info");
            NodeList studentList = document.getElementsByTagName("student");

            // retrieving student id
            Element studentElement = (Element) studentList.item(0);
            id = studentElement.getAttribute("id");

            //retrieving remaining elements
            for (int i = 0; i < infoList.getLength(); i++) {
                Node node = infoList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    Node node1 = element.getElementsByTagName("name").item(0);
                    name = node1.getTextContent();

                    Node node2 = element.getElementsByTagName("email").item(0);
                    email = node2.getTextContent();

                    Node node3 = element.getElementsByTagName("software-description").item(0);
                    description = node3.getTextContent();
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        // taking parsed information and setting it up to display on the about page
        Label nameLabel = new Label("Name: " + name);
        Label idLabel = new Label("ID: " + id);
        Label emailLabel = new Label("Email: " + email);
        TextArea descr = new TextArea(description);
        descr.setMaxSize(270, 150);

        vBox.getChildren().addAll(nameLabel, idLabel, emailLabel, descr);
        return vBox;
    }
}
