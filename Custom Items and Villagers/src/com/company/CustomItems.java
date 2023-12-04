package com.company;
import java.util.*;
public class CustomItems {
    static String loreColor= "";
    static String loreNameItalic = "";
    static String loreNameBold= "";
    static String lore= "";

    public static void main(String[] args) {
        System.out.println("Type in the following to create a custom item. For questions that are not applicable type 0. for questions with a ? at the end, type 'y' or 'n'\nHow Many/Count: ");
        Scanner console = new Scanner(System.in);
        String count = console.nextLine();
        System.out.println("Minecraft name/id: ");
        String minecraftName = console.nextLine();
        System.out.println("Display name: ");
        String displayName = console.nextLine();
        System.out.println("Text Color: ");
        String color = ",\"color\":\""+console.nextLine()+"\"";
        System.out.println("Unbreakable?");
        String unbreakable = unbreakableTrueOrFalse(console.nextLine());
        System.out.println("Display name italic?");
        String displayNameItalic = italicTrueOrFalse(console.nextLine());
        System.out.println("Display name bold?");
        String displayNameBold = boldTrueOrFalse(console.nextLine());
        System.out.println("Lines Lore: ");
        String lore = console.nextLine();
        Scanner c = new Scanner(lore);
        int linesOfLore = c.nextInt();
        String loreFinal = loreTrueOrFalse(linesOfLore, console);
        System.out.println("Number of enchantments: ");
        String numberEnchants = console.nextLine();
        Scanner a = new Scanner(numberEnchants);
        int numEnchants = a.nextInt();
        String enchantments = enchants(numEnchants, console);
        System.out.println("Hide enchants?");
        String hide = new String(console.nextLine());
        System.out.println("Number of Attributes: ");
        String numberAttributes = console.nextLine();
        Scanner b = new Scanner(numberAttributes);
        int numAttributes = b.nextInt();
        String attributes = attributesTrueOrFalse(numAttributes, console);
        String item = "give @p "+minecraftName+"{"+unbreakable+"display:{Name:'[{\"text\":\""+displayName+"\""+displayNameItalic+displayNameBold+color+"}]'"+loreFinal+"}"+enchantments+hideTrueOrFalse(hide)+attributes+"} "+count;
        System.out.println("The give command is: "+item);
        String nbtData = "{id: \"minecraft:"+minecraftName+"\", tag: {"+unbreakable+"display:{Name:'[{\"text\":\""+displayName+"\""+displayNameItalic+displayNameBold+color+"}]'"+loreFinal+"]}"+enchantments+hideTrueOrFalse(hide)+attributes+"}, Count: "+count+"b}";
        System.out.println("The nbt data is: "+nbtData);

    }
    public static String enchants(int numEnchants, Scanner console){
        if(numEnchants == 0){
            return "";
        } else {
            ArrayList<String> enchants = new ArrayList<>();
            for (int i = 0; i < numEnchants; i++) {
                System.out.print("Enchantment name/id: ");
                String enchantName = console.nextLine();
                System.out.println("Enchantment level: ");
                String enchantmentLevel = console.nextLine();
                enchants.add(",{id:" + enchantName + ",lvl:" + enchantmentLevel + "}");
            }
            enchants.set(0, enchants.get(0).substring(1));
            String finalEnchants = "";
            for (int x = 0; x < enchants.size(); x++) {
                finalEnchants = finalEnchants + enchants.get(x);
            }
            return ",Enchantments:[" + finalEnchants + "]";
        }
    }
    public static String attributesTrueOrFalse(int numAttributes, Scanner console){
        if(numAttributes ==0){
            return "";
        } else {
            int first = (int)(Math.random()*(9999998-1000001));
            int second = (int)(Math.random()*(99998-10001));
            int third = (int)(Math.random()*(999998-100001));
            int fourth = (int)(Math.random()*(99998-10001));
            String uuid = "-"+first+","+second+","+third+",-"+fourth;
            ArrayList<String> attributes = new ArrayList<>();
            for (int i = 0; i <numAttributes; i++) {
                System.out.print("Attribute name/id: ");
                String attributeName = console.nextLine();
                System.out.println("Amount: ");
                String amount = console.nextLine();
                System.out.println("Operation: ");
                String operation = console.nextLine();
                System.out.println("Slot: ");
                String slot = console.nextLine();
                attributes.add(",{AttributeName:\""+attributeName+"\",Amount:"+amount+",Operation:"+operation+",UUID:[I;"+uuid+"],Slot:"+slot+",Name:\""+attributeName+"\"}");
            }
            attributes.set(0,attributes.get(0).substring(1));
            String finalAttributes = "";
            for(int a = 0; a<attributes.size();a++){
                finalAttributes = finalAttributes+attributes.get(a);
            }
            return ",AttributeModifiers:["+finalAttributes+"]";
        }
    }
    public static String unbreakableTrueOrFalse(String ans){
        if(ans.equalsIgnoreCase("y")){
            return "Unbreakable:1,";
        } else if(ans.equalsIgnoreCase("n")){
            return "";
        } else {
            return "you messed up idiot";
        }
    }
    public static String italicTrueOrFalse(String ans){
        if(ans.equalsIgnoreCase("y")){
            return ",\"italic\":true";
        } else if(ans.equalsIgnoreCase("n")){
            return ",\"italic\":false";
        } else {
            return "you messed up idiot";
        }
    }
    public static String loreTrueOrFalse(int ans, Scanner console){
        if (ans==0) {
            return "";
        } else {
            return loreComplete(ans, console);
        }
    }
    public static String boldTrueOrFalse(String ans){
        if (ans.equalsIgnoreCase("y")) {
            return ",\"bold\":true";
        } else if (ans.equalsIgnoreCase("n")) {
            return "";
        } else {
            return "you messed up idiot";
        }
    }
    public static String hideTrueOrFalse(String ans){
        if (ans.equalsIgnoreCase("y")) {
            return ",HideFlags:1";
        } else if (ans.equalsIgnoreCase("n")) {
            return "";
        } else {
            return "you messed up idiot";
        }
    }
    public static String loreComplete(int ans, Scanner console){
        String temp = "";
        ArrayList<String> loreFinal = new ArrayList<>();
        for(int i = 0; i<ans;i++) {
            System.out.print("Line "+(i+1)+" of lore: ");
            String temp1 = console.nextLine();
            if(temp1.equals("0")){
                lore = "";
            } else {
                lore = temp1;
            }
            System.out.println("Lore color: ");
            loreColor = ",\"color\":\"" + console.nextLine() + "\"";
            System.out.println("Lore name italic?");
            loreNameItalic = italicTrueOrFalse(console.nextLine());
            System.out.println("Lore name bold?");
            loreNameBold = boldTrueOrFalse(console.nextLine());
             loreFinal.add("'[{\"text\":\"" + lore + "\"" + loreNameItalic + loreNameBold + loreColor + "}]'");
        }
        for(int a = 0;a<loreFinal.size();a++){
            temp = temp + loreFinal.get(a);
        }
        return ",Lore:"+loreFinal;
    }
}
