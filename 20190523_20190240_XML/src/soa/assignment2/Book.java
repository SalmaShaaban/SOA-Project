/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soa.assignment2;

/**
 *
 * @author Lap Smart
 */
class Book {
   private String ID;
    private String Author;
    private String Title;
    private String Gerne;
    private String Price;
    private String PublishDate;
    private String Description;
    
  

    Book(String ID, String Author, String Title, String Gerne, String Price, String PublishDate, String Description) {
      this.ID=ID;
        this.Author=Author;
        this.Title=Title;
        this.Gerne=Gerne;
        this.Price=Price;
        this.PublishDate=PublishDate;
        this.Description=Description;    
    }

   

    @Override
    public String toString() {
        return "<" + ID + ", " + Author + ", " + Title + ", " + Gerne + ", " + Price + ", "+PublishDate+ ", "+Description+ ">";
    }  
}
