package utils;

public class CheckNumeric {
    public static boolean CheckNumeric(final String number) {
        if(number == null || number.equals("")) {
            return false;
        } 
        int dotCount = 0;

        try {
        double numericValue = Double.parseDouble(number);

        if (numericValue <= 0) {
            return false;
        }

        for (char c : number.toCharArray()) {
            if (!Character.isDigit(c)) {
                if (c == '.' && dotCount == 0) {
                    dotCount++;
                } else {
                    return false;
                }
            }
        }

        return true;
        } catch (NumberFormatException e) {
            return false; 
        }
    }
    
    public boolean checkNumber(String number) {
        if(number == null || number.equals("")) {
            return false;
        } 
        int dotCount = 0;

        try {
            double numericValue = Double.parseDouble(number);

            if (numericValue < 0) {
                return false;
            }

            for (char c : number.toCharArray()) {
                if (!Character.isDigit(c)) {
                    if (c == '.' && dotCount == 0) {   
                        return false;
                    }
                }
            }

            return true;
        } catch (NumberFormatException e) {
            return false; 
        }
    }
    
    public boolean checkNumber2 (String number) {
        if(number == null || number.equals("")) {
            return false;
        } 
        int dotCount = 0;

        try {
            double numericValue = Double.parseDouble(number);

            if (numericValue < 0) {
                return false;
            }

            for (char c : number.toCharArray()) {
                if (!Character.isDigit(c)) {
                    if (c == '.' && dotCount == 0) {
                    dotCount++;
                    } else {
                        return false;
                    }
                }
            }

            return true;
        } catch (NumberFormatException e) {
            return false; 
        }
    }
    
    public boolean checkSDT(String number) {
        if(number == null || number.equals("")) {
            return false;
        } 

        try {
            double numericValue = Double.parseDouble(number);

            if (numericValue <= 0) {
                return false;
            }

            for (char c : number.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
        
        } catch (NumberFormatException e) {
            return false; 
        }
        
        return number.length() == 10;
    }
    
    public boolean checkCCCD(String number) {
        if(number == null || number.equals("")) {
            return false;
        } 

        try {
            double numericValue = Double.parseDouble(number);

            if (numericValue <= 0) {
                return false;
            }

            for (char c : number.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
        
        } catch (NumberFormatException e) {
            return false; 
        }
        
        return number.length() == 12;
    }
}
