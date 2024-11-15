import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TSPSolver {

    public int solveWithBruteForce(int[][] distanceMatrix, ArrayList<Integer> optimalRoute) {
        int numberOfCities = distanceMatrix.length;
        ArrayList<Integer> cities = new ArrayList<>();
        for (int cityIndex = 0; cityIndex < numberOfCities; cityIndex++) {
            cities.add(cityIndex);
        }

        int minimumDistance = Integer.MAX_VALUE;

        do {
            int currentDistance = calculateTotalDistance(distanceMatrix, cities);
            if (currentDistance < minimumDistance) {
                minimumDistance = currentDistance;
                optimalRoute.clear();
                optimalRoute.addAll(cities);
            }
        } while (generateNextPermutation(cities));

        optimalRoute.add(optimalRoute.get(0));

        return minimumDistance;
    }

    public int solveWithDynamicProgramming(int[][] distanceMatrix, ArrayList<Integer> optimalRoute) {
        int numberOfCities = distanceMatrix.length;
        int[][] dpTable = new int[1 << numberOfCities][numberOfCities];
        int[][] previousCity = new int[1 << numberOfCities][numberOfCities];

        for (int[] row : dpTable) Arrays.fill(row, Integer.MAX_VALUE / 2);
        for (int[] row : previousCity) Arrays.fill(row, -1);

        dpTable[1][0] = 0;

        for (int subsetMask = 1; subsetMask < (1 << numberOfCities); subsetMask++) {
            for (int currentCity = 0; currentCity < numberOfCities; currentCity++) {
                if ((subsetMask & (1 << currentCity)) == 0) continue;

                for (int nextCity = 0; nextCity < numberOfCities; nextCity++) {
                    if ((subsetMask & (1 << nextCity)) != 0 || currentCity == nextCity) continue;

                    int updatedMask = subsetMask | (1 << nextCity);
                    int newCost = dpTable[subsetMask][currentCity] + distanceMatrix[currentCity][nextCity];
                    if (newCost < dpTable[updatedMask][nextCity]) {
                        dpTable[updatedMask][nextCity] = newCost;
                        previousCity[updatedMask][nextCity] = currentCity;
                    }
                }
            }
        }

        int minimumCost = Integer.MAX_VALUE;
        int lastVisitedCity = -1;
        int fullSetMask = (1 << numberOfCities) - 1;

        for (int cityIndex = 1; cityIndex < numberOfCities; cityIndex++) {
            int costToReturn = dpTable[fullSetMask][cityIndex] + distanceMatrix[cityIndex][0];
            if (costToReturn < minimumCost) {
                minimumCost = costToReturn;
                lastVisitedCity = cityIndex;
            }
        }

        optimalRoute.add(0);
        int currentMask = fullSetMask;
        while (lastVisitedCity != -1) {
            optimalRoute.add(lastVisitedCity);
            int tempCity = previousCity[currentMask][lastVisitedCity];
            currentMask ^= (1 << lastVisitedCity);
            lastVisitedCity = tempCity;
        }

        return minimumCost;
    }

    private int calculateTotalDistance(int[][] distanceMatrix, ArrayList<Integer> citySequence) {
        int totalDistance = 0;
        for (int cityIndex = 0; cityIndex < citySequence.size() - 1; cityIndex++) {
            int fromCity = citySequence.get(cityIndex);
            int toCity = citySequence.get(cityIndex + 1);
            totalDistance += distanceMatrix[fromCity][toCity];
        }
        int lastCity = citySequence.get(citySequence.size() - 1);
        totalDistance += distanceMatrix[lastCity][citySequence.get(0)];
        return totalDistance;
    }

    private boolean generateNextPermutation(ArrayList<Integer> cities) {
        int numberOfCities = cities.size();
        int largestIndex = numberOfCities - 2;

        while (largestIndex >= 0 && cities.get(largestIndex) >= cities.get(largestIndex + 1)) {
            largestIndex--;
        }
        if (largestIndex < 0) return false;

        int swapIndex = numberOfCities - 1;
        while (cities.get(swapIndex) <= cities.get(largestIndex)) {
            swapIndex--;
        }

        Collections.swap(cities, largestIndex, swapIndex);

        Collections.reverse(cities.subList(largestIndex + 1, numberOfCities));
        return true;
    }
}
