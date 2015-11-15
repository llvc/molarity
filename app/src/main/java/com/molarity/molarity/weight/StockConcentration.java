package com.molarity.molarity.weight;

/**
 * Created by a1 on 10/8/2015.
 */
public class StockConcentration extends FormulaWeight {

    public StockConcentration() {
        String[] _codes = new String[] {
                "18-155",
                "18-156",
                "18-157",
                "18-158",
                "18-159",
                "18-160",
                "18-161",
                "18-162",
                "18-163",
                "18-164",
                "18-165",
                "18-166",
                "18-167",
                "18-168",
                "18-169",
                "18-170",
                "18-171",
                "18-172",
                "42-403",
                "42-404",
                "42-405",
                "42-406",
                "42-407",
                "42-410",
                "42-411"
        };

        String[] _descriptions = new String[] {
                "EDTA Buffer, 1X",
                "EDTA Buffer, 1X",
                "Glycine-HCI, 10X",
                "Glycine-HCI, 10X",
                "Glycine-HCI, 1X",
                "Glycine-HCI, 1X",
                "HEPES-KOH, 1X",
                "HEPES-KOH, 1X",
                "HEPES-KOH, 1X",
                "HEPES-KOH, 1X",
                "HEPES-KOH, 1X",
                "HEPES-KOH, 1X",
                "HEPES-NaOH, 1X",
                "HEPES-NaOH, 1X",
                "HEPES-NaOH, 1X",
                "HEPES-NaOH, 1X",
                "HEPES-NaOH, 1X",
                "HEPES-NaOH, 1X",
                "dNTP Set, 4 x 100umol",
                "dNTP Set 4 x 500umol",
                "dNTP Mix, 200umol",
                "dNTP Mix, 100umol",
                "dNTP Mix, 100umol",
                "dNTP Set, 4 x 25umol",
                "dNTP Mix, 40mM"
        };

        String[] _concentrations = new String[] {
                "0.5",
                "0.5",
                "0.1",
                "0.1",
                "0.1",
                "0.1",
                "1",
                "1",
                "1",
                "1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "9",
                "100",
                "100",
                "100",
                "10",
                "40",
                "100",
                "40"
        };

        String[] _units = new String[] {
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "-3",
                "-3",
                "-3",
                "-3",
                "-3",
                "-3",
                "-3"
        };

        for (int i = 0; i < _codes.length; i++) {
            codes.add(_codes[i]);
        }

        for (int i = 0; i < _descriptions.length; i++) {
            descriptions.add(_descriptions[i]);
        }

        for (int i = 0; i < _concentrations.length; i++) {
            concentrations.add(_concentrations[i]);
        }

        for (int i = 0; i < _units.length; i++) {
            units.add(_units[i]);
        }
    }
}
