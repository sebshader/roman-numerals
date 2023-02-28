
package com.sebshader.roman;

public class RomanNumeral {
    protected String roman;
    protected int arabic;
    private static final int[] numbers = {1000, 900, 500, 400, 100, 90, 50, 40,
        10, 9, 5, 4, 1};
    private static final String[] names = {"M", "CM", "D", "CD", "C", "XC", "L",
        "XL", "X", "IX", "V", "IV", "I"};
    public RomanNumeral (String fromstring) throws NumberFormatException {
        this.roman = fromstring;
        int arabic = 0;
        int prevLevel = -1;
        int level = 0;
        int baseLevel = -1;
        int repcount = 0;
        // was the last a 9 digit?
        int lastFourNine = 1;
        // we have already used the last level
        boolean borrowed = false;
        int strlen = fromstring.length();
        for(int i = 0; i < strlen; i++) {
            char charrent = fromstring.charAt(i);
            switch(charrent) {
                case 'M': level = 0; break;
                case 'D': level = 2; break;
                case 'C': level = 4; break;
                case 'L': level = 6; break;
                case 'X': level = 8; break;
                case 'V': level = 10; break;
                case 'I': level = 12; break;
                default: throw new NumberFormatException("RomanNumeral: invalid character " + charrent + " at position " + i);
            }
            // last value is > current level
            if(level < prevLevel) {
                // ten's place (last multiple of 4), since we need to 
                // figure out if the last was the correct "1"'s place
                int roundLevel = (level & ~3);
                if (((prevLevel - roundLevel) == 4) && (baseLevel <= level)) {
                    if(baseLevel == level && (level & 2) != 0)
                        throw new NumberFormatException("2 5-digits in a row at position " + i);
                    borrowed = true;
                    arabic -= numbers[prevLevel] << 1;
                    baseLevel = level;

                    prevLevel = level;
                    lastFourNine = level & 3;
                } else throw new NumberFormatException("invalid order at position " + i);
            } else if (level > prevLevel) {
                // if the last digit was a 9 (CM or XC), the prev level must be >= 6 away
                // or else it's a malformed number e.g. IXIV is not 13, XIII is.
                // if it was a four, don't allow next to be only 1 level away.
                if((lastFourNine == 0) && ((level - baseLevel) < 6))
                    throw new NumberFormatException("number following 9's place is too big at position " + i);
                else if(((lastFourNine & 2) != 0) && ((level - baseLevel) <= 2))
                    throw new NumberFormatException("can't have ones immediately after fours at position " + i);
                lastFourNine = 1;
                baseLevel = prevLevel;
                prevLevel = level;
                repcount = 1;
                borrowed = false;
            } else { // level == prevLevel
                // we've already repeated 3 times
                // or, the repeat is a 5-digit
                if(repcount == 3 || borrowed || ((level & 2) != 0))
                    throw new NumberFormatException("too many repeated numbers at position " + i);
                repcount++;
                baseLevel = level;
            }
            arabic += numbers[level];
        }
        this.arabic = arabic;
    }
    public RomanNumeral (int fromint) throws NumberFormatException {
        if (fromint < 1 || fromint > 3999)
            throw new NumberFormatException("RomanNumeral: number " + fromint + " out of range");
        this.arabic = fromint;
        StringBuilder roman = new StringBuilder();
        for(int i = 0; i < numbers.length; i++) {
            int current = numbers[i];
            while(fromint >= current) {
                roman.append(names[i]);
                fromint -= current;
            }
        }
        this.roman = roman.toString();
    }
    public String toString() {
        return this.roman;
    }
    public int toInt() {
        return this.arabic;
    }
}
