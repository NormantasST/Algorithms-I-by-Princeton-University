import java.util.ArrayList;

public class FastCollinearPoints {
    public FastCollinearPoints(Point[] points)     // finds all line segments containing 4 or more points
    {
        for (int p = 0; p < points.length; p++) {
            ArrayList<LineSegment> tempLineSegments = new ArrayList<LineSegment>(); // size - n
            for (int q = p + 1; q < points.length; q++) {

                tempLineSegments.add(new LineSegment(points[p], points[q])); // Builds Line Segment Library
                //tempLineSegments = Collections.sort(tempLineSegments, Collections.);

            }

        }

    }

    public int numberOfSegments()        // the number of line segments
    {
        return 0; // TODO
    }

    public LineSegment[] segments()                // the line segments
    {
        return null; // TODO
    }
}
