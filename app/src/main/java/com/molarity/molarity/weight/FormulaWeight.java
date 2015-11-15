package com.molarity.molarity.weight;

import java.util.ArrayList;

/**
 * Created by a1 on 10/7/2015.
 */
public class FormulaWeight {
    public ArrayList<String> categories;

    public ArrayList<String> codes;
    public ArrayList<String> descriptions;

    public ArrayList<String> weights;

    public ArrayList<String> concentrations;
    public ArrayList<String> units;

    public FormulaWeight() {
        categories = new ArrayList<String>();

        codes = new ArrayList<String>();
        descriptions = new ArrayList<String>();

        weights = new ArrayList<String>();

        concentrations = new ArrayList<String>();
        units = new ArrayList<String>();
    }

    public void setCodeList( ArrayList<String> codes ) {
        this.codes = codes;
    }

    public  void setDescriptionList( ArrayList<String> descriptions ) {
        this.descriptions = descriptions;
    }

    public void clear() {
        //set null
        codes.clear();
        descriptions.clear();

        weights.clear();

        concentrations.clear();
        units.clear();
    }

    public void remove(int index) {
        if (categories.size() > index)
            categories.remove(index);

        if (codes.size() > index)
            codes.remove(index);

        if (descriptions.size() > index)
            descriptions.remove(index);

        if (weights.size() > index)
            weights.remove(index);

        if (concentrations.size() > index)
            concentrations.remove(index);

        if (units.size() > index)
            units.remove(index);
    }
}
