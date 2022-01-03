import java.util.ArrayList;

public class BruteCollinearPoints {

    private ArrayList<LineSegment> LineSegments = new ArrayList<LineSegment>();

    public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
    {
        int size = points.length;
        for (int i = 0; i < size; i++) { // 1st Cycle
            for (int j = i + 1; j < size; j++) { // 2nd Cycle
                double slope = points[i].slopeTo(points[j]);
                for (int k = j + 1; k < size; k++) { // 3rd Cycle
                    double tempSlope = points[j].slopeTo(points[k]);
                    if (Double.compare(slope, tempSlope) == 0)
                        for (int o = k + 1; o < size; ) { // 4th Cycle
                            double finalSlope = points[k].slopeTo(points[o]);
                            if (Double.compare(slope, finalSlope) == 0) {
                                LineSegments.add(new LineSegment(points[i], points[j])); // TODO might need fixing
                                LineSegments.add(new LineSegment(points[j], points[k]));
                                LineSegments.add(new LineSegment(points[k], points[o]));
                            }
                        }
                }
            }
        }
    }

    public int numberOfSegments()        // the number of line segments
    {
        return LineSegments.size();
    }

    public LineSegment[] segments()                // the line segments
    {
        return (LineSegment[]) LineSegments.toArray();
    }
}
