/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soa.assignment2;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

/**
 *
 * @author Lap Smart
 */
public class SOAAssignment2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
     
          DocumentBuilder builder= dbf.newDocumentBuilder();
          Document doc =builder.parse("Books.xml");
          
          int n,w,BId;
          double Bprice;
          boolean flag=true;
          SearchAndDelete x =new SearchAndDelete();
          List<String> gerne=new ArrayList<String>();
            gerne.add("1");
            gerne.add("2");
            gerne.add("3");
          String y,Btitle,Bauthor,Bgerne,Bdescription,BpublishDate,input;
          System.out.println("Please, Enter the numbur of books");
          Scanner sc= new Scanner(System.in);
          n =sc.nextInt();
          //create root 
         Element root=doc.getDocumentElement();
          //create book
          for(int i=0;i<n;i++){
            
          Element Book =doc.createElement("Book");
          root.appendChild(Book);
            System.out.println("Please, Enter Book ID, it must be an integer number");
            while(flag==true){   
            Scanner s1= new Scanner(System.in);
            BId=s1.nextInt();
            input=toString(BId);
            flag=x.IsExist(input);
            if(flag==true){
               System.out.println("This ID is already exist, enter another ID"); 
            }
            if(flag==false){ 
             Book.setAttribute("ID",input);
            }
            }
          Element Author =doc.createElement("Author");
            System.out.println("Please, Enter Author Name");
            Scanner s2= new Scanner(System.in);
            Bauthor=s2.nextLine();
            while(Bauthor.isEmpty()|| x.DetectSpecialCharacters(Bauthor)){
            System.out.println("Autor name can't be Empty or contain numbers or special characters! please, Enter Author Name");
            s2= new Scanner(System.in);
            Bauthor=s2.nextLine();
            }
          Author.setTextContent(Bauthor);
          Book.appendChild(Author);
          Element Title =doc.createElement("Title");
            System.out.println("Please, Enter Book Title");
            Scanner s3= new Scanner(System.in);
            Btitle=s3.nextLine();
             while(Btitle.isEmpty()){
            System.out.println("Book Title can't be Empty! please, Enter Book Title Name");
            s3= new Scanner(System.in);
            Btitle=s3.nextLine();
            }
          Title.setTextContent(Btitle);
          Book.appendChild(Title);
          
          Element Gerne =doc.createElement("Gerne");
            System.out.println("Please, Choose Book Genre from (Science, Fiction, Drama)."
                    + "Please, Enter 1,2 or 3");
            Scanner s4= new Scanner(System.in);
            Bgerne=s4.nextLine();
             while(Bgerne.isEmpty() || !gerne.contains(Bgerne)){
                System.out.println("Book Genre can't be Empty or you enter invalid gerne! Please, Choose Book Genre from (Science, Fiction, Drama)"
                    + "Please, Enter 1,2 or 3");
            s4= new Scanner(System.in);
            Bgerne=s4.nextLine();
             }
          if("1".equals(Bgerne)) Bgerne="Science";
          if("2".equals(Bgerne)) Bgerne="Fiction";
          if("3".equals(Bgerne)) Bgerne="Drama";
          Gerne.setTextContent(Bgerne);
          Book.appendChild(Gerne);
          
          Element Price =doc.createElement("Price");
            System.out.println("Please, Enter Book Price");
            Scanner s5= new Scanner(System.in);
            Bprice=s5.nextDouble();
            input = toString(Bprice);
              while(input.isEmpty()){
                System.out.println("Book Price can't be Empty! please, Enter Book Price");
            s5= new Scanner(System.in);
            input=s5.nextLine();
            }
          Price.setTextContent(input);
          Book.appendChild(Price);
          
          Element PublishDate =doc.createElement("PublishDate");
            System.out.println("Please, Enter the Publish Date of the Book in this format dd-mm-yyyy");
            Scanner s6= new Scanner(System.in);
            BpublishDate=s6.nextLine();
            flag=x.DetectDateFormat(BpublishDate);
              while(BpublishDate.isEmpty() || flag==false){
                System.out.println("Publish Date of the Book can't be Empty! please, Enter Publish Date of the Book");
                s6= new Scanner(System.in);
                BpublishDate=s6.nextLine();
                flag=x.DetectDateFormat(BpublishDate);

            }
          PublishDate.setTextContent(BpublishDate);
          Book.appendChild(PublishDate);
          
          Element Description =doc.createElement("Description");
            System.out.println("Please, Enter Book Description");
            Scanner s7= new Scanner(System.in);
            Bdescription=s7.nextLine();
          Description.setTextContent(Bdescription);
          Book.appendChild(Description);
      
         
          }
          
          
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(doc);
        StreamResult streamResult = new StreamResult("Books.xml");
        transformer.transform(domSource, streamResult);  
        
        do{
       System.out.println("Do you want to 1-Search by Title    2-Search by Author   3-Search by Gerne   4-Search by price   \n"
               + "5-Publish Date    6-Search by Description   7-Search by ID   8-Delete Book by ID     9-Exit");
           Scanner s8= new Scanner(System.in);
           w=s8.nextInt();
           Scanner s9= new Scanner(System.in);
            if(w==1){
            System.out.println("Please, Enter Book Title");
            y=s9.nextLine();
            x.searchByAttribute("Title", y);
            }
            if(w==2){
            System.out.println("Please, Enter Book Author");
            y=s9.nextLine();
            x.searchByAttribute("Author", y);
            }
            if(w==3){
            System.out.println("Please, Enter Book Gerne");
            y=s9.nextLine();
            x.searchByAttribute("Gerne", y);
            }
            if(w==4){
            System.out.println("Please, Enter Book Price");
            y=s9.nextLine();
            Double d = Double.valueOf(y);
            y=toString(d);
            x.searchByAttribute("Price", y);
            }
            if(w==5){
            System.out.println("Please, Enter Book PublishDate");
            y=s9.nextLine();
            x.searchByAttribute("PublishDate", y);
            }
            if(w==6){
            System.out.println("Please, Enter Book Description");
            y=s9.nextLine();
            x.searchByAttribute("Description", y);
            }
            if(w==7){
            System.out.println("Please, Enter Book ID");
            y=s9.nextLine();
            x.searchByAttribute("ID", y);
            }
            if(w==8){
            System.out.println("Please, Enter Book ID");
            y=s9.nextLine();   
            x.Delete(y);
            }
        }while(w!=9);
        
          

    }

    private static String toString(int x) {
      String s=String.valueOf(x);
        return s;
    }
     private static String toString(double x) {
      String s=String.valueOf(x);
        return s;
    }

       
          }
    
    
    

