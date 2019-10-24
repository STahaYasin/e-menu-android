package be.tahayasin.menubook.Models;

public class NameValuePair{
    private String name;
    private String value;

    public NameValuePair(String n, String v){
        name = n;
        value = v;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append("=");
        sb.append(value);
        sb.append("&");

        return sb.toString();
    }
}