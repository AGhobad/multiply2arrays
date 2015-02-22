/*
  given two arrays of digits, multiply them and put result in a third array
  example
  [1,4,0,9] +
    [2,4,5]
============
 [3,4,5,2,0,5]

Note thst given arrays could be of arbirary size
*/
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;



public  class Multiply2Arrays
{
    private static  void addRest(int reminder, int arrC[], int arr[], int cIndex, int index, String arrayName)
    {
	//	System.out.println("         addrest from array:" + arrayName);
	if (index < 0)
	    {		
		arrC[cIndex] = reminder;
		return;
	    }
	for (int i = index; i>=0; i --)
	    {
		//		System.out.println("index:" + index + " i:" + i + "arr:" + arr[i]);
		int num = arr[i] + reminder;
		reminder = num / 10;
		num = num % 10;
		arrC[cIndex--] = num;
	    }
    }
    public static void  multipleArrByDigit(int res[], int index,int arr[], int multiplier)
    {
	int arrLen = arr.length ;
	int resIndex = res.length -1 -index;
	int rem = 0;
	for (int k = arrLen -1; k >= 0; k--)
	    {
		int num = 0;
		num = arr[k] * multiplier + rem;
		rem = num / 10;
		num = num % 10;
		res[resIndex--] = num;
	    }
	res[resIndex] = rem;
	
    }
	    
    public static int[] add2Arrays(int arrA[], int arrB[])
    {
	int aLen = arrA.length;
	if (aLen == 0)
	    return arrB;
	
	int bLen = arrB.length;
	if (arrB.length == 0)
	    return arrA;
	
	int cLen = ((aLen > bLen) ? aLen:bLen) + 1;	
	
	int arrC[] = new int[cLen];
	
	int aIndex = aLen  -1;
	int bIndex = bLen  -1;
	int cIndex = cLen  -1;	

	int rem = 0;
	while (aIndex >=0  && bIndex >=0 )
	    {
		//		System.out.println(" aIndex:" +  aIndex + " bIndex:" +  bIndex  + " cIndex:" +  cIndex);
		int num = 0;

		num = arrA[aIndex--] + arrB[bIndex--] + rem;
		rem = num / 10;
		num = num % 10;
		arrC[cIndex--] = num;
		if ( aIndex  < 0  )
		    {
			//System.out.println("+++++++ aIndex:" +  aIndex + " bIndex:" +  bIndex  + " cIndex:" +  cIndex);
			addRest(rem,arrC, arrB, cIndex, bIndex, "arrayB");
			return arrC;
		    }
		if ( bIndex  < 0  )
		    {
			//System.out.println("====== aIndex:" +  aIndex + " bIndex:" +  bIndex  + " cIndex:" +  cIndex);
			addRest(rem,arrC, arrA, cIndex, aIndex, "arrayA");
			return arrC;
		    }
	    }	
	return arrC;
    }
    
    public static int[] multiply2Arrays(int arrA[], int arrB[])
    {
	
	int smallerArr[];
	int biggerArr[];
	if (arrA.length < arrB.length)
	    {
		//		System.out.println("case1");
		smallerArr = arrA;
		biggerArr = arrB;
	    }
	else
	    {
		///		System.out.println("case2");
		smallerArr = arrB;
		biggerArr = arrA;
	    }

	int sLen = smallerArr.length;
	if (sLen == 0)
	    return biggerArr;
	
	int bLen = biggerArr.length;
	if (biggerArr.length == 0)
	    return smallerArr;

	int cLen = bLen + sLen ;
	//	System.out.println("sLen:" + sLen + " bLen:" + bLen + " cLen:" + cLen);
	int arrM[][] = new int[sLen][cLen];
	
	for (int i = sLen -1; i>= 0; i--)
	    {		
		multipleArrByDigit(arrM[i],sLen -1 - i,biggerArr, smallerArr[i]);
		//		printArray(arrM[i]);
	    }
	System.out.println("============================================");
	int arrC[] = arrM[0];
	for (int l = 1; l< sLen; l++)
	    {
		//printArray(arrC);
		arrC =  add2Arrays(arrC, arrM[l]);
	    }
	
	return arrC;
    }


    private static void printArray(int arr[])
    {
	for (int i=0; i< arr.length; i++)
	    System.out.print(arr[i] + ", ");

	System.out.println();
    }
   

    public static void main(String[] args) 
    {
	int arr1[]={9,2,5,0,9};
	int arr2[]={9,0,2};
	int arr[] = multiply2Arrays(arr1,arr2);
	
	printArray(arr1);
	printArray(arr2);
	printArray(arr);
	 
    }
	
}