package com.molarity.molarity.weight;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by a1 on 10/7/2015.
 */
public class GeneseeWeight extends FormulaWeight {
    public GeneseeWeight() {
        String[] _codes = new String[]{
                "18-106",
                "18-107",
                "18-108",
                "18-109",
                "18-110",
                "18-111",
                "18-112",
                "18-113",
                "18-114",
                "18-115",
                "18-124",
                "18-125",
                "18-126",
                "18-127",
                "18-132",
                "18-133",
                "18-141",
                "18-142",
                "18-143",
                "18-144",
                "18-145",
                "18-146",
                "18-148",
                "18-149",
                "18-150",
                "18-151",
                "18-152",
                "20-147",
                "20-148",
                "20-149",
                "20-150",
                "20-200",
                "20-201",
                "20-262",
                "20-204",
                "20-205",
                "20-206",
                "20-236",
                "20-258",
                "20-259",
                "20-266",
                "20-108",
                "20-109",
                "20-110",
                "18-199",
                "18-200",
                "18-201",
                "18-202",
                "18-203",
                "18-204",
                "18-205",
                "18-206",
                "18-207",
                "18-208",
                "18-209",
                "18-210",
                "18-211",
                "18-214",
                "18-215",
                "18-216",
                "18-217",
                "25-513",
                "25-530",
                "25-531",
                "25-532"
        };

        String[] _descriptions = new String[]{
                "EDTA Buffer, 1X",
                "Glycine",
                "Glycine",
                "Glycine",
                "Glycine-HCl, 1X",
                "Glycine-HCl, 1X",
                "HEPES",
                "HEPES",
                "MOPS",
                "MOPS",
                "PIPES",
                "PIPES",
                "Sodium Acetate, 1X, pH 5.2",
                "Sodium Acetate, 1X, pH 7.0",
                "TAPS",
                "TAPS",
                "Tricine",
                "Tricine",
                "Tris Base",
                "Tris Base",
                "Tris Base",
                "Tris Base",
                "Tris-HCl",
                "Tris-HCl",
                "Tris-HCl, 1X,  pH 7.4",
                "Tris-HCl, 1X,  pH 8.0",
                "Tris-HCl, 1X,  pH 8.3",
                "EDTA, Disodium Salt, 500g",
                "EDTA, Disodium Salt, 1kg",
                "EDTA, Disodium Salt, 2.5kg",
                "EDTA, Disodium Salt, 5kg",
                "Tris Base, 500g",
                "Tris Base, 1kg",
                "Tris Base, 5Kg",
                "Tris Hydrochloride, 500g",
                "Urea, 1kg",
                "Urea, 2.5kg",
                "Tricine, 1kg",
                "Tegosept, 1Kg",
                "Tegosept, 5Kg",
                "Tegosept, 25Kg",
                "Apex X-Gal 1",
                "Apex IPTG 5g",
                "X-Glu 100mg",
                "Bicine",
                "Boric Acid",
                "Chloramphenicol",
                "Chloramphenicol",
                "DTT",
                "DTT",
                "Glycerol",
                "Guanidine Hydrochloride",
                "Guanidine Thiocyanate",
                "Imidazole",
                "Imidazole",
                "Kanamycin Sulfate",
                "Kanamycin Sulfate",
                "Sodium Chloride",
                "Sodium Chloride",
                "Sodium Chloride",
                "TCEP",
                "G418 disulfate salt G418",
                "Ampicillin, Sodium Salt",
                "Puromycin Dihydrochloride",
                "Carbenicillin, Disodium Salt"
        };

        String[] _weights = new String[]{
                "292.24",
                "75.07",
                "75.07",
                "75.07",
                "111.5",
                "111.5",
                "238.3",
                "238.3",
                "209.3",
                "209.3",
                "302.37",
                "302.37",
                "82.03",
                "82.03",
                "243.28",
                "243.28",
                "179.17",
                "179.17",
                "121.14",
                "121.14",
                "121.14",
                "121.14",
                "157.59",
                "157.59",
                "157.59",
                "157.59",
                "157.59",
                "372.2",
                "372.2",
                "372.2",
                "372.2",
                "121.14",
                "121.14",
                "121.14",
                "157.59",
                "60.06",
                "60.06",
                "179.17",
                "152.15",
                "152.15",
                "152.15",
                "408.63",
                "238.3",
                "408.63",
                "163.17",
                "61.83",
                "323.13",
                "323.13",
                "154.25",
                "154.25",
                "92.09",
                "95.53",
                "118.16",
                "68.08",
                "68.08",
                "582.6",
                "582.6",
                "58.44",
                "58.44",
                "58.44",
                "286.65",
                "692.71",
                "371.39",
                "544.43",
                "422.4"
        };

        for (int i = 0; i < _codes.length; i++) {
            codes.add(_codes[i]);
        }

        for (int i = 0; i < _descriptions.length; i++) {
            descriptions.add(_descriptions[i]);
        }

        for (int i = 0; i < _weights.length; i++) {
            weights.add(_weights[i]);
        }
    }
}