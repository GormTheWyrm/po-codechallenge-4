package org.challenge;

import java.sql.Array;
import java.util.ArrayList;

public class ProblemOne {
    /*
    Weekly Coding Challenge: Word Bucket

    Write a function that divides a phrase into word buckets, with each bucket containing n or fewer characters. Only include full words inside each bucket.

    Examples
    bucketize("she sells sea shells by the sea", 10)
    ➞ ["she sells", "sea shells", "by the sea"]

    bucketize("the mouse jumped over the cheese", 7)
    ➞ ["the", "mouse", "jumped", "over", "the", "cheese"]

    bucketize("fairy dust coated the air", 20)
    ➞ ["fairy dust coated", "the air"]

    bucketize("a b c d e", 2)
    ➞ ["a", "b", "c", "d", "e"]

    Notes
    Spaces count as one character.
    Trim beginning and end spaces for each word bucket (see final example).
    If buckets are too small to hold a single word, return an empty array: []
    The final goal isn't to return just the words with a length equal (or lower) to the given n, but to return the entire given phrase bucketized (if possible).
    So, for the specific case of "by" the only word with a proper length, the phrase can't be bucketized, and the returned array has to be empty.
     */


    public static void main(String[] args) {
        //I should of done unit testing, this was more work than it was worth - thought I could skimp.
        String[] arr1 = bucketize("fairy dust coated the air", 20);
        printArr(arr1);
        String[] arr2 = bucketize("the mouse jumped over the cheese", 7);
        printArr(arr2);
        String[] arr3 = bucketize("she sells sea shells by the sea", 10);
        printArr(arr3);
        String[] arr4 = bucketize("a b c d e", 2);
        printArr(arr4);
        String[] arr5 = bucketize("on a pig", 3);
        printArr(arr5); //prints empty array - works
        String[] arr6 = bucketize("on a pig", 4);
        printArr(arr6); //prints empty array
        String[] arr7 = bucketize("pig", 3);
        printArr(arr7); //prints empty array
        String[] arr8 = bucketize("pig", 2);
        printArr(arr8); //prints empty array

    }

    public static String[] bucketize(String word, int bucketSize){
        word = word.trim();
        //initialize an arraylist, so size doesnt need to be known
        ArrayList<String> tempList = new ArrayList<String>();

        //split into words (array...)
        String[] tempArray = word.split(" ");
//        printArr(tempArray); //trimmed!
        //current string = next in array
        String currentString = ""; //should make this a string builder for best practice

        for (int i = 0; i<tempArray.length; i++){ //will not work on array of size 0
            if (i == tempArray.length-1){ //if last word in array
                if(currentString.length()+tempArray[i].trim().length()+1 <= bucketSize){ //if current word + array[i] fits, add to list
                    tempList.add((currentString+" "+ tempArray[i]).trim()); //note the space
                } else if (tempArray[i].trim().length()<=bucketSize){
                    if (currentString.length()>0){
                        tempList.add(currentString.trim());// add current string to bucket list - if there is a string
                    }
                    tempList.add(tempArray[i].trim()); //add last word in temparray to bucket list
                }
                else { //should I specify a condition here? ...I dont think I need to
                    return new String[0]; //return empty array if last word does not fit
                }
            }
            else if (currentString.length()<1){ //if current string is empty (and not last word)
                if (tempArray[i].trim().length() > bucketSize){
                    return new String[0]; //return empty array if word too big
                }
                else { //if current string not empty (and not last word)
                    currentString = tempArray[i].trim();
                }

            }
            else if (currentString.length()>0){ //if current string not empty (and not last word)
                if (currentString.length()+tempArray[i].trim().length()+1 <= bucketSize){ //if "word word" fits
//                    System.out.println("current string = " + currentString);
//                    System.out.println(currentString.length());
                    currentString = currentString + " " + tempArray[i].trim();
                }
                else { //if doesnt fit
                    tempList.add(currentString.trim());
                    currentString = "";

                    i--;//iterates over word again - this time with empty cuerrntstring
                }
            }


        }
        //turn templist into array
        String[] answer = new String[tempList.size()];
        for (int i = 0; i < tempList.size();i++){
//            System.out.println(tempList.get(1)); //second bucket not appearing
            answer[i] = tempList.get(i).trim(); //trim whitespaces away!
        }
        return answer;

        //if size of next word+ 1 + current string < bucketsize
        //then currentstr = currentstr+next
        //create bucket array and return it

    }
    public static void printArr(String[] arr1){
        System.out.println("~~~ ~~~ ~~~");
        for (int i = 0; i<arr1.length;i++){
            System.out.println(arr1[i]);

        }
    }
}

//strip trailing and leading whitespace... though there should not be any ?
//currently "sea shells" is split into 2 lines... and should be on one
//may need to trim earlier... somethign is wrong...