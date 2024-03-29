package edu.wit.cs.comp3370;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/* Sorts integers from command line using various algorithms 
 * 
 * Wentworth Institute of Technology
 * COMP 3370
 * Lab Assignment 1
 * 
 */

public class LAB1 {

	/**
	 * Counting sort creates an array from 0 to the max in the provided list to
	 * be sorted. It then runs through the provided array and uses the numbers
	 * as the index value and adds one in the created array. Using the created
	 * array it over writes the provided array by concatenation the index number
	 * the amount of times as the value at that index
	 */
	public static int[] countingSort(int[] a) {
		int max = 0;
		for (int x : a) {
			max = Math.max(x, max);
		}
		max++;

		System.out.println("Max: " + max);
		int[] histogram = new int[max];

		for (int x : a)
			histogram[x]++;

		int i = 0;
		for (int x = 0; x < histogram.length; x++)
			for (int y = 0; y < histogram[x]; y++) {
				a[i] = x;
				i++;
			}

		return a;
	}

	/**
	 * Performs a counting sort digit by digit, starting at the least
	 * significant
	 * 
	 * @param a
	 * @return
	 */
	public static int[] radixSort(int[] a) {
		if (a.length <= 1)
			return a;
		int max = Integer.MIN_VALUE;
		for (int x : a)
			max = Math.max(x, max);

		for (int digit = 1; max / digit > 0; digit *= 10) {
			int[] count = new int[10];
			int[] pos = new int[a.length];

			int tot = 0;
			for (int x = 0; x < 10; x++) {
				for (int y = 0; y < a.length; y++) { // This feels inefficient
														// to me
					int d = (a[y] / digit) % 10;
					if (d != x)
						continue;
					count[x]++;
					pos[y] = tot;
					tot++;
				}
			}

			int[] output = new int[a.length];
			for (int x = 0; x < output.length; x++) {
				output[pos[x]] = a[x];
			}
			a = output;
		}

		return a;
	}

	/********************************************
	 * 
	 * You shouldn't modify anything past here
	 * 
	 ********************************************/

	public final static int MAX_INPUT = 524287;
	public final static int MIN_INPUT = 0;

	// example sorting algorithm
	public static int[] insertionSort(int[] a) {

		for (int i = 1; i < a.length; i++) {
			int tmp = a[i];
			int j;
			for (j = i - 1; j >= 0 && tmp < a[j]; j--)
				a[j + 1] = a[j];
			a[j + 1] = tmp;
		}

		return a;
	}

	/*
	 * Implementation note: The sorting algorithm is a Dual-Pivot Quicksort by
	 * Vladimir Yaroslavskiy, Jon Bentley, and Joshua Bloch. This algorithm
	 * offers O(n log(n)) performance on many data sets that cause other
	 * quicksorts to degrade to quadratic performance, and is typically faster
	 * than traditional (one-pivot) Quicksort implementations.
	 */
	public static int[] systemSort(int[] a) {
		Arrays.sort(a);
		return a;
	}

	// read ints from a Scanner
	// returns an array of the ints read
	private static int[] getInts(Scanner s) {
		ArrayList<Integer> a = new ArrayList<Integer>();

		while (s.hasNextInt()) {
			int i = s.nextInt();
			if ((i <= MAX_INPUT) && (i >= MIN_INPUT))
				a.add(i);
		}

		return toIntArray(a);
	}

	// copies an ArrayList of Integer to an array of int
	private static int[] toIntArray(ArrayList<Integer> a) {
		int[] ret = new int[a.size()];
		for (int i = 0; i < ret.length; i++)
			ret[i] = a.get(i);
		return ret;
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		System.out.printf("Enter the sorting algorithm to use ([c]ounting, [r]adix, [i]nsertion, or [s]ystem): ");
		char algo = s.next().charAt(0);

		System.out.printf("Enter the integers that you would like sorted, followed by a non-integer character: ");
		int[] unsorted_values = getInts(s);
		int[] sorted_values = {};

		s.close();

		switch (algo) {
		case 'c':
			sorted_values = countingSort(unsorted_values);
			break;
		case 'r':
			sorted_values = radixSort(unsorted_values);
			break;
		case 'i':
			sorted_values = insertionSort(unsorted_values);
			break;
		case 's':
			sorted_values = systemSort(unsorted_values);
			break;
		default:
			System.out.println("Invalid sorting algorithm");
			System.exit(0);
			break;
		}

		System.out.println(Arrays.toString(sorted_values));
	}

}
