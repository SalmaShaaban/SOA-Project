import java.util.Scanner;

public class build {
    String name;
    String city;
    String year;

    public build(String name,String city,String year)
    {

        this.city=city;
        this.name=name;
        this.year=year;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getYear() {
        return year;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        builder.append(", name=");
        builder.append(name);
        builder.append(", city=");
        builder.append(city);
        builder.append(", year=");
        builder.append(year);

        builder.append("]");
        return builder.toString();
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        build other = (build) obj;
        if (name != other.name)
            return false;
        return true;
    }

}
