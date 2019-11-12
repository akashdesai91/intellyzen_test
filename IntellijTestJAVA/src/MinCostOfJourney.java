import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;;

public class MinCostOfJourney {
    
	/**
	 * Method to sail persons to river and person who is having less cost return
	 */
	public static int templeToVillage(ArrayList<Integer> temple, int person1, int person2) {
		temple.add(person1);
		temple.add(person2);
		Collections.sort(temple);
		int returnCost = temple.get(0);
		temple.remove(0);
		return returnCost;
	}

	public static void main(String a[]) throws IOException {

		

		/**
		 * Uncomment code to check with multiple input
		 */
		// System.out.println("Please enter no of test cases (Max Test Cases are 10) :
		// ");
//	int noOfTestCasesT = sc.nextInt();
//	int noOfPersonsList[] = new int[noOfTestCasesT];   
//	ArrayList<String[]> testCasesCostList = new ArrayList<String[]>();  
//	for(int i=0; i < noOfTestCasesT; i++) {
//		Scanner sc1 = new Scanner(System.in);
//		System.out.println("Please enter no of persons from village (Range In Between 1 to 100000) : ");
//		int noOfPersons =  sc1.nextInt();
//		noOfPersonsList[i] = noOfPersons;
//		System.out.println("Please enter space separated cost for each person (Range In Between 1 to 100000) : ");
//		
//		String[] str = sc1.nextLine() != null && sc1.nextLine() != "" ? sc1.nextLine().split(",") : null;
//		testCasesCostList.add(str);
//		sc1.close();
//		}
		
		
		/**
		 * Check With Single Input
		 */
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter no of persons from village (Range In Between 1 to 100000) : ");
		int noOfTestCasesT = 1;
		int noOfPersonsList[] = new int[noOfTestCasesT];
		ArrayList<String[]> testCasesCostList = new ArrayList<String[]>();
		noOfPersonsList[0] = sc.nextInt();
		System.out.println("Please enter space separated cost for each person (Range In Between 1 to 100000) : ");
		Scanner sc1 = new Scanner(System.in);
		String[] tempStr = sc1.next().split(",");
		testCasesCostList.add(tempStr);

		for (int i = 0; i < noOfPersonsList.length; i++) {
			/**
			 * Take String Input to Array as per requirement
			 */
			String[] str = testCasesCostList.get(0);
			int[] personCost = Arrays.stream(str).mapToInt(Integer::parseInt).toArray();
			int noOfPersonsInVillage = personCost.length;
			boolean lessCostPresentAtTemple = false;
			int totalCost = 0;
         
			ArrayList<Integer> village = new ArrayList<Integer>();
			ArrayList<Integer> temple = new ArrayList<Integer>();
			for (int element : personCost) {
				village.add(element);
			}
         
			while ((noOfPersonsInVillage - 1) != 0) {
				Collections.sort(village);
				if (village.size() > 2) {
					if (village.size() != 3
							&& ((village.get(1) - village.get(0)) < (village.get(2) - village.get(1)))) {
						totalCost += village.get(1);
						// Add element in temple and get best seller return cost
						int returnCost = templeToVillage(temple, village.get(0), village.get(1));
						totalCost += returnCost;
						village.remove(0);
						village.remove(0);
						village.add(returnCost);
						lessCostPresentAtTemple = true;
					} else if (lessCostPresentAtTemple) {
						totalCost += village.get(village.size() - 1);
						// Add element in temple and get best seller return cost
						int returnCost = templeToVillage(temple, village.get(village.size() - 2),
								village.get(village.size() - 1));
						totalCost += returnCost;
						village.remove(village.size() - 1);
						village.remove(village.size() - 1);
						village.add(returnCost);
						lessCostPresentAtTemple = false;
					} else {
						totalCost += village.get(village.size() - 1);
						// Add element in temple and get best seller return cost
						int returnCost = templeToVillage(temple, village.get(0), village.get(village.size() - 1));
						totalCost += returnCost;
						village.remove(0);
						village.remove(village.size() - 1);
						village.add(returnCost);
						lessCostPresentAtTemple = false;
					}
				} else {
					totalCost += village.get(village.size() - 1);
					temple.add(village.get(0));
				}
				noOfPersonsInVillage--;
			}
			System.out.println("Total Cost for  is " + totalCost);
		}
	}

}
