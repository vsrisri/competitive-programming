import java.util.*;

public class MooRoute2 {
    public static final int MAX_TIME = 1000000007;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numAirports = scanner.nextInt();
        int numFlights = scanner.nextInt();
        scanner.nextLine();

        List<List<int[]>> flightConnections = new ArrayList<>();
        for (int i = 0; i <= numAirports; i++) {
            flightConnections.add(new ArrayList<>());
        }

        for (int i = 0; i < numFlights; i++) {
            int source = scanner.nextInt();
            int departure = scanner.nextInt();
            int destination = scanner.nextInt();
            int startTime = scanner.nextInt();
            scanner.nextLine();
            flightConnections.get(source).add(new int[]{departure, destination, startTime});
        }

        int[] stopovers = new int[numAirports + 1];
        for (int i = 1; i <= numAirports; i++) {
            stopovers[i] = scanner.nextInt();
        }
        stopovers[1] = 0;
        scanner.nextLine();

        List<List<int[]>> adjustedFlights = new ArrayList<>();
        for (int i = 0; i <= numAirports; i++) {
            adjustedFlights.add(new ArrayList<>());
        }

        for (int i = 1; i <= numAirports; i++) {
            for (int[] flight : flightConnections.get(i)) {
                int arrival = flight[0];
                int dest = flight[1];
                int start = flight[2];
                adjustedFlights.get(i).add(new int[]{arrival - stopovers[i], dest, start});
            }
            adjustedFlights.get(i).sort((f1, f2) -> Integer.compare(f2[0], f1[0]));
        }

        int[] earliestArrival = new int[numAirports + 1];
        Arrays.fill(earliestArrival, MAX_TIME);
        earliestArrival[1] = 0;

        int[] flightIndices = new int[numAirports + 1];

        Queue<int[]> queue = new LinkedList<>();
        for (int[] flight : adjustedFlights.get(1)) {
            queue.add(flight);
        }

        int currentIndex = 0;
        while (currentIndex < queue.size()) {
            int[] currentFlight = queue.poll();
            int departureTime = currentFlight[0];
            int targetAirport = currentFlight[1];
            int startTime = currentFlight[2];

            earliestArrival[targetAirport] = Math.min(earliestArrival[targetAirport], startTime);

            while (flightIndices[targetAirport] < adjustedFlights.get(targetAirport).size() && adjustedFlights.get(targetAirport).get(flightIndices[targetAirport])[0] >= startTime) {
                queue.add(adjustedFlights.get(targetAirport).get(flightIndices[targetAirport]));
                flightIndices[targetAirport]++;
            }

            currentIndex++;
        }

        for (int i = 1; i <= numAirports; i++) {
            if (earliestArrival[i] != MAX_TIME) {
                System.out.println(earliestArrival[i]);
            } else {
                System.out.println(-1);
            }
        }

        scanner.close();
    }
}

