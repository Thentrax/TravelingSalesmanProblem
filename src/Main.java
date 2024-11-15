import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int[][] distanceMatrix = {
                {0, 10, 15, 20},
                {10, 0, 35, 25},
                {15, 35, 0, 30},
                {20, 25, 30, 0}
        };

        TSPSolver solver = new TSPSolver();

        // Resolver usando Força Bruta
        ArrayList<Integer> bruteForceRoute = new ArrayList<>();
        int bruteForceDistance = solver.solveWithBruteForce(distanceMatrix, bruteForceRoute);
        System.out.println("Menor distância encontrada (Força Bruta): " + bruteForceDistance);
        System.out.println("Rota sugerida (Força Bruta): " + formatRoute(bruteForceRoute));

        // Resolver usando Programação Dinâmica (Held-Karp)
        ArrayList<Integer> dynamicProgrammingRoute = new ArrayList<>();
        int dynamicProgrammingDistance = solver.solveWithDynamicProgramming(distanceMatrix, dynamicProgrammingRoute);
        System.out.println("Menor distância encontrada (Held-Karp): " + dynamicProgrammingDistance);
        System.out.println("Rota sugerida (Held-Karp): " + formatRoute(dynamicProgrammingRoute));
    }

    private static String formatRoute(ArrayList<Integer> route) {
        StringBuilder formattedRoute = new StringBuilder();
        for (int cityIndex = 0; cityIndex < route.size(); cityIndex++) {
            formattedRoute.append("Cidade ").append(route.get(cityIndex) + 1);
            if (cityIndex < route.size() - 1) formattedRoute.append(" -> ");
        }
        return formattedRoute.toString();
    }
}
