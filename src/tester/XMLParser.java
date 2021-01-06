package tester;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import liaisonappliBDopta.Connexion;

public class XMLParser {

	public static void main(String[] args) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("file.xml"); //Load le fichier
			//Récupérer l'unique node element+'List'
			NodeList topicList = doc.getElementsByTagName("topicList"); //Faire une liste de l'unique element
			NodeList periodList = doc.getElementsByTagName("periodList"); //Faire une liste de l'unique element
			NodeList roomList = doc.getElementsByTagName("roomList"); //Faire une liste de l'unique element
			//Dans cette NodeList à un élement, récupérer le Node et son contenu (récupérer l'attribut)
			
			if (topicList.getLength()==0 || periodList.getLength()==0 || roomList.getLength()==0) {
				System.out.println("Fichier XML invalide");
			}
			
			int tLid = 0;
			int pLid = 0;
			int rLid = 0;
			Node tL = topicList.item(0);
			if(tL.getNodeType()==Node.ELEMENT_NODE) {
				Element chose = (Element) tL;
				tLid = Integer.valueOf(chose.getAttribute("id"));
			}
			Node pL = periodList.item(0);
			if(pL.getNodeType()==Node.ELEMENT_NODE) {
				Element chose = (Element) pL;
				pLid = Integer.valueOf(chose.getAttribute("id"));
			}
			Node rL = roomList.item(0);
			if(rL.getNodeType()==Node.ELEMENT_NODE) {
				Element chose = (Element) rL;
				rLid = Integer.valueOf(chose.getAttribute("id"));
			}
			//Pour chaque examList, récupérer l'ID
			NodeList examList = doc.getElementsByTagName("examList");
			String rPLid ="";
			int dimL = 0;
			Node temp = examList.item(0);
			if(temp.getNodeType()==Node.ELEMENT_NODE) {
				Element chose = (Element) temp;
				rPLid = chose.getAttribute("id");
				NodeList LeadingExamList = doc.getElementsByTagName("LeadingExam");
				dimL = LeadingExamList.getLength();
			}
			int[][] laListe = new int[3][dimL];
			Node rPL = examList.item(0);
			if(rPL.getNodeType()==Node.ELEMENT_NODE) {
				Element chose = (Element) rPL;
				rPLid = chose.getAttribute("id");
				//Dans cette liste, récupérer les LeadingExam
				NodeList LeadingExamList = doc.getElementsByTagName("LeadingExam");
				for(int i=0;i<LeadingExamList.getLength();i++) { //Pour chaque LeadingExam
					Node n = LeadingExamList.item(i); //Enregistrer dans un node
					Element LeadingExam = (Element) n; //Le cast
					NodeList LeadingExamChild = LeadingExam.getChildNodes(); //Récupérer ses nodes
					for(int j=0;j<LeadingExamChild.getLength();j++) { //Pour chaque noeud d'un LeadingExam
						Node cn = LeadingExamChild.item(j); //Enregistrer en un node
						if(cn.getNodeType()==Node.ELEMENT_NODE) { //Si il y a du contenus
							Element LeadingExamChildNode = (Element) cn; //Le cast
							if(LeadingExamChildNode.getTagName()=="topic") {
								//System.out.println("topic="+LeadingExamChildNode.getAttribute("reference"));
								laListe[0][i] = 
										(int) Math.ceil( (Integer.valueOf(LeadingExamChildNode.getAttribute("reference"))- tLid) /2)+1;
							}
							else if(LeadingExamChildNode.getTagName()=="room") {
								//System.out.println("room="+LeadingExamChildNode.getAttribute("reference"));
								laListe[1][i] = Integer.valueOf(LeadingExamChildNode.getAttribute("reference"))- rLid;
							}
							else if(LeadingExamChildNode.getTagName()=="period") {
								//System.out.println("period="+LeadingExamChildNode.getAttribute("reference"));
								laListe[2][i] = Integer.valueOf(LeadingExamChildNode.getAttribute("reference"))- pLid;
							}
						}
						
					}
				}
				Connexion a = new Connexion();
				a.RequeteDB("DELETE FROM liaison");
				for(int j=0;j<laListe[0].length;j++) {
					System.out.println(laListe[0][j]+";"+laListe[2][j]+";"+laListe[1][j]);
					a.RequeteDB("INSERT INTO liaison VALUES ("+laListe[0][j]+","+laListe[2][j]+","+laListe[1][j]+")");
				}
			}
			System.out.println("Fin="+tLid + ";" + pLid + ";" + rLid + ":" + rPLid);			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
