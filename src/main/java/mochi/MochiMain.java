package mochi;

import mochi.exceptions.MissingArgumentException;
import mochi.exceptions.MissingDescription;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * main class that Mochi is running under, catches most exceptions here
 */
public class MochiMain {
    //Java main need to be static, so I cannot call this.isRunning. So I outsourced it
    public static void main(String[] args) {
        try {
            new Parser().runMain();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (MissingArgumentException e) {
            System.out.println("Missing argument");
        } catch (MissingDescription e) {
            System.out.println("Missing Description");
        }catch (IOException e) {
            System.out.println("Path to save file incorrect");
        }
    }
}
