import java.util.Arrays;
/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author Ariel Wong-Edwin.
 * @version    31.10.24
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;
    //Counting the total accesses   
    private int totalAccesses;
    //Assuming no leap years
    private int[] daysinYear;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader();
        //starting the total access
        totalAccesses = 0; 
        //Assuming no leap years
        daysinYear = new int[365];
    }
    
    
    
    /**
     * Analyze the daily access data from the log file.
     */
    public void analyzeData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
            totalAccesses++;
            //adding incriments
            int days = entry.getdayoftheYear();
            daysinYear[days]++;
        }
    }
    /**
     * Print the daily counts.
     * These should have been set with a prior
     * call to analyzeDailyData.
     */
    public void printCounts()
    {
        System.out.println("Day: Count");
        for(int day = 0; day < daysinYear.length; day++) {
            System.out.println(day + ": " + daysinYear[day]);
        }
        System.out.println("Hour: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Determining the busiest hour on the file.
     */
    public int busiesthouronFile(){
        int maximumHour = 0;
        for (int hour = 1; hour < hourCounts.length; hour++){
            if (hourCounts[hour] > hourCounts[maximumHour]){
            maximumHour = hour;}
        }
        return maximumHour;
    }
    
    
    /**
     * Finding the quietest number on the file.
     */
    public int quietesthouronFile(){
        int minimumHour = 0;
        for (int hour = 1; hour < hourCounts.length; hour++){
            if (hourCounts[hour] < hourCounts[minimumHour]){
            minimumHour = hour;}
        }
        return minimumHour;
    }
    /**
     * Finding the busiest two-hour period
     */
    public int[] busiesttwohourPeriod() {
        int maximumHour1 = 0;
        int maximumHour2 = 0;

        for (int hour = 0; hour < 24; hour++) {
            // Sum accesses for the current hour and the next hour
            int currentlyAccessed = hourCounts[hour] + hourCounts[(hour + 1) % 24];
            if (currentlyAccessed > maximumHour1) {
                maximumHour1 = currentlyAccessed;
                maximumHour2 = hour;
            }
        }
        return new int[] {maximumHour1, maximumHour2};
        
    }
    
    public int quietestDay(){
        int quietestDay = 0;
        for (int day = 1; day < daysinYear.length; day++){
            if (daysinYear[day] < daysinYear[quietestDay]){
                quietestDay = day;
            }
        }
        return quietestDay + 1;
    }
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
    /**
     * Getting the total number of accesses
     */
    public int totalnumAccesses() {
        return totalAccesses;
    }
}
