package utils;

import java.util.Random;

public class RandomID {
    public static String randomBookID() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder randomAlphabet = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            char randomChar = alphabet.charAt(random.nextInt(alphabet.length()));
            randomAlphabet.append(randomChar);
        }
        
        StringBuilder randomNumbers = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int randomNumber = random.nextInt(10);
            randomNumbers.append(randomNumber);
        }
        
        return randomAlphabet.toString() + randomNumbers.toString();

    }
    
    public String randomReaderID() {
        Random random = new Random();
        
        StringBuilder randomNumbers = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int randomNumber = random.nextInt(10);
            randomNumbers.append(randomNumber);
        }
        
        return "DG" + randomNumbers.toString();
    }
    
    public String randomCardID() {
        Random random = new Random();
        
        StringBuilder randomNumbers = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int randomNumber = random.nextInt(10);
            randomNumbers.append(randomNumber);
        }
        
        return "TTV" + randomNumbers.toString();
    }
    
    public String randomBorrowID() {
        Random random = new Random();
        
        StringBuilder randomNumbers = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int randomNumber = random.nextInt(10);
            randomNumbers.append(randomNumber);
        }
        
        return "MT" + randomNumbers.toString();
    }
    
    public String randomStaff() {
        Random random = new Random();
        
        StringBuilder randomNumbers = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int randomNumber = random.nextInt(10);
            randomNumbers.append(randomNumber);
        }
        
        return "NV" + randomNumbers.toString();
    }
    
    public String randomBookRoomID () {
        Random random = new Random();
        
        StringBuilder randomNumbers = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int randomNumber = random.nextInt(10);
            randomNumbers.append(randomNumber);
        }
        
        return "BR" + randomNumbers.toString();
    }
}
