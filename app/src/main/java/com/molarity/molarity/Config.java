package com.molarity.molarity;

/**
 * Created by a1 on 10/9/2015.
 */
public class Config {
    public static int list_view_min_height = 95;

    /*
    * table
    * */
    public static String table_name = "FormulaWeight";

    //field
    public static String category = "category";
    public static String code = "code";
    public static String description = "description";
    public static String weight = "weight";

    //category
    public static String Mass = "MASS";
    public static String Volume = "VOLUME";
    public static String Molarity = "MOLARITY";
    public static String Dilute = "Dilute";

    //molar unit
    public static int FentoMolar  = -15;
    public static int PicoMolar   = -12;
    public static int NanoMolar   = -9;
    public static int MicroMolar  = -6;
    public static int MilliMolar   = -3;
    public static int Molar = 0;

    //gram unit
    public static int MicroGram = -9;
    public static int MilliGram = -6;
    public static int Gram = -3;
    public static int KiloGram =0;

    //liter unit
    public static int MicroLiter = -6;
    public static int MilliLiter = -3;
    public static int Liter = 0;

}
