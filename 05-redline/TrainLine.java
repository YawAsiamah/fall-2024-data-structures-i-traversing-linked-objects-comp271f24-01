public class TrainLine {

    /** The name of the trainline */
    private String name;
    /** Points to the first station in the trainline */
    private TrainStation head;
    /** Points to the last station in the trainline */
    private TrainStation tail;
    /** Keeps a running tally of train stations in the trainline */
    private int numberOfStations;

    /** Full constructor */
    public TrainLine(String name, TrainStation head) {
        this.name = name;
        this.head = head;
        this.numberOfStations = 0;
        if (this.head != null) {
            // If head is not null, there is one station in the line
            this.numberOfStations = 1;
        }
        this.tail = this.head;  // At initialization, head and tail point to the same station
    }

    /** Basic constructor */
    public TrainLine(String name) {
        this(name, null);
    }

    /**
     * Creates a new station with the given name and adds it to the end of the line.
     */
    public void add(String name) {
        TrainStation newStation = new TrainStation(name);
        if (this.head == null) {
            this.head = newStation;  // If the train line is empty, make this station the head
        } else {
            this.tail.setNext(newStation);  // Add the new station after the current tail
        }
        this.tail = newStation;  // Update the tail to the new station
        this.numberOfStations++;  // Update the number of stations
    }

    /** Returns the number of stations in the line */
    public int getNumberOfStations() {
        return numberOfStations;
    }

    /** Method to check if a station with the given name exists in the line */
    public boolean contains(String name) {
        if (name == null || this.head == null) {
            return false;  // Return false if the name is null or the line is empty
        }

        TrainStation cursor = this.head;
        while (cursor != null) {
            if (name.equals(cursor.getName())) {
                return true;  // Station found
            }
            cursor = cursor.getNext();  // Move to the next station
        }
        return false;  // Station not found
    }

    /** Method to find the index of a station with the given name */
    public int indexOf(String name) {
        if (name == null || this.head == null) {
            return -1;  // Return -1 if the name is null or the line is empty
        }

        TrainStation cursor = this.head;
        int index = 0;  // Start at the first station
        while (cursor != null) {
            if (name.equals(cursor.getName())) {
                return index;  // Return the index if station is found
            }
            cursor = cursor.getNext();  // Move to the next station
            index++;
        }
        return -1;  // Station not found
    }

    /** Method to return the names of stations in reverse order */
    public String reverseList() {
        if (this.head == null) {
            return "";  // Return an empty string if the train line is empty
        }

        TrainStation cursor = this.head;
        String reversed = "";  // Initialize the string to hold the reversed list
        while (cursor != null) {
            reversed = cursor.getName() + "\n" + reversed;  // Prepend the station name
            cursor = cursor.getNext();  // Move to the next station
        }
        return reversed.trim();  // Trim the trailing new line and return
    }

    /** Method to check if the train line is empty */
    public boolean isEmpty() {
        return this.head == null;  // Return true if the head is null
    }

    /*******************************************************************************
     * DO NOT REMOVE TESTS FROM THE CODE BELOW. YOU MAY **ADD** YOUR OWN TESTS BUT *
     * YOU MAY NOT REMOVE ANY OF THE EXISTING TEST CODE. *
     ******************************************************************************/
    public static void main(String[] args) {
        // A few station names
        String[] stationNames = { "Howard", "Jarvis", "Morse",
                "Loyola", "Granville", "Thorndale" };
        // A populated trainline
        TrainLine redLineSB = new TrainLine("Red Line SB");
        for (String station : stationNames) {
            redLineSB.add(station);
        }
        // An empty trainline
        TrainLine brownLineSB = new TrainLine("Brown Line SB");
        // A random station name
        String randomName = "Oak Park";
        // Guard tests
        redLineSB.indexOf(null);
        redLineSB.contains(null);
        // Test indexOf on existing values
        boolean indexOfTestExisting = true;
        for (int i = 0; i < stationNames.length; i++) {
            indexOfTestExisting = (indexOfTestExisting && (redLineSB.indexOf(stationNames[i]) == i));
        }
        // Test indexOf for non existing station
        boolean indexOfTestNotExisting = (redLineSB.indexOf(randomName) == -1);
        // Test indexOf on empty line
        boolean indexOfTestingEmpty = (brownLineSB.indexOf(stationNames[0]) == -1);
        // Test contains for existing stations
        boolean containsTestExisting = true;
        for (String station : stationNames) {
            containsTestExisting = (containsTestExisting && redLineSB.contains(station));
        }
        // Test contains for non existing values
        boolean containsTestNonExisting = (!redLineSB.contains(randomName));
        // Test reverse list
        String expectedReverseList = "";
        for (int i = stationNames.length - 1; i >= 0; i--) {
            expectedReverseList = expectedReverseList + stationNames[i] + "\n";
        }
        boolean reverseListTest = redLineSB.reverseList().equals(expectedReverseList.trim());
        // Reporting strings
        final String PASS = "Pass";
        final String FAIL = "Fail";
        String reportIndexOfTestExisting = (indexOfTestExisting) ? PASS : FAIL;
        String formatIndexOfTestExisting = "\n\nindexOf test for existing values: ......... %s";
        String reportIndexOfTestNonExisting = (indexOfTestNotExisting) ? PASS : FAIL;
        String formatIndexOfTestNonExisting = "\nindexOf test for non existing values: ..... %s";
        String reportIndexOfTestEmpty = (indexOfTestingEmpty) ? PASS : FAIL;
        String formatIndexOfTestEmpty = "\nindexOf test for empty object: ............ %s";
        String reportContaisTestExisting = (containsTestExisting) ? PASS : FAIL;
        String formatContainsTestExisting = "\ncontains test for existing values: ........ %s";
        String reportContainsTestNonExisting = (containsTestNonExisting) ? PASS : FAIL;
        String formatContainsTestNonExisting = "\ncontains test for non existing values: .... %s";
        String reportReverseListTest = (reverseListTest) ? PASS : FAIL;
        String formatReverseListTest = "\nreverseList test: ......................... %s\n\n";
        System.out.printf(formatIndexOfTestExisting, reportIndexOfTestExisting);
        System.out.printf(formatIndexOfTestEmpty, reportIndexOfTestEmpty);
        System.out.printf(formatIndexOfTestNonExisting, reportIndexOfTestNonExisting);
        System.out.printf(formatContainsTestExisting, reportContaisTestExisting);
        System.out.printf(formatContainsTestNonExisting, reportContainsTestNonExisting);
        System.out.printf(formatReverseListTest, reportReverseListTest);
        // ----------- YOU MAY ADD YOUR OWN TESTS BELOW THIS COMMENT LINE ---------------
    } // method main
} // class TrainLine
